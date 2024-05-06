package com.backend.config;

import com.backend.interceptor.SalesInterceptor;
import com.backend.interceptor.StockInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcInterceptorsConfig implements WebMvcConfigurer {

    private StockInterceptor stockInterceptor;

    private SalesInterceptor salesInterceptor;

    @Autowired
    private void setStockInterceptor(StockInterceptor stockInterceptor){
    this.stockInterceptor= stockInterceptor;
    }

    @Autowired
    public void setSalesInterceptor(SalesInterceptor salesInterceptor) {
        this.salesInterceptor = salesInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(stockInterceptor).addPathPatterns("/stocks/**").excludePathPatterns("/stocks/getAllStocks");

        registry.addInterceptor(salesInterceptor).addPathPatterns("/sales/**");


    }
}
