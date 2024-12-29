package com.example.tianshu.client;

import com.example.tianshu.dao.model.CostDTO;
import com.example.tianshu.dao.model.SoldItemDTO;
import com.example.tianshu.dao.model.VisitDTO;
import com.example.tianshu.dao.model.salesVolumeDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@CrossOrigin(origins = {"http://www.tsjiaoyi.asia:10","http://101.35.54.61:7531",
        "http://wl.tsjiaoyi.asia:10", "http://127.0.0.1:5501","http://localhost:7531","http://127.0.0.1:7531"})
public interface ShowClient {

    /**饼图数据，每个金额占比比例
     * @param type
     * @return
     */
    @GetMapping ("/searchsoldprop")
    List<SoldItemDTO> searchsoldprop(String type);

    /**查询日月年销售额（总额）
     * @return
     */
    @GetMapping ("/searchSales")
    salesVolumeDTO searchSales();

    /**充值总金额查询（塔货充值）
     * @return
     */
    @GetMapping("/getCost")
    List<CostDTO> costQuery();

    /**本周的出售道具的信息
     * @return
     */
    @GetMapping("/getWeekSold")
    List<SoldItemDTO> weeklysoldQuery();

    /**本月及上月销售额（总额）
     * @return
     */
    @GetMapping("/getMonthlySales")
    List<SoldItemDTO> getMonthlySales();

    /**今日访客数，访客量
     * @return
     */
    @GetMapping("/visitTodayNum")
    VisitDTO visitTodayNum();

    /**总访客数，访客量
     * @return
     */
    @GetMapping("/visitTotalNum")
    VisitDTO visitTotalNum();

    /**每年皇塔道具销售额（只含塔）
     * @return
     */
    @GetMapping("/searchsoldByYearta")
    Double searchsoldByYearta();

    /**查询每年皇塔利润+利润率（只含塔）
     * @return
     */
    @GetMapping("/searchtaInventoryRate")
    List<String> searchtaInventoryRate();

    /**
     * @return
     */
    @GetMapping("/searchsoldednumber")
    List<SoldItemDTO> searchsoldednumber();

    /**查询每月销售额（总额）
     * @return
     */
    @GetMapping("/searchsoldedByMonth")
    List<SoldItemDTO> searchsoldedByMonth();
}
