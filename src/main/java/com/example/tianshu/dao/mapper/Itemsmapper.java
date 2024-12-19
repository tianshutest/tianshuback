package com.example.tianshu.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tianshu.dao.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface Itemsmapper extends BaseMapper<ItemsDO> {

    List<ItemsDO> getAllItems(Map<String, Integer> params);

    List<ItemsDO> getQueryItems(QueryItemsDTO queryItemsDTO);

    ItemsDO getItemById(String uid);

    void insertItem(ItemsDTO itemsDTO);

    void deleteItemById(String uid);

    void updateItem(ItemsDTO itemsDTO);

    void updateInfo(ItemsDTO itemsDTO);

    void soldItemById(ItemsDTO solditemsDTO);

    void insertsoldedItem(ItemInsertDTO itemInsertDTO);

}
