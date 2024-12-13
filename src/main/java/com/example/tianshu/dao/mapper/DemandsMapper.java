package com.example.tianshu.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tianshu.dao.model.DemandsDO;
import com.example.tianshu.dao.model.QueryItemsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DemandsMapper extends BaseMapper<DemandsDO> {

    List<DemandsDO> getAllDemands();

    List<DemandsDO> getQueryDemands(QueryItemsDTO queryItemsDTO);

    DemandsDO getDemandById(String uid);

    void insertDemand(DemandsDO demand);

    void updateDemand(DemandsDO demand);

    void deleteDemandById(String uid);

    void soldDemandById(String uid);
}
