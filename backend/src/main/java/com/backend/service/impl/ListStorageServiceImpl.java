package com.backend.service.impl;


import com.backend.pojo.ListStock;
import com.backend.service.ListStockService;
import com.backend.utils.Result;
import com.backend.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.pojo.ListStorage;
import com.backend.service.ListStorageService;
import com.backend.mapper.ListStorageMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author charlie
 * @description 针对表【list_storage】的数据库操作Service实现
 * @createDate 2024-04-23 21:40:43
 */
@Service
public class ListStorageServiceImpl extends ServiceImpl<ListStorageMapper, ListStorage>
        implements ListStorageService {


    private ListStockService listStockService;


    @Resource
    private ListStorageMapper listStorageMapper;

    @Autowired
    public void setListStockService(ListStockService listStockService) {
        this.listStockService = listStockService;
    }


    public Result storageStock(ListStorage listStorage) {

        @SuppressWarnings("unchecked") HashMap<String, ArrayList<ListStock>> stockData = (HashMap<String, ArrayList<ListStock>>) listStockService.queryStocks("id", String.valueOf(listStorage.getStockId())).getData();
        Result updateStockResult;

        Map<String, String> resMap = new HashMap<>();
        if (listStorage.getStorageVolume() <= 0) {
            resMap.put("message", "Storage Volume Cannot be less than or equal to zero");
            return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
        }

        if (listStorage.getStorageTime().isAfter(LocalDateTime.now())) {
            resMap.put("message", "Time is not the time of the future");
            return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);

        }

        if (listStorage.getStoragePrice() <= 0) {
            resMap.put("message", "Insert Parameter Error");
            return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
        }


        try {
            ListStock stock = stockData.get("stockList").get(0);

            stock.setReserveNow(stock.getReserveNow() + listStorage.getStorageVolume());
            updateStockResult = listStockService.updateStock(stock);


        } catch (Exception e) {
            resMap.put("message", "Query Empty");

            return Result.build(resMap, ResultCodeEnum.QUERY_EMPTY);
        }


        if (updateStockResult.getCode() != 200) {
            resMap.put("message", "Update Error");

            return Result.build(resMap, ResultCodeEnum.UPDATE_ERROR);
        }

        try {
            if (listStorageMapper.insert(listStorage) == 1) {
                resMap.put("message", "insert successful");

                return Result.ok(resMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("message", "insert Error");

            return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
        }
        resMap.put("message", "insert Error");
        return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
    }
}




