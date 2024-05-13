package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;


@TableName(value ="list_inventory")
@Data
public class ListInventoryName implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer stockId;

    private String stockName;


    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "HH:mm:ss", timezone = "UTC+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime date;

    private Double storagePrice;

    private Double salePrice;

    private Double profitPrice;

    private Integer remainingCount;

    private Integer replenishCount;

    private static final long serialVersionUID = 1L;
}