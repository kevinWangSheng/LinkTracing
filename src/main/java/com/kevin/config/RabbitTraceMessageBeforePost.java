package com.kevin.config;

import com.kevin.common.Constance;
import com.kevin.util.TraceIdUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;


/**
 * @author wang
 * @create 2024-01-10-23:19
 */
public class RabbitTraceMessageBeforePost implements MessagePostProcessor {
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        return message;
    }

    @Override
    public Message postProcessMessage(Message message, Correlation correlation, String exchange, String routingKey) {
        String traceId = TraceIdUtil.getTraceId();
        if(StringUtils.isBlank(traceId)){
            traceId = TraceIdUtil.generateTraceId();
        }
        message.getMessageProperties().setHeader(Constance.RabbitConstance.RABBIT_TRACE_ID,traceId);
        return message;
    }
}
