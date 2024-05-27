package com.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;


@TableName(value = "employee")

@Setter
@Getter
public class Employee implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String sex;

    private String phone;

    private LocalDate hiredate;

    private LocalDate birthday;


    //
    public Employee(AuthorAndEmployess authorAndEmployess) {
        this.id = authorAndEmployess.getEmployee().getId();
        this.name = authorAndEmployess.getEmployee().getName();
        this.sex = authorAndEmployess.getEmployee().getSex();
        this.phone = authorAndEmployess.getEmployee().getPhone();
        this.hiredate = authorAndEmployess.getEmployee().getHiredate();
        this.birthday = authorAndEmployess.getEmployee().getBirthday();
    }

    public void setIdNull( ) {
        this.id = null;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", hiredate=" + hiredate +
                ", birthday=" + birthday +
                '}';
    }

    @Serial
    private static final long serialVersionUID = 1L;
}