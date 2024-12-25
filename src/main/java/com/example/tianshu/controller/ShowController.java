package com.example.tianshu.controller;

import com.example.tianshu.client.ShowClient;
import com.example.tianshu.dao.model.CostDTO;
import com.example.tianshu.dao.model.SoldItemDTO;
import com.example.tianshu.dao.model.VisitDTO;
import com.example.tianshu.dao.model.salesVolumeDTO;
import com.example.tianshu.service.ShowService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = {"http://www.tsjiaoyi.asia:10","http://wl.tsjiaoyi.asia:10",
        "http://101.35.54.61:7531", "http://101.35.54.61:5500","http://127.0.0.1:5501","http://127.0.0.1:7531"})
@RestController
@RequestMapping("/show")
public class ShowController  implements ShowClient {

    @Resource
    private ShowService showService;

    @Override
    public List<CostDTO> costQuery() {
        return showService.getCost();
    }

    @Override
    public List<SoldItemDTO> weeklysoldQuery() {
        return showService.weeklysoldQuery();
    }

    @Override
    public List<SoldItemDTO> getMonthlySales() {
        return showService.getMonthlySales();
    }

    @Override
    public VisitDTO visitTodayNum() {
        return showService.visitTodayNum();
    }

    @Override
    public VisitDTO visitTotalNum() {
        return showService.visitTotalNum();
    }

    @Override
    public Double searchsoldByYearta() {
        return showService.searchsoldByYearta();
    }

    @Override
    public List<String> searchtaInventoryRate() {
        return showService.searchtaInventoryRate();
    }

    @Override
    public List<SoldItemDTO> searchsoldednumber() {
        return showService.searchsoldednumber();
    }

    @Override
    public List<SoldItemDTO> searchsoldedByMonth() {
        return showService.searchsoldedByMonth();
    }

    @Override
    public List<SoldItemDTO> searchsoldprop(String type) {
        return showService.searchsoldprop(type);
    }

    @Override
    public salesVolumeDTO searchSales() {
        return showService.searchSales();
    }
}
