package com.backend.config;

import com.backend.interceptor.InventoryInterceptor;
import com.backend.interceptor.SalesInterceptor;
import com.backend.interceptor.StockInterceptor;
import com.backend.interceptor.StorageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcInterceptorsConfig implements WebMvcConfigurer {

    private StockInterceptor stockInterceptor;

    private SalesInterceptor salesInterceptor;

    private StorageInterceptor storageInterceptor;


   private InventoryInterceptor inventoryInterceptor;

   @Autowired
    public void setInterceptor(InventoryInterceptor inventoryInterceptor) {
        this.inventoryInterceptor = inventoryInterceptor;
    }

    @Autowired
    private void setStockInterceptor(StockInterceptor stockInterceptor) {
        this.stockInterceptor = stockInterceptor;
    }

    @Autowired
    public void setSalesInterceptor(SalesInterceptor salesInterceptor) {
        this.salesInterceptor = salesInterceptor;
    }

    @Autowired
    public void setStorageInterceptor(StorageInterceptor storageInterceptor) {
        this.storageInterceptor = storageInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(stockInterceptor).addPathPatterns("/stocks/**").excludePathPatterns("/stocks/getAllStocks");

        registry.addInterceptor(salesInterceptor).addPathPatterns("/sales/**");

        registry.addInterceptor(storageInterceptor).addPathPatterns("/storage/**");

        registry.addInterceptor(inventoryInterceptor).addPathPatterns("/inventory/**");

    }
}
