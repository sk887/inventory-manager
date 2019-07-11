package com.inventory.manager.service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.inventory.manager.service.filter.TransactionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
@EnableTransactionManagement
@PropertySources({
        @PropertySource("classpath:application.yml")
})
public class ApplicationConfiguration {

    @Bean
    public Filter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(5120);
        return filter;
    }

    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.
                json()
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .timeZone(TimeZone.getTimeZone("Asia/Calcutta"))
                .build();
    }

    @Bean("date")
    public ObjectMapper objectMapperWithDate() {
        // Customize...
        return Jackson2ObjectMapperBuilder.
                json()
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .timeZone(TimeZone.getTimeZone("Asia/Calcutta"))
                .build();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TransactionFilter());

        return registrationBean;
    }

}

