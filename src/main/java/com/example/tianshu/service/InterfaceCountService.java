package com.example.tianshu.service;

import com.example.tianshu.dao.model.InterfaceCountsDTO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface InterfaceCountService {

    void updateCount();

    InterfaceCountsDTO findByIpAndVisitDate(InterfaceCountsDTO interfaceCountsDTO);

    void insertVisit(InterfaceCountsDTO interfaceCountsDTO);

    void updateVisit(InterfaceCountsDTO interfaceCountsDTO);
}
