package com.example.tianshu.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface LoginmapperMapper{
    String checkPassword(String username);

    List<String> checkUsername();
}
