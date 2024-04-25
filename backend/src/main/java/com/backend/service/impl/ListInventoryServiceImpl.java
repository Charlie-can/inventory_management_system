package com.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.pojo.ListInventory;
import com.backend.service.ListInventoryService;
import com.backend.mapper.ListInventoryMapper;
import org.springframework.stereotype.Service;

/**
* @author charlie
* @description 针对表【list_inventory】的数据库操作Service实现
* @createDate 2024-04-23 21:40:43
*/
@Service
public class ListInventoryServiceImpl extends ServiceImpl<ListInventoryMapper, ListInventory>
    implements ListInventoryService{

}




