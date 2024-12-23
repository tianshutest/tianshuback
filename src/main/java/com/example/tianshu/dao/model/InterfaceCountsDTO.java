package com.example.tianshu.dao.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InterfaceCountsDTO {

    private Integer counts;

    private LocalDate visitDate;

    private String ip;

    private Long id;

    private Integer visitCount;

    private LocalDateTime lastVisitTime;
}