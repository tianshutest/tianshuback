package com.example.tianshu.dao.model;

import lombok.Data;
import java.io.Serializable;

/**
 * 商品道具信息
 */
@Data
public class ItemsDO implements Serializable {
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
    private Double realPrice;
    private Integer delflag;
    private Integer soldedNum;
}
