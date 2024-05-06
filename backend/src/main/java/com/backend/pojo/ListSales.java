package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @TableName list_sales
 */
@TableName(value = "list_sales")
@Data
public class ListSales implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer stockId;

        @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleTime;

    private Double salePrice;

    private Integer saleVolume;

    private Integer saleEmployeeId;

    private static final long serialVersionUID = 1L;
}