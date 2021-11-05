package com.inventory.manager.service.filter;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class TransactionFilter implements Filter {

    private static final String TRANSACTION_ID = "HTTP_X_TRANSACTION_ID";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try (MDC.MDCCloseable closeable = MDC.putCloseable("txnId", getTransactionId(httpServletRequest, httpServletResponse))) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private String getTransactionId(HttpServletRequest request, HttpServletResponse response) {
        String transactionId = request.getHeader(TRANSACTION_ID) == null ?
                createTxnId(response) : request.getHeader(TRANSACTION_ID);
        return transactionId;
    }

    private String createTxnId(HttpServletResponse response) {
        String txnId = "TXN-" + UUID.randomUUID().toString();

        response.addHeader(TRANSACTION_ID, txnId);

        return txnId;
    }

    @Override
    public void destroy() {

    }
}
