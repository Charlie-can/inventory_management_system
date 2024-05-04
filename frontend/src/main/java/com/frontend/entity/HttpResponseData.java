package com.frontend.entity;

import lombok.Data;

import java.util.HashMap;

@Data
public class HttpResponseData {

    private  Integer code;

    private  String message;

    private HashMap<String,String> data;
}