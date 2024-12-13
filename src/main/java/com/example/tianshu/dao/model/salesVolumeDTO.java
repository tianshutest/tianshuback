package com.example.tianshu.dao.model;

import lombok.Data;

@Data
public class salesVolumeDTO {
    //当年销售额
    private Double annualSales;
    //历史销售额
    private Double historicalSales;
    //当月销售额
    private Double monthklySales;
    //当日销售额
    private Double dailySales;
}
