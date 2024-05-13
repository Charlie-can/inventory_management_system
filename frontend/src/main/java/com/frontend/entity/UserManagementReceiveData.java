package com.frontend.entity;


import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
@Data
public class UserManagementReceiveData {

    private  Integer code;

    private  String message;

    private ArrayList<UserInfoList> data;


    @Data
    public static class UserInfoList{

        private Integer id;

        private String password;

        private Boolean admin;

        private Boolean salesperson;

        private Boolean storageperson;

        private Boolean inventoryperson;

        private Boolean stockmanager;

        private Employee employee;

        @Data
        public static class Employee  {

            private Integer id;

            private String name;

            private String sex;

            private String  phone;

            private LocalDate hiredate;

            private LocalDate birthday;
        }

    }

}
