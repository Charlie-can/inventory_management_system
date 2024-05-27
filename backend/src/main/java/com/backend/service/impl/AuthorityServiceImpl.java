package com.backend.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.backend.mapper.AuthorityAndEmployeeMapper;
import com.backend.mapper.EmployeeMapper;
import com.backend.pojo.AuthorAndEmployess;
import com.backend.pojo.Employee;
import com.backend.utils.JwtHelper;
import com.backend.utils.MD5Util;
import com.backend.utils.Result;
import com.backend.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.pojo.Authority;
import com.backend.service.AuthorityService;
import com.backend.mapper.AuthorityMapper;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;


@Service
@Log4j2
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

    @Resource
    private AuthorityMapper authorityMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private AuthorityAndEmployeeMapper authorityAndEmployeeMapper;


    @Resource
    private JwtHelper jwtHelper;

    @Override
    public Result login(Authority authority) {

//        LambdaQueryWrapper<Authority> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//
//        lambdaQueryWrapper.eq(Authority::getId, authority.getId());
//        Authority dataAuthority = authorityMapper.selectOne(lambdaQueryWrapper);


        QueryWrapper<Authority> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("id", authority.getId());
        Authority dataAuthority = authorityMapper.selectOne(queryWrapper);

        if (dataAuthority == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }


        if (!StringUtils.isEmpty(authority.getPassword()) && MD5Util.encrypt(authority.getPassword()).equals(dataAuthority.getPassword())) {


            HashMap<String, Object> claimsMap = new HashMap<>();

            claimsMap.put("userId", dataAuthority.getId());
            claimsMap.put("isAdmin", dataAuthority.getAdmin());
            claimsMap.put("isSalesperson", dataAuthority.getSalesperson());
            claimsMap.put("isStoragePerson", dataAuthority.getStorageperson());
            claimsMap.put("isInventoryPerson", dataAuthority.getInventoryperson());
            claimsMap.put("isStockManager", dataAuthority.getStockmanager());

            String token = jwtHelper.createToken(claimsMap);

            Map<String, String> tokenMap = new HashMap<>();

            tokenMap.put("token", token);

            return Result.ok(tokenMap);

        } else {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);


        }

    }

    @Override
    public Result getUserInfo(String token) {

        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        int userId = Math.toIntExact(jwtHelper.getUserId(token));

        AuthorAndEmployess authorAndEmployess = authorityAndEmployeeMapper.SelectIdInAuthorityAndEmployee(userId);

        System.out.println(authorAndEmployess);

        authorAndEmployess.setPassword(null);

        Map<String, AuthorAndEmployess> authorAndEmployessMap = new HashMap<>();

        authorAndEmployessMap.put("UserInfo", authorAndEmployess);


        return Result.ok(authorAndEmployessMap);
    }

    @Override
    public Result getUserInfoList() {

        ArrayList<AuthorAndEmployess> authorAndEmployessArrayList = authorityAndEmployeeMapper.SelectAllAuthorityAndEmployee();


        Map<String, ArrayList<AuthorAndEmployess>> authorityMap = new HashMap<>();

        authorityMap.put("UserInfoList", authorAndEmployessArrayList);

        return Result.ok(authorityMap);
    }

    @Override
    public Result updateUserInfo(AuthorAndEmployess authorAndEmployess) {
        Map<String, String> resMap = new HashMap<>();

        if (authorAndEmployess.getPassword().equals("***")) {
            authorAndEmployess.setPassword(null);
        } else {
            authorAndEmployess.setPassword(MD5Util.encrypt(authorAndEmployess.getPassword()));
        }

        if (!Objects.equals(authorAndEmployess.getId(), authorAndEmployess.getEmployee().getId())) {
            resMap.put("message", "Update Error");
            return Result.build(resMap, ResultCodeEnum.UPDATE_ERROR);
        }

        try {
            Authority authority = new Authority(authorAndEmployess);
            Employee employee = new Employee(authorAndEmployess);

            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", authorAndEmployess.getId());

            int updated = authorityMapper.update(authority, queryWrapper);
            employeeMapper.update(employee, queryWrapper);

            if(updated==0){
                resMap.put("message", "Update Empty");
                return Result.build(resMap, ResultCodeEnum.UPDATE_ERROR);



            }
            resMap.put("message", "Update successful");

            return Result.ok(resMap);

        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("message", "Update Error");
            return Result.build(resMap, ResultCodeEnum.UPDATE_ERROR);
        }

    }

    @Override
    public Result insertUserInfo(AuthorAndEmployess authorAndEmployess) {
        Map<String, String> resMap = new HashMap<>();

        System.out.println(authorAndEmployess);
        for (Field declaredField : authorAndEmployess.getClass().getDeclaredFields()) {

            declaredField.setAccessible(true);
            try {
                if (declaredField.getName().equals("id")) continue;
                if (declaredField.get(authorAndEmployess) == null) {
                    resMap.put("message", "Insert Parameter Error");
                    return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                resMap.put("message", "Insert Parameter Error");
                return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
            }

        }
        for (Field declaredField : authorAndEmployess.getEmployee().getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);

            try {
                if (declaredField.getName().equals("id")) continue;
                if (declaredField.get(authorAndEmployess.getEmployee()) == null) {
                    resMap.put("message", "Insert Parameter Error");
                    return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                resMap.put("message", "Insert Parameter Error");
                return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
            }

        }
        if (authorAndEmployess.getPassword().equals("") || !Objects.equals(authorAndEmployess.getId(), authorAndEmployess.getEmployee().getId()) || authorAndEmployess.getEmployee().getName().equals("") || authorAndEmployess.getEmployee().getPhone().length() != 11 || authorAndEmployess.getEmployee().getHiredate().isAfter(LocalDate.now()) || authorAndEmployess.getEmployee().getBirthday().isAfter(LocalDate.now())) {
            resMap.put("message", "Insert Parameter Error");
            return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
        }

        try {
            Long.parseLong(authorAndEmployess.getEmployee().getPhone());
        } catch (Exception e) {
            resMap.put("message", "Insert Parameter Error");
            return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
        }


        try {
            authorAndEmployess.setPassword(MD5Util.encrypt(authorAndEmployess.getPassword()));
            authorAndEmployess.setIdNull();
            authorAndEmployess.getEmployee().setIdNull();

            Authority authority = new Authority(authorAndEmployess);
            Employee employee = new Employee(authorAndEmployess);

            authorityMapper.insert(authority);
            employeeMapper.insert(employee);

        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("message", "Insert Parameter Error");
            return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
        }

        resMap.put("message", "Insert successful");
        return Result.ok(resMap);
    }

    @Override
    public Result deleteUser(Map<String, List<Integer>> idList) {

        int deleteAuthorityNumber = authorityMapper.deleteBatchIds(idList.get("idList"));
        int deleteEmployeeNumber = employeeMapper.deleteBatchIds(idList.get("idList"));

        Map<String, String> resMap = new HashMap<>();

        if (deleteAuthorityNumber == 0) {
            resMap.put("message", "not delete stock");
            return Result.build(resMap, ResultCodeEnum.DELETE_EMPTY);
        } else if (deleteEmployeeNumber != deleteAuthorityNumber) {
            resMap.put("message", "not all are delete");
            return Result.build(resMap, ResultCodeEnum.DELETE_NOT_ALL);
        } else if (deleteAuthorityNumber < idList.get("idList").size()) {
            resMap.put("message", "not all are delete");
            return Result.build(resMap, ResultCodeEnum.DELETE_NOT_ALL);
        } else {
            resMap.put("message", "Delete Successful");
            return Result.ok(resMap);
        }

    }

    @Override
    public Result queryUserInfo(String type, String value) {
        ArrayList<AuthorAndEmployess> authorAndEmployessArrayList = null;

        if (type.equals("id")) {
            authorAndEmployessArrayList = authorityAndEmployeeMapper.SelectByIdAuthorityAndEmployee(Integer.parseInt(value));
        } else if (type.equals("name")) {
            authorAndEmployessArrayList = authorityAndEmployeeMapper.SelectByNameAuthorityAndEmployee(value);
        } else if (type.equals("sex")) {
            authorAndEmployessArrayList = authorityAndEmployeeMapper.SelectBySexAuthorityAndEmployee(Boolean.parseBoolean(value));
        } else if (type.equals("phone")) {
            authorAndEmployessArrayList = authorityAndEmployeeMapper.SelectByPhoneAuthorityAndEmployee(value);
        } else if (type.equals("admin")) {
            authorAndEmployessArrayList = authorityAndEmployeeMapper.SelectByAdminAuthorityAndEmployee(Boolean.parseBoolean(value));
        } else if (type.equals("salesperson")) {
            authorAndEmployessArrayList = authorityAndEmployeeMapper.SelectBySalesPersonAuthorityAndEmployee(Boolean.parseBoolean(value));
        } else if (type.equals("storageperson")) {
            authorAndEmployessArrayList = authorityAndEmployeeMapper.SelectByStoragePersonAuthorityAndEmployee(Boolean.parseBoolean(value));
        } else if (type.equals("inventoryperson")) {
            authorAndEmployessArrayList = authorityAndEmployeeMapper.SelectByInventoryPersonAuthorityAndEmployee(Boolean.parseBoolean(value));
        } else if (type.equals("stockmanager")) {
            authorAndEmployessArrayList = authorityAndEmployeeMapper.SelectByStockManagerPersonAuthorityAndEmployee(Boolean.parseBoolean(value));
        }

        Map<String, ArrayList<AuthorAndEmployess>> authorityMap = new HashMap<>();

        authorityMap.put("UserInfoList", authorAndEmployessArrayList);

        return Result.ok(authorityMap);
    }

    @Override
    public Result updateUserInfoSelf(String token, AuthorAndEmployess authorAndEmployess) {
        Map<String, String> resMap = new HashMap<>();


        if(jwtHelper.getUserId(token)!= Long.parseLong(String.valueOf(authorAndEmployess.getId()))){
            resMap.put("message", "Update Error");
            return Result.build(resMap, ResultCodeEnum.UPDATE_ERROR);

        }


        if (authorAndEmployess.getPassword().equals("***")) {
            authorAndEmployess.setPassword(null);
        } else {
            authorAndEmployess.setPassword(MD5Util.encrypt(authorAndEmployess.getPassword()));
        }

        if (!Objects.equals(authorAndEmployess.getId(), authorAndEmployess.getEmployee().getId())) {
            resMap.put("message", "Update Error");
            return Result.build(resMap, ResultCodeEnum.UPDATE_ERROR);
        }

        try {
            Authority authority = new Authority(authorAndEmployess);
            Employee employee = new Employee(authorAndEmployess);

            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", authorAndEmployess.getId());

            int updated = authorityMapper.update(authority, queryWrapper);
            employeeMapper.update(employee, queryWrapper);

            if(updated==0){
                resMap.put("message", "Update Empty");
                return Result.build(resMap, ResultCodeEnum.UPDATE_ERROR);
            }
            resMap.put("message", "Update successful");

            return Result.ok(resMap);

        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("message", "Update Error");
            return Result.build(resMap, ResultCodeEnum.UPDATE_ERROR);
        }

    }
}




