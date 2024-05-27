package com.frontend.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryStock implements Serializable {

    @ExcelProperty("存储编号")
    private Integer id;

    @ExcelProperty("商品编号")
    private Integer stockId;

    @ExcelProperty("商品名称")
    private String stockName;

    @ExcelProperty("统计日期")
    private String date;

    @ExcelProperty("存入价格")
    private Double storagePrice;

    @ExcelProperty("售出价格")
    private Double salePrice;

    @ExcelProperty("销售利润")
    private Double profitPrice;

    @ExcelProperty("库存总数")
    private Integer remainingCount;

    @ExcelProperty("补货数量")
    private Integer replenishCount;
}