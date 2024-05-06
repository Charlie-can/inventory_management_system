package com.backend.service;

import com.backend.pojo.ListSales;
import com.backend.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author charlie
* @description 针对表【list_sales】的数据库操作Service
* @createDate 2024-04-23 21:40:43
*/
public interface ListSalesService extends IService<ListSales> {



    Result salesStock(ListSales listSales);
}
