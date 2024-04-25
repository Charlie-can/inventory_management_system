package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName authority
 */
@TableName(value ="authority")
@Data
public class Authority implements Serializable {
    @TableId
    private Integer id;

    private String password;

    private Integer admin;

    private Integer salesperson;

    private Integer storageperson;

    private Integer inventoryperson;

    private static final long serialVersionUID = 1L;
}