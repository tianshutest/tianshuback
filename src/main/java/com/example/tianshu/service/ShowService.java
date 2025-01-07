package com.example.tianshu.service;

import com.example.tianshu.dao.model.CostDTO;
import com.example.tianshu.dao.model.SoldItemDTO;
import com.example.tianshu.dao.model.VisitDTO;
import com.example.tianshu.dao.model.salesVolumeDTO;

import java.util.List;

public interface ShowService {

    salesVolumeDTO searchSales(String year);

    List<SoldItemDTO> searchsoldprop(String type);

    List<CostDTO> getCost(String year);

    List<SoldItemDTO> weeklysoldQuery();

    List<SoldItemDTO> getMonthlySales();

    VisitDTO visitTodayNum();

    VisitDTO visitTotalNum();

    Double searchsoldByYearta(String year);

    List<String> searchtaInventoryRate(String year);

    List<SoldItemDTO> searchsoldednumber();

    List<SoldItemDTO> searchsoldedByMonth();
}
