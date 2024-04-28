package com.frontend.test;

import lombok.Data;

import java.util.HashMap;

@Data
public class LoginDataTest {

    private  Integer code;

    private  String message;

    private HashMap<String,String> data;
}
