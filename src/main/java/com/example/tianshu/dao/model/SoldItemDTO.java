package com.example.tianshu.dao.model;

import lombok.Data;

import java.util.Date;

@Data
public class SoldItemDTO {
    private String district;
    private Double totalprice;
    private Integer source;
    private String name;
    private String sourceName;
    private Date instime;
    private int number;
    private double price;
    private double total_sales;
}
