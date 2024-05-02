package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName appointment
 */
@TableName(value ="appointment")
@Data
public class Appointment implements Serializable {

    @TableId
    private Integer id;

    private String appoint;

    private Integer employeeId;

    @Serial
    private static final long serialVersionUID = 1L;
}