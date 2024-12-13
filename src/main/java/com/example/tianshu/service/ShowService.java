package com.example.tianshu.service;

import com.example.tianshu.dao.model.CostDTO;
import com.example.tianshu.dao.model.SoldItemDTO;

import java.util.List;

public interface ShowService {

    List<CostDTO> getCost();

    List<SoldItemDTO> weeklysoldQuery();
}
