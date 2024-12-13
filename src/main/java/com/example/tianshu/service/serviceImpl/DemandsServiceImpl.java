package com.example.tianshu.service.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianshu.dao.mapper.DemandsMapper;
import com.example.tianshu.dao.model.DemandsDO;
import com.example.tianshu.dao.model.QueryItemsDTO;
import com.example.tianshu.service.DemandsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.example.tianshu.service.serviceImpl.ItemsServiceImpl.generateShortRandomUID;

@Service
public class DemandsServiceImpl extends ServiceImpl<DemandsMapper, DemandsDO>
        implements DemandsService {

    @Resource
    private DemandsMapper demandsMapper;


    @Override
    public List<DemandsDO> getQueryDemands(QueryItemsDTO queryItemsDTO) {
        queryItemsDTO.setPageSize(queryItemsDTO.getPageSize());
        queryItemsDTO.setPageNum(queryItemsDTO.getPageNum());
        queryItemsDTO.setOffset((queryItemsDTO.getPageNum() - 1) * queryItemsDTO.getPageSize());
        return demandsMapper.getQueryDemands(queryItemsDTO);
    }

    @Override
    public DemandsDO getDemandById(String uid) {
        return demandsMapper.getDemandById(uid);
    }

//    @Override
    public void insertDemand(DemandsDO demand) {
        String randomUID = generateShortRandomUID();
        demand.setUid(randomUID);
        demandsMapper.insertDemand(demand);
    }

    @Override
    public void updateDemand(DemandsDO demand) {
        demandsMapper.updateDemand(demand);
    }

    @Override
    public void deleteDemandById(String uid) {
        demandsMapper.deleteDemandById(uid);
    }

    @Override
    public void soldDemandById(String uid) {
        demandsMapper.soldDemandById(uid);
    }
}
