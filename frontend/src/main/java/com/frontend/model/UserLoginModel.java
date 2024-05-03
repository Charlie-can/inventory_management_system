package com.frontend.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.Application;
import com.frontend.entity.HTTPStatusEnums;
import com.frontend.entity.HttpResponseData;
import com.frontend.entity.LoginSendData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserLoginModel {

    static public HttpResponseData isLogin(int id, String password) {

        try {
            // 创建HttpClient实例
            HttpClient httpClient = HttpClientBuilder.create().build();

            // 创建POST请求
            HttpPost request = new HttpPost(Application.appConfigEntity.getHttpClient().getHost() + "/user/login");

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


            HttpResponse response = httpClient.execute(request);

            String responseBody = EntityUtils.toString(response.getEntity());

            return objectMapper.readValue(responseBody, HttpResponseData.class);


            // 打印响应内容

        } catch (HttpHostConnectException e) {
            HttpResponseData HttpResponseData1 = new HttpResponseData();
            HttpResponseData1.setCode(HTTPStatusEnums.Not_Connected_Server.getCode());
            return HttpResponseData1;

        } catch (Exception e) {
            e.printStackTrace();
            HttpResponseData HttpResponseData1 = new HttpResponseData();
            HttpResponseData1.setCode(HTTPStatusEnums.Unknown_Error.getCode());

            return HttpResponseData1;

        }

    }
}
