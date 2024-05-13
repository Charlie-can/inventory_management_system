package com.frontend.entity;

import lombok.Data;

@Data
public class StorageStock {

    private Integer stockId;

    private String storageTime;

    private Double storagePrice;

    private Integer storageVolume;

    private Integer storageEmployeeId;



}
