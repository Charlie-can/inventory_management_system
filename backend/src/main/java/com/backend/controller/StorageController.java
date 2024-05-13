package com.backend.controller;

import com.backend.pojo.ListSales;
import com.backend.pojo.ListStorage;
import com.backend.service.ListSalesService;
import com.backend.service.ListStockService;
import com.backend.service.ListStorageService;
import com.backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storage")
@CrossOrigin
public class StorageController {

    private ListStorageService listStorageService;

    @Autowired
    public void setListStorageService(ListStorageService listStorageService) {
        this.listStorageService = listStorageService;
    }

    @PostMapping("/storageStock")
    public Result storageStock(@RequestHeader String token, @RequestBody ListStorage listStorage) {

        Result result = listStorageService.storageStock(listStorage);

        return result;
    }


}
