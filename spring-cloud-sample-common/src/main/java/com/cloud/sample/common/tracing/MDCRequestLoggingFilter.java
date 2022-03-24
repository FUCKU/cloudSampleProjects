package com.cloud.sample.common.tracing;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * Component, managing resources in MDC for logging purposes.
 */
@Component
public class MDCRequestLoggingFilter extends CommonsRequestLoggingFilter {
    public static final String TRACE_ID = "TRACE_ID";

    /**
     * Propagation some information from the incoming request to {@link MDC}.
     *
     * @param request - {@link HttpServletRequest}
     * @param message - {@link String}
     */
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        MDC.put(TRACE_ID, request.getHeader(TRACE_ID));
    }

    /**
     * Clearing MDC after request.
     *
     * @param request - {@link HttpServletRequest}
     * @param message - {@link String}
     */
    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        MDC.clear();
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return Boolean.TRUE;
    }
}
