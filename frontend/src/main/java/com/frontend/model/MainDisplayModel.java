package com.frontend.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.Application;
import com.frontend.entity.UserInfoReceiveData;
import com.frontend.utils.GetResource;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainDisplayModel {


    static public UserInfoReceiveData getUserInfo() {


        GetResource<UserInfoReceiveData> userInfoReceiveDataGetResource = new GetResource<>();
        UserInfoReceiveData userInfoReceiveData = userInfoReceiveDataGetResource.getRequest("/user/getUserInfo", UserInfoReceiveData.class);
        if (userInfoReceiveData != null) {
            return userInfoReceiveData;
        } else {

            return new UserInfoReceiveData();
        }


    }
}
