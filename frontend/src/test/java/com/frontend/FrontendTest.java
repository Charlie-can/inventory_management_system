package com.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class FrontendTest {




//    @Test
    public void GetTest() throws Exception {

        try {
            // 创建HttpClient实例
            HttpClient httpClient = HttpClientBuilder.create().build();

            // 创建GET请求
            HttpGet request = new HttpGet("http://127.0.0.1:8080/test");

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(request);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // 打印响应内容
            System.out.println("Response: " + result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


@Test
    public void PostTest(){

    try {
        // 创建HttpClient实例
        HttpClient httpClient = HttpClientBuilder.create().build();

        // 创建POST请求
        HttpPost request = new HttpPost("http://127.0.0.1:8080/user/login");

        // 添加请求头
        request.addHeader("Content-Type", "application/json");

        // 添加请求体（JSON数据）
//        String jsonBody = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";

        LoginTest loginTest = new LoginTest();

        loginTest.setId(1001);
        loginTest.setPassword("123456");

        ObjectMapper objectMapper =new ObjectMapper();

        String JsBody = objectMapper.writeValueAsString(loginTest);


        request.setEntity(new StringEntity(JsBody));

        // 发送请求并获取响应
        HttpResponse response = httpClient.execute(request);

        // 读取响应内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        LoginDataTest loginDataTest = objectMapper.readValue(result.toString(), LoginDataTest.class);


        // 打印响应内容
        System.out.println("Response: " + result.toString());
        System.out.println("loginTest: " + loginDataTest);
    } catch (Exception e) {
        e.printStackTrace();
    }




}
}
