package com.inventory.manager.service.filter;

import com.inventory.manager.service.utils.context.SampleContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextFilter extends OncePerRequestFilter {

    private static final String X_APP_ID = "X-APP-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try {
            SampleContextHolder.setContext(new SampleContextHolder.Context(request.getHeader(X_APP_ID)));
            chain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            SampleContextHolder.clear();
        }
    }
}
