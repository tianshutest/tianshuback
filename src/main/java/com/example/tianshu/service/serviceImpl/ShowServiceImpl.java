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

    @Override
    public Double searchsoldByYearta() {
        return showMapper.searchsoldByYearta();
    }

    @Override
    public List<String> searchtaInventoryRate() {
        List<String> list = new ArrayList<>();
        double remaining = showMapper.searchsoldremaining();
        double inventory = showMapper.searchsoldByYearta();
        double cost = showMapper.searchsoldedtacost();
        double profit = inventory + remaining - cost;
        double profitRate = profit / cost;
        String formattedProfit = String.format("%.2f", profit);
        String formattedProfitRate = String.format("%.2f", profitRate * 100);
        list.add(formattedProfit);
        list.add(formattedProfitRate);
        return list;
    }

    @Override
    public List<SoldItemDTO> searchsoldednumber() {
        return showMapper.searchsoldednumber();
    }

    @Override
    public List<SoldItemDTO> searchsoldedByMonth() {
        return showMapper.searchsoldedByMonth();
    }
}
