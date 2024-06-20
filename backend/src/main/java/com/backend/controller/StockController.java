package com.backend.controller;

import com.backend.pojo.ListStock;
import com.backend.service.ListStockService;
import com.backend.utils.Result;
import com.backend.utils.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stocks")
@CrossOrigin
public class StockController {


    @Resource
    private ListStockService listStockService;


    @GetMapping("/getAllStocks")
    public Result getAllStocks(@RequestHeader String token) {

        return listStockService.getAllStock();
    }


    @GetMapping("/queryStocks")
    public Result queryStocks(@RequestHeader String token, @RequestParam("type") String type, @RequestParam("value") String value) {
        if (!(type.isEmpty() || value.isEmpty())) {
            return listStockService.queryStocks( type, value);
        }
        System.out.println(Result.build("One parameter is empty", ResultCodeEnum.USERINPUT_ERROR));
        return Result.build("One parameter is empty", ResultCodeEnum.USERINPUT_ERROR);
    }

    @PostMapping("/deleteStocks")
    public Result deleteStocks(@RequestHeader String token, @RequestBody Map<String, List<Integer>> idList) {

        if (idList.get("idList")==null){
            System.out.println("null");
            return Result.build("delete parameter error",ResultCodeEnum.USERINPUT_ERROR);
        }

        return listStockService.deleteStocks(idList);
    }


    @PostMapping("/insertStock")
    public Result insertStock(@RequestHeader String token, @RequestBody ListStock stock) {

        System.out.println(stock);
        return listStockService.insertStock( stock);
    }

    @PostMapping("/updateStock")
    public Result updateStock(@RequestHeader String token, @RequestBody ListStock stock) {

        return listStockService.updateStock( stock);


    }


}
