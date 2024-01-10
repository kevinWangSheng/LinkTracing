package com.kevin.config;

import com.kevin.common.Constance;
import com.kevin.util.TraceIdUtil;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.ContainerCustomizer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    public MessageListenerContainer container(ConnectionFactory connectionFactory,
                                              TraceIdAwareListenerAdapter traceIdAwareListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(traceIdAwareListenerAdapter);
        return container;
    }

    @Bean
    public TraceIdAwareListenerAdapter traceIdAwareListenerAdapter(){
        return new TraceIdAwareListenerAdapter();
    }


    @Bean
    public Queue Queue() {
        return new Queue(Constance.RabbitConstance.TRACE_QUEUE);
    }
}
