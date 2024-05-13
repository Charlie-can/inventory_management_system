package com.backend.config;

import com.backend.interceptor.*;
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

    private UserManagementInterceptor userManagementInterceptor;

    @Autowired

    public void setInventoryInterceptor(InventoryInterceptor inventoryInterceptor) {
        this.inventoryInterceptor = inventoryInterceptor;
    }

    @Autowired

    public void setUserManagementInterceptor(UserManagementInterceptor userManagementInterceptor) {
        this.userManagementInterceptor = userManagementInterceptor;
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

        registry.addInterceptor(userManagementInterceptor).addPathPatterns("/userManagement/**");

    }
}
