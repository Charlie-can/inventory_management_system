package com.backend.service.impl;

import com.backend.utils.Result;
import com.backend.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.pojo.ListSales;
import com.backend.service.ListSalesService;
import com.backend.mapper.ListSalesMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author charlie
 * @description 针对表【list_sales】的数据库操作Service实现
 * @createDate 2024-04-23 21:40:43
 */
@Service
public class ListSalesServiceImpl extends ServiceImpl<ListSalesMapper, ListSales>
        implements ListSalesService {


    @Resource
    private ListSalesMapper listSalesMapper;


    @Override
    public Result salesStock(ListSales listSales) {

        System.out.println(listSales);
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




