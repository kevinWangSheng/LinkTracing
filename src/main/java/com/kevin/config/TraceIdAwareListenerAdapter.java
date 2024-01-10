package com.kevin.config;

import com.kevin.common.Constance;
import com.kevin.util.TraceIdUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * @author wang
 * @create 2024-01-11-0:28
 */
public class TraceIdAwareListenerAdapter extends MessageListenerAdapter{


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String traceId = message.getMessageProperties().getHeader(Constance.RabbitConstance.RABBIT_TRACE_ID);
            TraceIdUtil.setTraceId(traceId);
            super.onMessage(message, channel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            TraceIdUtil.removeTraceId();
        }
    }
}
