package com.frontend.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.Application;
import com.frontend.entity.InventoryReceiveDate;
import com.frontend.entity.ProductsReceiveData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class InventoryModel {

    static public InventoryReceiveDate receiveDate(String date){
        try {
            // 创建HttpClient实例
//
            HttpClient httpClient = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(Application.appConfigEntity.getHttpClient().getHost() + "/inventory/getInventoryByDate");
            uriBuilder.addParameter("date", date);

            HttpGet httpGet = new HttpGet(uriBuilder.build());

            httpGet.addHeader("token", Application.userLoginToken);

            HttpResponse response = httpClient.execute(httpGet);
            String responseBody = EntityUtils.toString(response.getEntity());

            // 发送请求并获取响应
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(responseBody.toString(), InventoryReceiveDate.class);
            // 打印响应内容
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }















}
