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

    @GetMapping ("/searchSales")
    salesVolumeDTO searchSales();

    /**充值总金额查询
     * @return
     */
    @GetMapping("/getCost")
    List<CostDTO> costQuery();

    /**本周的出售道具的信息
     * @return
     */
    @GetMapping("/getWeekSold")
    List<SoldItemDTO> weeklysoldQuery();

    /**本月及上月销售额
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
}
