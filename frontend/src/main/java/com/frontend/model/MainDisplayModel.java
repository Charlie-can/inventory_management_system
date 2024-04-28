package com.frontend.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.Application;
import com.frontend.entity.UserInfoReceiveData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainDisplayModel {



   static public UserInfoReceiveData getUserInfo(){

        try {
            // 创建HttpClient实例
            HttpClient httpClient = HttpClientBuilder.create().build();

            // 创建GET请求
            HttpGet request = new HttpGet(Application.appConfigEntity.getHttpClient().getHost() +"/user/getUserInfo");

            request.addHeader("token",Application.userLoginToken);
            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(request);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();

            UserInfoReceiveData userInfoReceiveData = objectMapper.readValue(result.toString(),UserInfoReceiveData.class);
            return  userInfoReceiveData;
            // 打印响应内容
        } catch (Exception e) {
            e.printStackTrace();

            return  new UserInfoReceiveData();
        }



    }
}
