package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorAndEmployess implements Serializable {
    @TableId
    private Integer id;

    private String password;

    private Integer admin;

    private Integer salesperson;

    private Integer storageperson;

    private Integer inventoryperson;

    private Employee employee;

    private static final long serialVersionUID = 1L;


}
