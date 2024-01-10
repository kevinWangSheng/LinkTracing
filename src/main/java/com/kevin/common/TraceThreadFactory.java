package com.kevin.common;

import com.kevin.util.TraceIdUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author wang
 * @create 2024-01-10-21:42
 */
public class TraceThreadFactory implements ThreadFactory {
    private final ThreadFactory delegateThreadFactory = Executors.defaultThreadFactory();
    @Override
    public Thread newThread(Runnable task) {
        return delegateThreadFactory.newThread(new TraceRunnable(TraceIdUtil.getTraceId(),task));
    }
}
