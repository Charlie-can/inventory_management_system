package com.backend.controller;

import com.backend.pojo.ListSales;
import com.backend.service.ListSalesService;
import com.backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
@CrossOrigin
public class SalesController {

    private ListSalesService listSalesService;

    @Autowired
    public void setListSalesService(ListSalesService listSalesService) {
        this.listSalesService = listSalesService;
    }

    @PostMapping("/salesStock")
    public Result salesStock(@RequestHeader String token, @RequestBody ListSales listSales) {



        Result result = listSalesService.salesStock(listSales);

        return result;
    }


}
