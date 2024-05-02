package com.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;


@TableName(value ="list_stock")
@Data
public class ListStock implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer reserveNow;

    private Integer reserveMin;

    private Double price;

    private String vendor;

    private String introduction;

    @Serial
    private static final long serialVersionUID = 1L;


}