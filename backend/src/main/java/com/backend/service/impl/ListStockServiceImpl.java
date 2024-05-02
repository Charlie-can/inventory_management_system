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


        if (!Arrays.asList("id", "name", "reserve_now", "reserve_min", "price", "vendor", "Introduction").contains(type)) {
            return Result.build(null, ResultCodeEnum.USERINPUT_ERROR);
        }


        QueryWrapper<ListStock> objectQueryWrapper = new QueryWrapper<>();

        objectQueryWrapper.eq(type, value);

        List<ListStock> listStocks = listStockMapper.selectList(objectQueryWrapper);

        Map<String, List> stringListStockMap = new HashMap<>();

        stringListStockMap.put("stockList", listStocks);


        return Result.ok(stringListStockMap);
    }

    @Override
    public Result deleteStocks(String token, Map<String, List<Integer>> idList) {

        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        int deleteNumber = listStockMapper.deleteBatchIds(idList.get("idList"));

        if(deleteNumber==0){
            return Result.build("not delete stock",ResultCodeEnum.DELETE_EMPTY);
        } else if (deleteNumber<idList.get("idList").size()) {
         return Result.build("not all are delete",ResultCodeEnum.DELETE_NOT_ALL);
        }else{
            return Result.ok("Delete Successful");
        }
    }

    @Override
    public Result insertStock(String token, ListStock stock) {

        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        try {
            listStockMapper.insert(stock);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.build("Insert Error", ResultCodeEnum.INSERT_ERROR);
        }

        return Result.ok("Insert Successful");
    }

    @Override
    public Result updateStock(String token, ListStock stock) {

        boolean expiration = jwtHelper.isExpiration(token);

        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        if (listStockMapper.updateById(stock) > 0) {
            return Result.ok("Update Successful");
        }
        return Result.build("Update Empty", ResultCodeEnum.UPDATE_ERROR);

    }
}




