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

    @PostMapping("/getQueryItems")
    List<ItemsDO> getQueryItems(@RequestBody QueryItemsDTO queryItemsDTO);

    @GetMapping("/getItemById")
    ItemsDO getItemById(String uid);

    @PostMapping ("/insertItem")
    boolean insertItem(@RequestParam("files") List<MultipartFile> files, @ModelAttribute ItemsDO itemsDO) throws IOException;

    @PostMapping ("/deleteItemById")
    boolean deleteItem(String uid);

    @PostMapping ("/updateItem")
    boolean updateItem(@RequestParam("file") MultipartFile file, @ModelAttribute ItemsDO itemsDO);

    @PostMapping ("/updateInfo")
    boolean updateInfo(@ModelAttribute ItemsDO itemsDO);

    @PostMapping ("/soldItemById")
    boolean soldItem(String uid, Double salesUnitPrice, Integer saleNum, String district, String type, Integer source);

}
