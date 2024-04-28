package com.frontend.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.Application;
import com.frontend.entity.HTTPStatusEnums;
import com.frontend.entity.LoginReceiveData;
import com.frontend.entity.LoginSendData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserLoginModel {

    static public LoginReceiveData isLogin(int id, String password) {

        try {
            // 创建HttpClient实例
            HttpClient httpClient = HttpClientBuilder.create().build();

            // 创建POST请求
            HttpPost request = new HttpPost(Application.appConfigEntity.getHttpClient().getHost()+"/user/login");

            // 添加请求头
            request.addHeader("Content-Type", "application/json");

            LoginSendData loginSendData = new LoginSendData();
            loginSendData.setId(id);
            loginSendData.setPassword(password);

            ObjectMapper objectMapper = new ObjectMapper();

            //实体类信息转Json
            String JsBody = objectMapper.writeValueAsString(loginSendData);

            //接受请求
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

            LoginReceiveData loginReceiveData = objectMapper.readValue(result.toString(), LoginReceiveData.class);

//            System.out.println("Response: " + result);
//            System.out.println("loginTest: " + loginReceiveData);


                return loginReceiveData;


            // 打印响应内容

        } catch (HttpHostConnectException e) {
//            e.printStackTrace();
//            System.out.println("网络错误");
            LoginReceiveData loginReceiveData1 = new LoginReceiveData();
            loginReceiveData1.setCode(HTTPStatusEnums.Not_Connected_Server.getCode());
            return loginReceiveData1;

        } catch (Exception e){
            e.printStackTrace();
            LoginReceiveData loginReceiveData1 =new LoginReceiveData();
            loginReceiveData1.setCode(HTTPStatusEnums.Unknown_Error.getCode());

            return loginReceiveData1;

        }

    }
}
