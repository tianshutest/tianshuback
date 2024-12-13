package com.example.tianshu.controller;

import com.example.tianshu.client.DemandsClient;
import com.example.tianshu.dao.model.DemandsDO;
import com.example.tianshu.dao.model.QueryItemsDTO;
import com.example.tianshu.service.DemandsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@CrossOrigin(origins = {"http://www.tsjiaoyi.asia:10","http://wl.tsjiaoyi.asia:10","http://101.35.54.61:7531", "http://101.35.54.61:5500","http://127.0.0.1:5500","http://127.0.0.1:7531"})
@RequestMapping("/tianshu/Demands")
public class DemandsController implements DemandsClient {

    @Resource
    private DemandsService demandsService;

    @Override
    public List<DemandsDO> getAllDemands(int pageSize, int pageNum) {
        return null;
    }

    @Override
    public List<DemandsDO> getQueryDemands(QueryItemsDTO queryItemsDTO) {
        return demandsService.getQueryDemands(queryItemsDTO);
    }

    @Override
    public DemandsDO getDemandById(String uid) {
        return demandsService.getDemandById(uid);
    }

    @Override
    public boolean insertDemand(DemandsDO demandsDO) {
        demandsService.insertDemand(demandsDO);
        return true;
    }

    @Override
    public boolean deleteDemandById(String uid) {
        demandsService.deleteDemandById(uid);
        return true;
    }

    @Override
    public boolean updateDemand(DemandsDO demandsDO) {
        demandsService.updateDemand(demandsDO);
        return true;
    }

    @Override
    public boolean soldDemandById(String uid) {
        demandsService.soldDemandById(uid);
        return true;
    }
}
