package com.frontend.entity;

import lombok.Data;

@Data
public class UserInfoList {

    private Integer id;

    private String password;

    private Boolean admin;

    private Boolean salesperson;

    private Boolean storageperson;

    private Boolean inventoryperson;

    private Boolean stockmanager;

    private Employee employee;


    @Data
    public static class Employee{
        private Integer id;
        private String name;
        private Integer sex;
        private String phone;
        private String hiredate;
        private String birthday;



    }


    public UserInfoList(){}

    public UserInfoList( String password, Boolean admin, Boolean salesperson, Boolean storageperson, Boolean inventoryperson, Boolean stockmanager, String name, Integer sex, String phone, String hireday, String birthday) {
        this.password = password;
        this.admin = admin;
        this.salesperson = salesperson;
        this.storageperson = storageperson;
        this.inventoryperson = inventoryperson;
        this.stockmanager = stockmanager;
        this.employee = new Employee();
        this.employee.name = name;
        this.employee.sex = sex;
        this.employee.phone = phone;
        this.employee.hiredate = hireday;
        this.employee.birthday = birthday;
    }

    public UserInfoList(UserInfoListSelected userInfoListSelected){
        this.id = userInfoListSelected.getId();
        this.password = userInfoListSelected.getPassword();
        this.admin = userInfoListSelected.isAdmin();
        this.salesperson = userInfoListSelected.isSalesperson();
        this.storageperson = userInfoListSelected.isStorageperson();
        this.inventoryperson = userInfoListSelected.isInventoryperson();
        this.stockmanager = userInfoListSelected.isStockmanager();
        this.employee = new Employee();
        this.employee.id = userInfoListSelected.getId();
        this.employee.name = userInfoListSelected.getEmployee().getName();
        this.employee.sex = Integer.parseInt(userInfoListSelected.getEmployee().getSex());
        this.employee.phone = userInfoListSelected.getEmployee().getPhone();
        this.employee.hiredate = userInfoListSelected.getEmployee().getHiredate();
        this.employee.birthday = userInfoListSelected.getEmployee().getBirthday();
    }

}
