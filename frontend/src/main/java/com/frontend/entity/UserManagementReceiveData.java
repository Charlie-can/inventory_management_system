package com.frontend.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class UserManagementReceiveData {

    private Integer code;

    private String message;

    private Data data;

    @lombok.Data
    public static class Data {
        @JsonProperty("UserInfoList")
        private ArrayList<UserInfoList> UserInfoList;

        @lombok.Data
        public static class UserInfoList {

            private Integer id;

            private String password;

            private Boolean admin;

            private Boolean salesperson;

            private Boolean storageperson;

            private Boolean inventoryperson;

            private Boolean stockmanager;

            private Employee employee;

            public Employee getEmployee() {
                return employee;
            }

            public void setEmployee(Employee employee) {
                this.employee = employee;
            }

            @lombok.Data
            public static class Employee {

                private Integer id;

                private String name;

                private String sex;

                private String phone;

//                @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC+8")
                private LocalDate hiredate;

//                @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC+8")
                private LocalDate birthday;
            }

        }

    }



}
