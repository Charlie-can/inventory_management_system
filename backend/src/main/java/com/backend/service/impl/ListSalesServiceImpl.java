package com.backend.service.impl;

import com.backend.pojo.ListStock;
import com.backend.service.ListStockService;
import com.backend.utils.Result;
import com.backend.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.pojo.ListSales;
import com.backend.service.ListSalesService;
import com.backend.mapper.ListSalesMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author charlie
 * @description 针对表【list_sales】的数据库操作Service实现
 * @createDate 2024-04-23 21:40:43
 */
@Service
public class ListSalesServiceImpl extends ServiceImpl<ListSalesMapper, ListSales> implements ListSalesService {


    @Resource
    private ListSalesMapper listSalesMapper;


    private ListStockService listStockService;

    @Autowired
    public void setListStockService(ListStockService listStockService) {
        this.listStockService = listStockService;
    }

    @Override
    public Result salesStock(ListSales listSales) {

        @SuppressWarnings("unchecked") HashMap<String, ArrayList<ListStock>> stockData = (HashMap<String, ArrayList<ListStock>>) listStockService.queryStocks("id", String.valueOf(listSales.getStockId())).getData();
        Result updateStockResult;
        Map<String, String> resMap = new HashMap<>();
        if (listSales.getSaleVolume() <= 0) {
            resMap.put("message", "Sale Volume Cannot be less than or equal to zero");
            return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);

        }
        if (listSales.getSaleTime().isAfter(LocalDateTime.now())) {
            resMap.put("message", "Time is not the time of the future");

            return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);

        }

        if (listSales.getSalePrice() <= 0) {
            resMap.put("message", "Insert Parameter Error");
            return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
        }


        try {
            ListStock stock = stockData.get("stockList").get(0);

            if (stock.getReserveNow() < listSales.getSaleVolume()) {
                resMap.put("message", "Insufficient inventory");

                return Result.build(resMap, ResultCodeEnum.INSERT_ERROR);
            }



            stock.setReserveNow(stock.getReserveNow() - listSales.getSaleVolume());
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
            if (listSalesMapper.insert(listSales) == 1) {
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




