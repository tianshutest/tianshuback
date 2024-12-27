package com.example.tianshu.dao.model;

import lombok.Data;
import java.io.Serializable;


@Data
public class ItemsDTO implements Serializable {
    private Long id;
    private String name;
    private String price;
    private String image;
    private String itemNumber;
    private String uid;
    private String type;
    private String district;
    private String provider;
    private String remark;
    private String imageUrls;
    private String totalItems;
    private Integer realQuantity;
    private Integer source;
    private Double realPrice;
    private Integer delflag;
}
