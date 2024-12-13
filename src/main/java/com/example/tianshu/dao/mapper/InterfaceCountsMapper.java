package com.example.tianshu.dao.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterfaceCountsMapper {

    int countByExample();

    int updateCount(int count);
}