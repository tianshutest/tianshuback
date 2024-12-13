package com.example.tianshu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tianshu.dao.model.DemandsDO;
import com.example.tianshu.dao.model.QueryItemsDTO;

import java.util.List;

public interface DemandsService extends IService<DemandsDO> {

    List<DemandsDO> getQueryDemands(QueryItemsDTO queryItemsDTO);

    DemandsDO getDemandById(String uid);

    void insertDemand(DemandsDO demand);

    void updateDemand(DemandsDO demand);

    void deleteDemandById(String uid);

    void soldDemandById(String uid);
}
