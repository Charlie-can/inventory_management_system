package com.frontend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class InventoryReceiveDateDate {

    private Integer code;

    private String message;

    private Data data;



    @lombok.Data
    static  public class Data{
        private ArrayList<String> inventoryDateList;
    }



}
