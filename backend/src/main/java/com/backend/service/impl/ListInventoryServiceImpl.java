package com.backend.service.impl;

import com.backend.mapper.ListSalesMapper;
import com.backend.mapper.ListStockMapper;
import com.backend.mapper.ListStorageMapper;
import com.backend.pojo.*;
import com.backend.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.service.ListInventoryService;
import com.backend.mapper.ListInventoryMapper;
import jakarta.annotation.Resource;
import org.slf4j.helpers.CheckReturnValue;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author charlie
 * @description 针对表【list_inventory】的数据库操作Service实现
 * @createDate 2024-05-10 11:56:28
 */
@Service
public class ListInventoryServiceImpl extends ServiceImpl<ListInventoryMapper, ListInventory>
        implements ListInventoryService {


    @Resource
    private ListStockMapper listStockMapper;

    @Resource
    private ListSalesMapper listSalesMapper;

    @Resource
    private ListStorageMapper listStorageMapper;

    @Resource
    private ListInventoryMapper listInventoryMapper;

    @Override

    public Result generateInventory() {

        List<ListStock> stockList;

        stockList = listStockMapper.selectList(null);


        Map<String, String> resMap = new HashMap<>();


        for (ListStock listStock : stockList) {

            ListInventory listInventory = new ListInventory();

            listInventory.setStockId(listStock.getId());
            listInventory.setDate(LocalDateTime.now());
            listInventory.setRemainingCount(listStock.getReserveNow());
            listInventory.setReplenishCount(Math.max(listStock.getReserveMin() - listStock.getReserveNow(), 0));


            double totalSalePrice = 0;

            QueryWrapper<ListSales> listSalesQueryWrapper = new QueryWrapper<>();

            listSalesQueryWrapper.eq("stock_id", listStock.getId());


            List<ListSales> listSales = listSalesMapper.selectList(listSalesQueryWrapper);


            for (ListSales listSale : listSales) {
                totalSalePrice = listSale.getSalePrice() * listSale.getSaleVolume() + totalSalePrice;
            }
            listInventory.setSalePrice(totalSalePrice);


            double totalStoragePrice = 0;

            QueryWrapper<ListStorage> listStorageQueryWrapper = new QueryWrapper<>();

            listStorageQueryWrapper.eq("stock_id", listStock.getId());

            List<ListStorage> listStorages = listStorageMapper.selectList(listStorageQueryWrapper);

            System.out.println(listStorages);
            for (ListStorage listStorage : listStorages) {
                totalStoragePrice = listStorage.getStoragePrice() * listStorage.getStorageVolume() + totalStoragePrice;
            }
            listInventory.setStoragePrice(totalStoragePrice);

            listInventory.setProfitPrice(listInventory.getSalePrice() - listInventory.getStoragePrice());

            listInventoryMapper.insert(listInventory);

            resMap.put("message", "generate Success");

        }

        return Result.ok(resMap);

    }

    @Override
    public Result getInventoryByDate(LocalDate localDate) {
        List<ListInventoryName> listInventoryNames = listInventoryMapper.SelectDateInventory(localDate);

        Map<String, List<ListInventoryName>> resMap = new HashMap<>();

        resMap.put("inventoryList", listInventoryNames);

        return Result.ok(resMap);
    }

    @Override
    public Result getDate() {

        List<LocalDate> localDates = listInventoryMapper.SelectAllDate();

        Map<String, List<LocalDate>> resMap = new HashMap<>();

        resMap.put("inventoryDateList", localDates);

        return Result.ok(resMap);
    }

    @Override
    public Result getAllInventory() {

        List<LocalDate> dateList = ((Map<String, List<LocalDate>>) getDate().getData()).get("inventoryDateList");

        System.out.println(dateList);



        List<Map<LocalDate,List<ListInventoryName>>> arrayList = new ArrayList<>();


        for (LocalDate localDate : dateList) {

            Map<LocalDate,List<ListInventoryName>> localDateListInventoryNameMap = new HashMap<>();

            List<ListInventoryName> listInventoryNames = ((Map<String, List<ListInventoryName>>) getInventoryByDate(localDate).getData()).get("inventoryList");

            localDateListInventoryNameMap.put(localDate,listInventoryNames);

            arrayList.add(localDateListInventoryNameMap);
        }


        System.out.println(arrayList);

        Map<String, List<Map<LocalDate,List<ListInventoryName>>>> resMap = new HashMap<>();

        resMap.put("AllInventory", arrayList);

        return Result.ok(resMap);
    }
}




