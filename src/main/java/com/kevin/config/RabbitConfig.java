package com.kevin.config;

import com.kevin.common.Constance;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.ContainerCustomizer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wang
 * @create 2024-01-10-23:12
 */
@Configuration
public class RabbitConfig {
    @Bean
    public RabbitTemplate rabbitTemplate(RabbitTemplateConfigurer configurer, ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate();
        configurer.configure(template,connectionFactory);

        template.setBeforePublishPostProcessors(new RabbitTraceMessageBeforePost());
        return template;
    }

    // 暂时不用这种方式
//    @Bean(name = {"rabbitListenerContainerFactory"})
//    @ConditionalOnProperty(
//            prefix = "spring.rabbitmq.listener",
//            name = {"type"},
//            havingValue = "simple",
//            matchIfMissing = true
//    )
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                                     ConnectionFactory connectionFactory,
                                                                                     ObjectProvider<ContainerCustomizer<SimpleMessageListenerContainer>> simpleContainerCustomizer){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory,connectionFactory);
        simpleContainerCustomizer.ifUnique(factory::setContainerCustomizer);
        factory.setAfterReceivePostProcessors(new RabbitTraceMessageAfterRec());
        return factory;
    }

    @Bean
    public Queue Queue() {
        return new Queue(Constance.RabbitConstance.TRACE_QUEUE);
    }
}
