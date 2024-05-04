package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;


@TableName(value ="authority")
@Data
public class Authority implements Serializable {
    @TableId
    private Integer id;

    private String password;

    private Boolean admin;

    private Boolean salesperson;

    private Boolean storageperson;

    private Boolean inventoryperson;

    private Boolean stockmanager;


    @Serial
    private static final long serialVersionUID = 1L;
}