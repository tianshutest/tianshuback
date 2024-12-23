package com.example.tianshu.dao.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * 出售道具信息
 */
@Data
public class SoldItemDTO {
    private String district;
    private Double totalprice;
    private Integer source;
    private String name;
    private String sourceName;
    private LocalDate instime;
    private int number;
    private double price;
    private double total_sales;
}
