package com.example.tianshu.dao.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 记录用户的访问时间/次数/地址
 */
@Data
public class Visitor {
    private Long id;

    private String ip;

    private Long visitCount;

    private LocalDateTime lastVisitTime;

    private LocalDate date;
}
