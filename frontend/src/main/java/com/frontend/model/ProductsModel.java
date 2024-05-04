package com.frontend.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.Application;
import com.frontend.entity.ProductsReceiveData;
import com.frontend.utils.BackendResource;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.Nullable;


public class ProductsModel {


    static public ProductsReceiveData getAllProducts() {

        BackendResource<ProductsReceiveData> productsReceiveDataBackendResource = new BackendResource<>();

        ProductsReceiveData productsReceiveData = productsReceiveDataBackendResource.getRequest("/stocks/getAllStocks", ProductsReceiveData.class);

        if (productsReceiveData != null) {

            return productsReceiveData;

        } else {
            return new ProductsReceiveData();

        }

    }


    @Nullable
    static public ProductsReceiveData queryProducts(String type, String value) {


        try {
            // 创建HttpClient实例
//
            HttpClient httpClient = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(Application.appConfigEntity.getHttpClient().getHost() + "/stocks/queryStocks");
            uriBuilder.addParameter("type", type);
            uriBuilder.addParameter("value", value);


            HttpGet httpGet = new HttpGet(uriBuilder.build());

            httpGet.addHeader("token", Application.userLoginToken);

            HttpResponse response = httpClient.execute(httpGet);
            String responseBody = EntityUtils.toString(response.getEntity());

            // 发送请求并获取响应
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(responseBody.toString(), ProductsReceiveData.class);
            // 打印响应内容
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


}
