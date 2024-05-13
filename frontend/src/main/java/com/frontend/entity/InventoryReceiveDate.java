package com.frontend.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class InventoryReceiveDate {

    private Integer code;

    private String message;

    private Data data;

    @lombok.Data
    static  public class Data{
        private ArrayList<InventoryStock> inventoryList;
    }
}
