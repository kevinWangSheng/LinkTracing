package com.kevin.service.impl;

import com.kevin.common.Constance;
import com.kevin.service.MessageServiceProducer;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author wang
 * @create 2024-01-10-23:39
 */
@Service
public class MessageServiceProducerImpl implements MessageServiceProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(Object message) {
        rabbitTemplate.convertAndSend(Constance.RabbitConstance.TRACE_QUEUE,message);
    }
}
