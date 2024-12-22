package com.example.tianshu.client;

import com.example.tianshu.dao.model.ItemsDO;
import com.example.tianshu.dao.model.QueryItemsDTO;
import com.example.tianshu.dao.model.SoldItemDTO;
import com.example.tianshu.dao.model.salesVolumeDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://www.tsjiaoyi.asia:10","http://101.35.54.61:7531","http://wl.tsjiaoyi.asia:10", "http://127.0.0.1:5501","http://localhost:7531","http://127.0.0.1:7531"})
public interface ItemsClient {

    @GetMapping("/getAllItems")
    List<ItemsDO> getAllItems(@Param("pageSize") int pageSize, @Param("pageNum") int pageNum);

    /** 分页查询所有在售道具数据
     * @param queryItemsDTO 分页信息以及查询条件
     * @return
     */
    @PostMapping("/getQueryItems")
    List<ItemsDO> getQueryItems(@RequestBody QueryItemsDTO queryItemsDTO);

    /** 根据id查询某个在售道具
     * @param uid 道具uid
     * @return
     */
    @GetMapping("/getItemById")
    ItemsDO getItemById(String uid);

    /** 新增在售道具
     * @param files 上传多张详细图片
     * @param itemsDO 其他道具信息
     * @return
     * @throws IOException
     */
    @PostMapping ("/insertItem")
    boolean insertItem(@RequestParam("files") List<MultipartFile> files, @ModelAttribute ItemsDO itemsDO) throws IOException;

    /** 根据id下架某个指定道具
     * @param uid 道具uid
     * @return
     */
    @PostMapping ("/deleteItemById")
    boolean deleteItem(String uid);

    /** 修改首页预览图片
     * @param file 需要上传的首页展示图片
     * @param itemsDO
     * @return
     */
    @PostMapping ("/updateItem")
    boolean updateItem(@RequestParam("file") MultipartFile file, @ModelAttribute ItemsDO itemsDO);

    /** 修改道具的部分数据
     * @param itemsDO 修改的道具信息
     * @return
     */
    @PostMapping ("/updateInfo")
    boolean updateInfo(@ModelAttribute ItemsDO itemsDO);

    /** 根据道具uid售出道具
     * @param uid 出售道具uid
     * @param salesUnitPrice 出售价格
     * @param saleNum 出售数量
     * @param district 大区
     * @param type 道具类型
     * @param source 货源
     * @return
     */
    @PostMapping ("/soldItemById")
    boolean soldItem(String uid, Double salesUnitPrice, Integer saleNum, String district, String type, Integer source, String name);

}
