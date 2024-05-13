package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @TableName list_storage
 */
@TableName(value ="list_storage")
@Data
public class ListStorage implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)

    private Integer id;

    private Integer stockId;
    
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime storageTime;

    private Double storagePrice;

    private Integer storageVolume;

    private Integer storageEmployeeId;

    private static final long serialVersionUID = 1L;
}