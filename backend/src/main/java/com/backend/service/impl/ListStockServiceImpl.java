package com.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.pojo.ListStock;
import com.backend.service.ListStockService;
import com.backend.mapper.ListStockMapper;
import org.springframework.stereotype.Service;

/**
* @author charlie
* @description 针对表【list_stock】的数据库操作Service实现
* @createDate 2024-04-23 21:40:43
*/
@Service
public class ListStockServiceImpl extends ServiceImpl<ListStockMapper, ListStock>
    implements ListStockService{

}




