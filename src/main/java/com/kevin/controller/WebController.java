package com.kevin.controller;

import com.kevin.common.Result;
import com.kevin.service.MessageServiceProducer;
import com.kevin.service.TraceService;
import com.kevin.util.TraceIdUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wang
 * @create 2024-01-10-20:09
 */
@RestController
@RequestMapping("/web")
public class WebController {
    @Resource
    private TraceService traceService;

    @Resource
    private MessageServiceProducer messageServiceProducer;
    @GetMapping("hello")
    public Result hello(){
        traceService.sayHelloWorld();
        return Result.success(TraceIdUtil.getTraceId());
    }

    @GetMapping("message")
    public Result message(){
        messageServiceProducer.send("hello world");
        return Result.success(TraceIdUtil.getTraceId());
    }
}
