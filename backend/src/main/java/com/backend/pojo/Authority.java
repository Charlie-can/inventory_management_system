package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;


@TableName(value ="authority")
@Data
public class Authority implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)

    private Integer id;

    private String password;

    private Boolean admin;

    private Boolean salesperson;

    private Boolean storageperson;

    private Boolean inventoryperson;

    private Boolean stockmanager;

    public Authority(Integer id, String password, Boolean admin, Boolean salesperson, Boolean storageperson, Boolean inventoryperson, Boolean stockmanager) {
        this.id = id;
        this.password = password;
        this.admin = admin;
        this.salesperson = salesperson;
        this.storageperson = storageperson;
        this.inventoryperson = inventoryperson;
        this.stockmanager = stockmanager;
    }

    public Authority(AuthorAndEmployess authorAndEmployess){
     this.id =authorAndEmployess.getId();
     this.password = authorAndEmployess.getPassword();
     this.admin = authorAndEmployess.getAdmin();
     this.salesperson = authorAndEmployess.getSalesperson();
     this.storageperson = authorAndEmployess.getStorageperson();
     this.inventoryperson = authorAndEmployess.getInventoryperson();
     this.stockmanager = authorAndEmployess.getStockmanager();
    }

    @Serial
    private static final long serialVersionUID = 1L;
}