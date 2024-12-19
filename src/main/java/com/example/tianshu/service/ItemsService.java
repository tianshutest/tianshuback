package com.example.tianshu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tianshu.dao.model.ItemsDO;
import com.example.tianshu.dao.model.QueryItemsDTO;
import com.example.tianshu.dao.model.SoldItemDTO;
import com.example.tianshu.dao.model.salesVolumeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface ItemsService extends IService<ItemsDO>{

    List<ItemsDO> getItemsAll(Map<String, Integer> params);

    List<ItemsDO> getQueryItems(QueryItemsDTO queryItemsDTO);

    ItemsDO getItemById(String uid);

    void insertItem(List<MultipartFile> files, ItemsDO itemsDO) throws IOException;

    void deleteItemById(String uid);

    void updateItem(MultipartFile file, ItemsDO itemsDO);

    void updateInfo(ItemsDO itemsDO);

    void soldItemById(String uid, Double salesUnitPrice, Integer saleNum, String district, String type, Integer source);
}
