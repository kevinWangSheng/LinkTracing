package com.kevin.common;

import org.slf4j.MDC;

/**
 * @author wang
 * @create 2024-01-10-21:38
 */
public class TraceRunnable implements Runnable{
    private String traceId;

    // 使用代理模式，来完成调用
    private Runnable deleteate;

    public TraceRunnable(String traceId, Runnable deleteate) {
        this.traceId = traceId;
        this.deleteate = deleteate;
    }

    @Override
    public void run() {
        try {
            MDC.put(Constance.TRACE_ID,traceId);
            deleteate.run();
        } catch (IllegalArgumentException e) {
            MDC.remove(Constance.TRACE_ID);
        }
    }
}
