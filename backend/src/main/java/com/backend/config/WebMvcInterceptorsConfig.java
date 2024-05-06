package com.backend.config;

import com.backend.interceptor.StockInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcInterceptorsConfig implements WebMvcConfigurer {

    private StockInterceptor stockInterceptor;

    @Autowired
    private void setStockInterceptor(StockInterceptor stockInterceptor){
    this.stockInterceptor= stockInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(stockInterceptor).addPathPatterns("/stocks/**");
    }
}
