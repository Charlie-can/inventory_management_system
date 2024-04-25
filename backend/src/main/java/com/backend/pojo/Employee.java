package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName employee
 */
@TableName(value ="employee")
@Data
public class Employee implements Serializable {
    @TableId
    private Integer id;

    private String name;

    private String sex;

    private Integer phone;

    private Date hiredate;

    private Date birthday;

    private static final long serialVersionUID = 1L;
}