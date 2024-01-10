package com.kevin.config;

import com.kevin.util.TraceIdUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author wang
 * @create 2024-01-10-20:00
 */
public class TraceIdInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(TraceIdInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = TraceIdUtil.generateTraceId();
        logger.info("");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TraceIdUtil.removeTraceId();
    }
}
