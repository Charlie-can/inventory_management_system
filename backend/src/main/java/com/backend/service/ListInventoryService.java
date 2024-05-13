package com.backend.service;

import com.backend.pojo.ListInventory;
import com.backend.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;

/**
* @author charlie
* @description 针对表【list_inventory】的数据库操作Service
* @createDate 2024-05-10 11:56:28
*/
public interface ListInventoryService extends IService<ListInventory> {

    Result generateInventory();

    Result getInventoryByDate(LocalDate localDate);

    Result getDate();

    Result getAllInventory();
}
