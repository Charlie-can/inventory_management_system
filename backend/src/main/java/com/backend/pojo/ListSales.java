package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName list_sales
 */
@TableName(value ="list_sales")
@Data
public class ListSales implements Serializable {
    @TableId
    private Integer id;

    private Integer stockId;

    private Date saleTime;

    private Double salePrice;

    private Integer saleVolume;

    private Integer saleEmployeeId;

    private static final long serialVersionUID = 1L;
}