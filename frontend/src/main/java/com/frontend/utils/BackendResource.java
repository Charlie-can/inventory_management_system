package com.frontend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.frontend.Application;
import com.frontend.entity.HttpResponseData;
import com.frontend.entity.ProductsReceiveData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;

public class BackendResource<T> {


    public T getRequest(String requestPath, Class<T> classValue) {

        try {
            // 创建HttpClient实例
            HttpClient httpClient = HttpClientBuilder.create().build();

            // 创建GET请求
            HttpGet request = new HttpGet(Application.appConfigEntity.getHttpClient().getHost() + requestPath);

            request.addHeader("token", Application.userLoginToken);
            // 发送请求并获取响应

            HttpResponse response = httpClient.execute(request);


            String responseBody = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            System.out.println(responseBody);
            return objectMapper.readValue(responseBody, classValue);
            // 打印响应内容
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }



    public HttpResponseData postRequest(String requestPath, T tObjects) {
        try {


            HttpClient httpClient = HttpClientBuilder.create().build();

            // 创建POST请求
            HttpPost request = new HttpPost(Application.appConfigEntity.getHttpClient().getHost() + requestPath);

            // 添加请求头
            request.addHeader("Content-Type", "application/json");

            request.addHeader("token", Application.userLoginToken);


            ObjectMapper objectMapper = new ObjectMapper();

            //实体类信息转Json
            String JsBody = objectMapper.writeValueAsString(tObjects);

            StringEntity stringEntity = new StringEntity(JsBody, StandardCharsets.UTF_8);

            stringEntity.setContentEncoding("UTF_8");
            //接受请求
            request.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(request);

            String responseBody = EntityUtils.toString(response.getEntity());

            System.out.println(JsBody);
            try {
                return objectMapper.readValue(responseBody, HttpResponseData.class);
            } catch (Exception e) {

                e.printStackTrace();
                System.out.println(responseBody);
                return null;
            }

        } catch (Exception e) {


            e.printStackTrace();
        }

        return null;

    }

     public T queryTableList(String requestPath,  Class<T> classValue,String type, String value) {


        try {
            // 创建HttpClient实例
//
            HttpClient httpClient = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(Application.appConfigEntity.getHttpClient().getHost() + requestPath);
            uriBuilder.addParameter("type", type);
            uriBuilder.addParameter("value", value);


            HttpGet httpGet = new HttpGet(uriBuilder.build());

            httpGet.addHeader("token", Application.userLoginToken);

            HttpResponse response = httpClient.execute(httpGet);
            String responseBody = EntityUtils.toString(response.getEntity());

            // 发送请求并获取响应
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            System.out.println(type);
            System.out.println(value);
            System.out.println(responseBody);
            return objectMapper.readValue(responseBody.toString(),classValue);
            // 打印响应内容
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}
