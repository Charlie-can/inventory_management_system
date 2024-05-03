package com.frontend.model;

import com.frontend.entity.UserInfoReceiveData;
import com.frontend.utils.BackendResource;

public class MainDisplayModel {


    static public UserInfoReceiveData getUserInfo() {


        BackendResource<UserInfoReceiveData> userInfoReceiveDataBackendResource = new BackendResource<>();
        UserInfoReceiveData userInfoReceiveData = userInfoReceiveDataBackendResource.getRequest("/user/getUserInfo", UserInfoReceiveData.class);
        if (userInfoReceiveData != null) {
            return userInfoReceiveData;
        } else {

            return new UserInfoReceiveData();
        }


    }
}
