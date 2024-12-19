package com.example.tianshu.dao.mapper;


import com.example.tianshu.dao.model.CostDTO;
import com.example.tianshu.dao.model.SoldItemDTO;
import com.example.tianshu.dao.model.salesVolumeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CostMapper{

    salesVolumeDTO searchSales();

    List<SoldItemDTO> searchsoldpropbydis();

    List<SoldItemDTO> searchsoldpropbytype();

    List<CostDTO> inventoryRateQuery();

    List<SoldItemDTO> weeklysoldQuery();

    List<SoldItemDTO> monthlySalesTrendChart();
}