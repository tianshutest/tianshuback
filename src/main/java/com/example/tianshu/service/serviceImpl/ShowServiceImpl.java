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
    public salesVolumeDTO searchSales(String year) {
        return showMapper.searchSales(year);
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
    public List<CostDTO> getCost(String year) {
        return showMapper.inventoryRateQuery(year);
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
    public Double searchsoldByYearta(String year) {
        String source = "";
        if(year.equals("2024")){
            source = "2";
        }else if(year.equals("2025")){
            source = "4";
        }
        return showMapper.searchsoldByYearta(source);
    }

    @Override
    public List<String> searchtaInventoryRate(String year) {
        List<String> list = new ArrayList<>();
        //仓库剩余价值量
        double remaining = showMapper.searchsoldremaining(year);
        //本年已售价值量
        String source = "";
        if(year.equals("2024")){
            source = "2";
        }else if(year.equals("2025")){
            source = "4";
        }
        double inventory = showMapper.searchsoldByYearta(source);
        //本年充值量
        double cost = showMapper.searchsoldedtacost(source);
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
