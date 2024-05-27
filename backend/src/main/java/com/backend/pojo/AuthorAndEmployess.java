package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorAndEmployess implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)

    private Integer id;

    private String password;

    private Boolean admin;

    private Boolean salesperson;

    private Boolean storageperson;

    private Boolean inventoryperson;

    private Boolean stockmanager;

    private Employee employee;

    public void setIdNull( ) {
        this.id = null;
    }
    private static final long serialVersionUID = 1L;

}
