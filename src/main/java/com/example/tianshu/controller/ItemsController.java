package com.example.tianshu.controller;

import com.example.tianshu.client.ItemsClient;
import com.example.tianshu.dao.model.ItemsDO;
import com.example.tianshu.dao.model.QueryItemsDTO;
import com.example.tianshu.service.ItemsService;
import org.apache.ibatis.annotations.Param;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = {"http://www.tsjiaoyi.asia:10","http://101.35.54.61:7531","http://wl.tsjiaoyi.asia:10",
        "http://127.0.0.1:5501","http://localhost:7531","http://127.0.0.1:7531", "http://localhost:8080", "http://127.0.0.1:8080"})
@RequestMapping("/tianshu/items")
public class ItemsController implements ItemsClient {
    @Resource
    private ItemsService itemsService;

    Logger log = Logger.getLogger(ItemsController.class);

    @Override
    public List<ItemsDO> getAllItems(@Param("pageSize") int pageSize, @Param("pageNum") int pageNum) {
        Map<String, Integer> params = new HashMap<>();
        params.put("pageSize", pageSize);
        params.put("pageNum", pageNum);
        return itemsService.getItemsAll(params);
    }

    @Override
    public List<ItemsDO> getQueryItems(@RequestBody QueryItemsDTO queryItemsDTO) {
        log.info("启用服务1");
        return itemsService.getQueryItems(queryItemsDTO);
    }

    @Override
    public ItemsDO getItemById(String uid) {
        return itemsService.getItemById(uid);
    }

    @Override
    public boolean insertItem(@RequestParam("files") List<MultipartFile> files, @ModelAttribute ItemsDO itemsDO) throws IOException {
        itemsService.insertItem(files, itemsDO);
        return true;
    }

    @Override
    public boolean deleteItem(String uid) {
        itemsService.deleteItemById(uid);
        return true;
    }

    @Override
    public boolean updateItem(@RequestParam("file") MultipartFile file, @ModelAttribute ItemsDO itemsDO) {
        itemsService.updateItem(file, itemsDO);
        return true;
    }

    @Override
    public boolean updateInfo(ItemsDO itemsDO) {
        itemsService.updateInfo(itemsDO);
        return true;
    }


    @Override
    public boolean soldItem(String uid, Double salesUnitPrice, Integer saleNum, String district, String type, Integer source ,String name, String saleDate) {
        itemsService.soldItemById(uid, salesUnitPrice, saleNum, district, type, source, name, saleDate);
        return true;
    }

    @Override
    public boolean addNumById(String uid, Integer saleNum, String district, String type, Integer source, String name) {
        return itemsService.addNumById(uid, saleNum, district, type, source, name);
    }
}
