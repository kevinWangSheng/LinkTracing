package com.kevin.service.impl;

import com.kevin.service.TraceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wang
 * @create 2024-01-10-20:21
 */
@Service
public class TraceServiceImpl implements TraceService {
    private final Logger logger = LoggerFactory.getLogger(TraceServiceImpl.class);
    @Async
    @Override
    public void sayHelloWorld() {
        logger.info("hello world");
    }
}
