package com.kevin.util;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.lang.generator.UUIDGenerator;
import cn.hutool.core.util.IdUtil;
import com.kevin.common.Constance;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.util.IdGenerator;

/**
 * @author wang
 * @create 2024-01-10-19:39
 */
public class TraceIdUtil {
    public static String setTraceId(String traceId){
        if(StringUtils.isNotBlank(traceId)){
            MDC.put(Constance.TRACE_ID,traceId);
        }else{
            return generateTraceId();
        }
        return traceId;
    }

    public static String generateTraceId(){
        String traceId = IdUtil.simpleUUID();
        MDC.put(Constance.TRACE_ID,traceId);
        return traceId;
    }

    public static void removeTraceId(){
        MDC.remove(Constance.TRACE_ID);
    }

    public static String getTraceId() {
        return MDC.get(Constance.TRACE_ID);
    }
}
