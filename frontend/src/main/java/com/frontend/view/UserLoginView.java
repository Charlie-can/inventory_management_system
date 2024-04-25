package com.frontend.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.entity.LoginReceiveData;
import com.frontend.entity.LoginSendData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserLoginView {

    private static LoginReceiveData loginReceiveData;
    static public LoginReceiveData isLogin(int id, String password) {

        try {
            // 创建HttpClient实例
            HttpClient httpClient = HttpClientBuilder.create().build();

            // 创建POST请求
            HttpPost request = new HttpPost("http://127.0.0.1:8080/user/login");

            // 添加请求头
            request.addHeader("Content-Type", "application/json");

            LoginSendData loginSendData = new LoginSendData();

            loginSendData.setId(id);
            loginSendData.setPassword(password);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(loginSendData);
            String JsBody = objectMapper.writeValueAsString(loginSendData);


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

             loginReceiveData = objectMapper.readValue(result.toString(), LoginReceiveData.class);

            System.out.println("Response: " + result);
            System.out.println("loginTest: " + loginReceiveData);





                return loginReceiveData;


            // 打印响应内容

        } catch (Exception e) {
            e.printStackTrace();
            return loginReceiveData;

        }

    }
}
