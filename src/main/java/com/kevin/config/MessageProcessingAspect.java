package com.kevin.config;

import com.kevin.common.Constance;
import com.kevin.util.TraceIdUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wang
 * @create 2024-01-11-0:47
 */
@Aspect
@Component
public class MessageProcessingAspect {
    @Around("@annotation(rabbitListener)")
    public Object aroundMessageHandling(ProceedingJoinPoint joinPoint, RabbitListener rabbitListener) throws Throwable {
        try {
            Object[] args = joinPoint.getArgs();
            for(Object arg : args){
                if(arg instanceof Message){
                    String traceId = ((Message) arg).getMessageProperties().getHeader(Constance.RabbitConstance.RABBIT_TRACE_ID);
                    TraceIdUtil.setTraceId(traceId);
                }
            }
            return joinPoint.proceed();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            // 在消息处理之后清除 TraceId
            TraceIdUtil.removeTraceId();
        }
    }
}
