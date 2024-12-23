package com.example.tianshu.dao.mapper;


import com.example.tianshu.dao.model.InterfaceCountsDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterfaceCountsMapper {

    int countByExample();

    int updateCount(int count);

    InterfaceCountsDTO findByIpAndVisitDate(InterfaceCountsDTO interfaceCountsDTO);

    void insertVisit(InterfaceCountsDTO interfaceCountsDTO);

    void updateVisit(InterfaceCountsDTO interfaceCountsDTO);
}