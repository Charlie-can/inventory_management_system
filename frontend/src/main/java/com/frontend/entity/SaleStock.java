package com.frontend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaleStock {

    private Integer stockId;

    private String saleTime;

    private Double salePrice;

    private Integer saleVolume;

    private Integer saleEmployeeId;

}
