package com.example.tianshu.service.serviceImpl;

import com.example.tianshu.dao.mapper.CostMapper;
import com.example.tianshu.dao.model.*;
import com.example.tianshu.service.ShowService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;



@Service
public class ShowServiceImpl implements ShowService {

    @Resource
    private CostMapper costMapper;

    @Override
    public salesVolumeDTO searchSales() {
        return costMapper.searchSales();
    }

    @Override
    public List<SoldItemDTO> searchsoldprop(String type) {
        if (type.equals("1")){
            System.out.println(costMapper.searchsoldpropbydis());
            return costMapper.searchsoldpropbydis();
        }else {
            return costMapper.searchsoldpropbytype();
        }
    }

    @Override
    public List<CostDTO> getCost() {
        return costMapper.inventoryRateQuery();
    }

    @Override
    public List<SoldItemDTO> weeklysoldQuery() {
        return costMapper.weeklysoldQuery();
    }

    @Override
    public List<SoldItemDTO> getMonthlySales() {
        return costMapper.monthlySalesTrendChart();
    }
}
