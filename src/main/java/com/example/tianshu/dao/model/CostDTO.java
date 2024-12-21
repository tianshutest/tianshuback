package com.example.tianshu.dao.model;

import lombok.Data;

/**
 * 花费支出
 */
@Data
public class CostDTO {
    private String district;
    private Integer source;
    private Float cost;
}
