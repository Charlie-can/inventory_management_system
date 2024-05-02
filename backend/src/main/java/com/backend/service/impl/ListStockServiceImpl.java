package com.backend.service.impl;

import com.backend.utils.JwtHelper;
import com.backend.utils.Result;
import com.backend.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.pojo.ListStock;
import com.backend.service.ListStockService;
import com.backend.mapper.ListStockMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author charlie
 * @description 针对表【list_stock】的数据库操作Service实现
 * @createDate 2024-04-23 21:40:43
 */
@Service
public class ListStockServiceImpl extends ServiceImpl<ListStockMapper, ListStock>
        implements ListStockService {


    @Resource
    private JwtHelper jwtHelper;

    @Resource
    private ListStockMapper listStockMapper;

    @Override
    public Result getAllStock(String token) {

        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        List<ListStock> listStockArrayList = listStockMapper.selectList(null);

        Map<String, List> listStockMap = new HashMap<>();
        listStockMap.put("stockList", listStockArrayList);


        return Result.ok(listStockMap);
    }

    @Override
    public Result queryStocks(String token, String type, String value) {


        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }


        if (!(Objects.equals(type, "id") ||
                Objects.equals(type, "name") ||
                Objects.equals(type, "reserve_now") ||
                Objects.equals(type, "reserve_min") ||
                Objects.equals(type, "price") ||
                Objects.equals(type, "vendor") ||
                Objects.equals(type, "Introduction"))) {
            return Result.build(null, ResultCodeEnum.USERINPUT_ERROR);
        }


        QueryWrapper<ListStock> objectQueryWrapper = new QueryWrapper<>();

        objectQueryWrapper.eq(type, value);

        List<ListStock> listStocks = listStockMapper.selectList(objectQueryWrapper);

        System.out.println(listStocks);
        Map<String, List> stringListStockMap = new HashMap<>();

        stringListStockMap.put("stockList", listStocks);


        return Result.ok(stringListStockMap);
    }
}




