package com.example.tianshu.dao.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DemandsDTO implements Serializable {
    private Long id;
    private String uid;
    private String name;
    private String price;
    private String type;
    private String district;
    private String provider;
    private String description;
    private String totalDemands;
}
