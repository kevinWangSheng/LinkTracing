package com.kevin.common;

/**
 * @author wang
 * @create 2024-01-10-19:40
 */
public interface Constance {
    String TRACE_ID = "traceId";

    class RabbitConstance{
        public static final String RABBIT_TRACE_ID = "rabbit_trace_ID";
        public static final String TRACE_QUEUE = "trace_queue";
    }
}
