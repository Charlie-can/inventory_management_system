package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @TableName list_inventory
 */
@TableName(value ="list_inventory")
@Data
public class ListInventory implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer stockId;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    private Double storagePrice;

    private Double salePrice;

    private Double profitPrice;

    private Integer remainingCount;

    private Integer replenishCount;

    private static final long serialVersionUID = 1L;
}