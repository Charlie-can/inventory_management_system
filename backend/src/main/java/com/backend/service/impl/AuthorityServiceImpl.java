package com.backend.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.backend.mapper.AuthorityAndEmployeeMapper;
import com.backend.pojo.AuthorAndEmployess;
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
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority>
        implements AuthorityService {

    @Resource
    private AuthorityMapper authorityMapper;

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

            String token = jwtHelper.createToken(Long.valueOf(dataAuthority.getId()));

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
        authorAndEmployess.setPassword(null);

        Map<String, AuthorAndEmployess> authorAndEmployessMap = new HashMap<>();

        authorAndEmployessMap.put("UserInfo", authorAndEmployess);

        return Result.ok(authorAndEmployessMap);
    }
}




