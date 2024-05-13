package com.backend.controller;

import com.backend.pojo.ListInventory;
import com.backend.service.ListInventoryService;
import com.backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/inventory")
@CrossOrigin
public class InventoryController {

    private ListInventoryService listInventoryService;

    @Autowired
    public void setListInventoryService(ListInventoryService listInventoryService) {
        this.listInventoryService = listInventoryService;
    }

    @GetMapping("/generateInventory")
    public Result generateInventory(@RequestHeader String token){
    return  listInventoryService.generateInventory();
    }

    @GetMapping("/getInventoryByDate")
    public Result getInventoryByDate(@RequestHeader String token, @RequestParam("date") LocalDate localDate){
        return  listInventoryService.getInventoryByDate(localDate);
    }

    @GetMapping("/getDate")
    public Result getDate(@RequestHeader String token){
        return  listInventoryService.getDate();
    }


    @GetMapping("/getAllInventory")
    public Result getAllInventory(@RequestHeader String token){
        return  listInventoryService.getAllInventory();
    }
}
