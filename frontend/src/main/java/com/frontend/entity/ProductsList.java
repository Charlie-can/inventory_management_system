package com.frontend.entity;


import lombok.Data;


/**
 * @TableName list_stock
 */
@Data
public class ProductsList {

    private Integer id;

    private String name;

    private Integer reserveNow;

    private Integer reserveMin;

    private Double price;

    private String vendor;

    private String introduction;

    public ProductsList(ProductsListSelected productsListSelected) {
        this.id = productsListSelected.getId();
        this.name = productsListSelected.getName();
        this.reserveNow = productsListSelected.getReserveNow();
        this.reserveMin = productsListSelected.getReserveMin();
        this.price = productsListSelected.getPrice();
        this.vendor = productsListSelected.getVendor();
        this.introduction = productsListSelected.getIntroduction();
    }

    public ProductsList() {
    }

}