package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName list_inventory
 */
@TableName(value ="list_inventory")
@Data
public class ListInventory implements Serializable {
    @TableId
    private Integer id;

    private Integer stockId;

    private Date date;

    private Integer number;

    private Double totalPrice;

    private Integer employeeId;

    private static final long serialVersionUID = 1L;
}