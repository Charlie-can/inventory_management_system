package com.backend.service;

import com.backend.pojo.ListStock;
import com.backend.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author charlie
 * @description 针对表【list_stock】的数据库操作Service
 * @createDate 2024-04-23 21:40:43
 */
public interface ListStockService extends IService<ListStock> {

    Result getAllStock( );

    Result queryStocks(  String type, String value);

    Result deleteStocks(  Map<String, List<Integer>> idList);


    Result insertStock( ListStock stock);

    Result updateStock(  ListStock stock);
}
