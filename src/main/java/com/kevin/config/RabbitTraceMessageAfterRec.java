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
 * @create 2024-01-10-23:32
 */
public class RabbitTraceMessageAfterRec implements MessagePostProcessor {
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        String traceId = message.getMessageProperties().getHeader(Constance.RabbitConstance.RABBIT_TRACE_ID);
        if(StringUtils.isBlank(traceId)){
            traceId = TraceIdUtil.generateTraceId();
        }
        TraceIdUtil.setTraceId(traceId);
        return message;
    }
}
