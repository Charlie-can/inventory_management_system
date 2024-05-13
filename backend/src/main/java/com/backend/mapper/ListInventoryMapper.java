package com.backend.mapper;

import com.backend.pojo.ListInventory;
import com.backend.pojo.ListInventoryName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
* @author charlie
* @description 针对表【list_inventory】的数据库操作Mapper
* @createDate 2024-05-10 11:56:28
* @Entity com.backend.pojo.ListInventory
*/
public interface ListInventoryMapper extends BaseMapper<ListInventory> {
    List<ListInventoryName> SelectDateInventory(LocalDate date);

    List<LocalDate> SelectAllDate();
}




