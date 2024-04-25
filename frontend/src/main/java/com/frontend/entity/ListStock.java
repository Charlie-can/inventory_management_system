package com.frontend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @TableName list_stock
 */
@Data
@AllArgsConstructor
public class ListStock   {

    private Integer id;

    private String name;

    private Integer reserveNow;

    private Integer reserveMin;

    private Double price;

    private String vendor;

    private String introduction;

}