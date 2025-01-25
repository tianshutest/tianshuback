package com.example.tianshu.dao.mapper;


import com.example.tianshu.dao.model.CostDTO;
import com.example.tianshu.dao.model.SoldItemDTO;
import com.example.tianshu.dao.model.VisitDTO;
import com.example.tianshu.dao.model.salesVolumeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShowMapper {

    salesVolumeDTO searchSales(String year);

    List<SoldItemDTO> searchsoldpropbydis();

    List<SoldItemDTO> searchsoldpropbytype();

    List<CostDTO> inventoryRateQuery(String year);

    List<SoldItemDTO> weeklysoldQuery();

    List<SoldItemDTO> monthlySalesTrendChart();

    VisitDTO visitTodayNum();

    VisitDTO visitTotalNum();

    Double searchsoldByYearta(String source);

    Double searchsoldremaining(String year);

    Double searchsoldedtacost(String source);

    Double costTotalByYear(String year);

    List<SoldItemDTO> searchsoldednumber();

    List<SoldItemDTO> searchsoldedByMonth();
}