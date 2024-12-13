package com.example.tianshu.client;

import com.example.tianshu.dao.model.DemandsDO;
import com.example.tianshu.dao.model.QueryItemsDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface DemandsClient {
    @GetMapping("/getAllDemands")
    List<DemandsDO> getAllDemands(@Param("pageSize") int pageSize, @Param("pageNum") int pageNum);

    @GetMapping("/getQueryDemands")
    List<DemandsDO> getQueryDemands(@ModelAttribute QueryItemsDTO queryItemsDTO);

    @GetMapping("/getDemandById")
    DemandsDO getDemandById(String uid);

    @PostMapping("/insertDemand")
    boolean insertDemand(@ModelAttribute DemandsDO demandsDO);

    @PostMapping ("/deleteDemandById")
    boolean deleteDemandById(String uid);

    @PostMapping ("/updateDemand")
    boolean updateDemand(@ModelAttribute DemandsDO demandsDO);

    @PostMapping ("/soldDemandById")
    boolean soldDemandById(String uid);
}
