package com.example.tianshu.dao.model;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

@Data
public class ItemInsertDTO {
    private Double price;
    private Integer number;
    private String uid;
    private String district;
    private String type;
    private Integer source;
    private String name;
    private Date instime;
}
