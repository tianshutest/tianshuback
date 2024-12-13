package com.example.tianshu.dao.model;

import lombok.Data;

@Data
public class QueryItemsDTO {
    private int pageSize;
    private int pageNum;
    private String type;
    private String district;
    private int offset; // 新增 offset 属性
    private String name;
    private String description;
}
