package com.example.tianshu.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianshu.dao.mapper.Itemsmapper;
import com.example.tianshu.dao.model.*;
import com.example.tianshu.service.ItemsService;
import com.example.tianshu.utils.FileCompression;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import java.sql.Date; // 确保导入的是 java.sql.Date
import java.time.LocalDate; // 导入 LocalDate
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Service
public class ItemsServiceImpl extends ServiceImpl<Itemsmapper, ItemsDO>
        implements ItemsService {

    @Resource
    private Itemsmapper itemsmapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<ItemsDO> getItemsAll(Map<String, Integer> params) {
        return itemsmapper.getAllItems(params);
    }

    @Override
    public List<ItemsDO> getQueryItems(QueryItemsDTO queryItemsDTO) {
        queryItemsDTO.setPageSize(queryItemsDTO.getPageSize());
        queryItemsDTO.setPageNum(queryItemsDTO.getPageNum());
        queryItemsDTO.setOffset((queryItemsDTO.getPageNum() - 1) * queryItemsDTO.getPageSize());
        String itemName = queryItemsDTO.getName().trim();
        queryItemsDTO.setName(itemName);
        List<ItemsDO> items = new ArrayList<>();
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        if (queryItemsDTO.getName().isEmpty()){
            // Redis key 可以通过 queryItemsDTO 来生成
            String redisKey = "page" + queryItemsDTO.getPageNum() + "/size" + queryItemsDTO.getPageSize()
                    + "/type=" + queryItemsDTO.getType() + "/district=" + queryItemsDTO.getDistrict();
            // 获取 Redis 中的缓存数据
            items = (List<ItemsDO>) valueOps.get(redisKey);
            if (items != null) {
                // 如果缓存存在，直接返回缓存数据
                System.out.println("调用了redis");
                return items;
            }
            items = itemsmapper.getQueryItems(queryItemsDTO);
            // 查询到的数据放入 Redis，设置缓存过期时间（例如1小时）
            if (items != null) {
                valueOps.set(redisKey, items, 36, TimeUnit.HOURS);
            }
        }else{
            items = itemsmapper.getQueryItems(queryItemsDTO);
        }
        return items;
    }

    @Override
    public ItemsDO getItemById(String uid) {
        return itemsmapper.getItemById(uid);
    }

    @Override
    public void insertItem(List<MultipartFile> files, ItemsDO itemsDO) throws IOException {
        ItemsDTO itemsDTO = new ItemsDTO();
        BeanUtils.copyProperties(itemsDO, itemsDTO);
        String randomUID = generateShortRandomUID();
        String directoryPath = "C:/project/tianshufront/.vscode/public/images";
        itemsDTO.setUid(randomUID);
        if (files != null && !files.isEmpty()) {
            if (files.size() == 1){
                MultipartFile file = files.get(0);
                MultipartFile compress_file = FileCompression.compressImage(file);
                String imagesUrl = saveImageToLocalDirectory(compress_file, directoryPath);
                itemsDTO.setImage(imagesUrl);
            }
            List<String> imagesUrls = new ArrayList<>();
            for (MultipartFile image : files) {
                try {
                    MultipartFile compress_file = FileCompression.compressImage(image);
                    String imageUrl = saveImageToLocalDirectory(compress_file, directoryPath);
                    imagesUrls.add(imageUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                    // 处理保存图片时的异常
                }
            }
            // 将 imagesUrls 转换为 JSON 字符串
            try {
                String imagesUrlsJson = new ObjectMapper().writeValueAsString(imagesUrls);
                itemsDTO.setImageUrls(imagesUrlsJson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                // 处理转换为 JSON 字符串时的异常
            }
        }
        // 调用 MyBatis 的插入方法
        itemsmapper.insertItem(itemsDTO);
    }

    @Override
    public void deleteItemById(String uid) {
        itemsmapper.deleteItemById(uid);
    }

    @Override
    public void updateItem(MultipartFile file, ItemsDO itemsDO) {
        ItemsDTO itemsDTO = new ItemsDTO();
        BeanUtils.copyProperties(itemsDO, itemsDTO);
        String directoryPath = "C:/project/tianshufront/.vscode/public/images";
        try {
            MultipartFile compress_file = FileCompression.compressImage(file);
            String imagesUrl = saveImageToLocalDirectory(compress_file, directoryPath);
            itemsDTO.setImage(imagesUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        itemsmapper.updateItem(itemsDTO);
    }

    @Override
    public void updateInfo(ItemsDO itemsDO) {
        ItemsDTO itemsDTO = new ItemsDTO();
        BeanUtils.copyProperties(itemsDO, itemsDTO);
        itemsmapper.updateInfo(itemsDTO);
    }

    @Override
    public void soldItemById(String uid, Double salesUnitPrice, Integer saleNum, String district, String type, Integer source) {
        ItemsDO itemsDO = itemsmapper.getItemById(uid);
        //获得销售后的真实数量
        int realQuantity = itemsDO.getRealQuantity() - saleNum;
        //获得前台展示的数量
        int number = Integer.parseInt(itemsDO.getItemNumber().replaceAll("[^0-9]", ""));
        //如果售后真实数量>前台展示的数量 ， 则依旧显示前台数量
        int finalNum = (realQuantity > number) ? number : realQuantity;
        LocalDate today = LocalDate.now(); // 获取今天的日期
        Date sqlDate = Date.valueOf(today); // 将 LocalDate 转换为 java.sql.Date
        ItemsDTO solditemsDTO = new ItemsDTO();
        ItemInsertDTO itemInsertDTO = new ItemInsertDTO();
        solditemsDTO.setItemNumber(String.valueOf(finalNum));
        solditemsDTO.setUid(uid);
        solditemsDTO.setRealQuantity(realQuantity);
        itemInsertDTO.setUid(uid);
        itemInsertDTO.setDistrict(district);
        itemInsertDTO.setNumber(saleNum);
        itemInsertDTO.setPrice(salesUnitPrice);
        itemInsertDTO.setSource(source);
        itemInsertDTO.setType(type);
        itemInsertDTO.setName(itemsDO.getName());
        itemInsertDTO.setInstime(sqlDate);
        itemsmapper.soldItemById(solditemsDTO);
        itemsmapper.insertsoldedItem(itemInsertDTO);
        if (finalNum <= 0){
            itemsmapper.deleteItemById(uid);
        }
    }

    public static String generateShortRandomUID() {
        // 生成一个随机的十六进制字符串
        String randomHex = Integer.toHexString(new Random().nextInt(0xFFFFFF));
        // 确保字符串长度为八位
        while (randomHex.length() < 8) {
            randomHex = "0" + randomHex; // 如果不足八位，在字符串前面添加0
        }
        return randomHex;
    }

    public static String saveImageToLocalDirectory(MultipartFile file, String directoryPath) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null) {
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex > 0) {
                fileExtension = originalFilename.substring(dotIndex);
            }
        }
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + fileExtension;
        // 创建目标目录路径
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // 创建目录
        }
        // 创建文件对象
        File destFile = new File(directory, fileName);
        // 保存文件到本地
        file.transferTo(destFile);
        // 返回保存的文件名
        return fileName;
    }

    private void scheduledCacheDelete(String redisKey) {
        // 延迟删除缓存的操作（例如，使用线程池或者定时任务）
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            redisTemplate.delete(redisKey);
        }, 1, TimeUnit.SECONDS);  // 延迟 1 秒
    }
}
