package com.frontend.entity;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class InventoryStock implements Serializable {

    private Integer id;

    private Integer stockId;

    private String stockName;

    private String date;

    private Double storagePrice;

    private Double salePrice;

    private Double profitPrice;

    private Integer remainingCount;

    private Integer replenishCount;
}