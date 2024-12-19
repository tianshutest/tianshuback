package com.example.tianshu.client;

import com.example.tianshu.dao.model.CostDTO;
import com.example.tianshu.dao.model.SoldItemDTO;
import com.example.tianshu.dao.model.salesVolumeDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@CrossOrigin(origins = {"http://www.tsjiaoyi.asia:10","http://101.35.54.61:7531",
        "http://wl.tsjiaoyi.asia:10", "http://127.0.0.1:5501","http://localhost:7531","http://127.0.0.1:7531"})
public interface ShowClient {
    @GetMapping ("/searchsoldprop")
    List<SoldItemDTO> searchsoldprop(String type);

    @GetMapping ("/searchSales")
    salesVolumeDTO searchSales();

    @GetMapping("/getCost")
    List<CostDTO> costQuery();

    @GetMapping("/getWeekSold")
    List<SoldItemDTO> weeklysoldQuery();

    @GetMapping("/getMonthlySales")
    List<SoldItemDTO> getMonthlySales();
}
