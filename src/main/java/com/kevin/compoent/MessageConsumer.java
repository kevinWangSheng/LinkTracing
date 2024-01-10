package com.kevin.compoent;

import com.kevin.common.Constance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wang
 * @create 2024-01-10-23:42
 */
@Component

public class MessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @RabbitListener(queues = Constance.RabbitConstance.TRACE_QUEUE)
    public void process(Message message){
        String messageBody = new String(message.getBody());
        logger.info("消费者消费消息：{}",messageBody);
    }
}
