package com.example.tianshu.dao.mapper;


import com.example.tianshu.dao.model.CostDTO;
import com.example.tianshu.dao.model.SoldItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CostMapper{

    List<CostDTO> inventoryRateQuery();

    List<SoldItemDTO> weeklysoldQuery();
}