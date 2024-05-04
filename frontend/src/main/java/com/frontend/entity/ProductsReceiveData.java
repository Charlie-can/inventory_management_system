package com.frontend.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ProductsReceiveData {

    private Integer code;

    private String message;

    private Data data;


    @lombok.Data
    static  public class Data{
        private ArrayList<ProductsList> stockList;
    }

}
