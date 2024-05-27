package com.backend.mapper;

import com.backend.pojo.AuthorAndEmployess;

import java.util.ArrayList;


public interface AuthorityAndEmployeeMapper {
    AuthorAndEmployess SelectIdInAuthorityAndEmployee(Integer id);

    ArrayList<AuthorAndEmployess> SelectAllAuthorityAndEmployee();

    ArrayList<AuthorAndEmployess> SelectByIdAuthorityAndEmployee(Integer id);

    ArrayList<AuthorAndEmployess> SelectByNameAuthorityAndEmployee(String name);

    ArrayList<AuthorAndEmployess> SelectBySexAuthorityAndEmployee(Boolean sex);

    ArrayList<AuthorAndEmployess> SelectByPhoneAuthorityAndEmployee(String phone);

    ArrayList<AuthorAndEmployess> SelectByAdminAuthorityAndEmployee(Boolean bool);

    ArrayList<AuthorAndEmployess> SelectBySalesPersonAuthorityAndEmployee(Boolean bool);

    ArrayList<AuthorAndEmployess> SelectByStoragePersonAuthorityAndEmployee(Boolean bool);

    ArrayList<AuthorAndEmployess> SelectByInventoryPersonAuthorityAndEmployee(Boolean bool);

      ArrayList<AuthorAndEmployess> SelectByStockManagerPersonAuthorityAndEmployee(Boolean bool);


}




