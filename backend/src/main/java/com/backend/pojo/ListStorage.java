package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName list_storage
 */
@TableName(value ="list_storage")
@Data
public class ListStorage implements Serializable {
    @TableId
    private Integer id;

    private Integer stockId;

    private Date storageTime;

    private Double storagePrice;

    private Integer storageVolume;

    private Integer storageEmployeeId;

    private static final long serialVersionUID = 1L;
}