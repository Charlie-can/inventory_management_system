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

            private Integer admin;

            private Integer salesperson;

            private Integer storageperson;

            private Integer inventoryperson;

            private EmployeeInfoReceiveData employee;

        }

    }


}
