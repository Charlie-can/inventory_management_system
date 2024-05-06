package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@TableName(value ="employee")
@Data
public class Employee implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)

    private Integer id;

    private String name;

    private String sex;

    private String  phone;

    private Date hiredate;

    private Date birthday;

    @Serial
    private static final long serialVersionUID = 1L;
}