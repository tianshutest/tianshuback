package com.example.tianshu.service;

import com.example.tianshu.dao.model.CostDTO;
import com.example.tianshu.dao.model.SoldItemDTO;
import com.example.tianshu.dao.model.salesVolumeDTO;

import java.util.List;

public interface ShowService {

    salesVolumeDTO searchSales();

    List<SoldItemDTO> searchsoldprop(String type);

    List<CostDTO> getCost();

    List<SoldItemDTO> weeklysoldQuery();

    List<SoldItemDTO> getMonthlySales();
}
