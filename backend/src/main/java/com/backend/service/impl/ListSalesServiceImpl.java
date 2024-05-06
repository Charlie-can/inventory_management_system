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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        System.out.println(listSales);

        @SuppressWarnings("unchecked") HashMap<String, ArrayList<ListStock>> stockData = (HashMap<String, ArrayList<ListStock>>) listStockService.queryStocks("id", String.valueOf(listSales.getStockId())).getData();
        Result updateStockResult;


        try {
            ListStock stock = stockData.get("stockList").get(0);

            if (stock.getReserveNow() < listSales.getSaleVolume()) {
                return Result.build("Insufficient inventory", ResultCodeEnum.INSERT_ERROR);
            }
            stock.setReserveNow(stock.getReserveNow() - listSales.getSaleVolume());
            updateStockResult = listStockService.updateStock(stock);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build("Query Empty", ResultCodeEnum.QUERY_EMPTY);
        }

        if (updateStockResult.getCode() != 200) {
            return Result.build("Update Error", ResultCodeEnum.UPDATE_ERROR);
        }

        try {
            if (listSalesMapper.insert(listSales) == 1) {
                return Result.ok("insert successful");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build("insert Error", ResultCodeEnum.INSERT_ERROR);
        }
        return Result.build("insert Error", ResultCodeEnum.INSERT_ERROR);
    }
}




