package com.frontend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfoReceiveData {

    private Integer code;

    private String message;


    private Data data;


    @lombok.Data
    static public class Data {
        @JsonProperty("UserInfo")

        private UserInfo userInfo;


        @lombok.Data
        static public class UserInfo {
            private Integer id;

            private String password;

            private Boolean admin;

            private Boolean salesperson;

            private Boolean storageperson;

            private Boolean inventoryperson;

            private Boolean stockmanager;

            private EmployeeInfoReceiveData employee;

        }

    }


}
