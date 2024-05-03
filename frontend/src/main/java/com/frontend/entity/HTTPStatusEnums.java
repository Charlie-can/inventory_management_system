package com.frontend.entity;

public enum HTTPStatusEnums {

    OK(200),
    Not_Connected_Server(419),
    Unknown_Error(600);


    private final Integer code;

    HTTPStatusEnums(Integer code) {
        this.code = code;

    }

    public Integer getCode() {
        return code;
    }

}
