package com.example.tianshu.service.serviceImpl;

import com.example.tianshu.dao.mapper.ShowMapper;
import com.example.tianshu.dao.model.*;
import com.example.tianshu.service.ShowService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;



@Service
public class ShowServiceImpl implements ShowService {

    @Resource
    private ShowMapper showMapper;

    @Override
    public salesVolumeDTO searchSales() {
        return showMapper.searchSales();
    }

    @Override
    public List<SoldItemDTO> searchsoldprop(String type) {
        if (type.equals("1")){
            System.out.println(showMapper.searchsoldpropbydis());
            return showMapper.searchsoldpropbydis();
        }else {
            return showMapper.searchsoldpropbytype();
        }
    }

    @Override
    public List<CostDTO> getCost() {
        return showMapper.inventoryRateQuery();
    }

    @Override
    public List<SoldItemDTO> weeklysoldQuery() {
        return showMapper.weeklysoldQuery();
    }

    @Override
    public List<SoldItemDTO> getMonthlySales() {
        return showMapper.monthlySalesTrendChart();
    }

    @Override
    public VisitDTO visitTodayNum() {
        return showMapper.visitTodayNum();
    }

    @Override
    public VisitDTO visitTotalNum() {
        return showMapper.visitTotalNum();
    }
}
