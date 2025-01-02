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
import org.springframework.transaction.annotation.Transactional;
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

    /** 分页查询所有在售道具，使用redis缓存缓解查询压力
     * @param queryItemsDTO
     * @return
     */
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
            String redisKey = "page" + queryItemsDTO.getPageNum() + ":size" + queryItemsDTO.getPageSize()
                    + ":type=" + queryItemsDTO.getType() + ":district=" + queryItemsDTO.getDistrict();
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

    /** 根据道具编号查询单条道具
     * @param uid
     * @return
     */
    @Override
    public ItemsDO getItemById(String uid) {
        return itemsmapper.getItemById(uid);
    }

    /** 新增上架道具
     * @param files 道具图片
     * @param itemsDO 道具详细信息
     * @throws IOException
     */
    @Transactional
    @Override
    public void insertItem(List<MultipartFile> files, ItemsDO itemsDO) throws IOException {
        ItemsDTO itemsDTO = new ItemsDTO();
        BeanUtils.copyProperties(itemsDO, itemsDTO);
        String randomUID = generateShortRandomUID();
        String directoryPath = "C:/project/tianshufrontvue/src/assets/images";
        itemsDTO.setUid(randomUID);
        if (files != null && !files.isEmpty()) {
            if (files.size() == 1){
                MultipartFile file = files.get(0);
                MultipartFile compress_file = FileCompression.compressImage(file);
                String imagesUrl = saveImageToLocalDirectory(compress_file, directoryPath);
                String directoryPathDist = "C:/project/tianshufrontvue/dist/img";
                saveImageToLocalDirectory(compress_file, directoryPathDist);
                String directoryPathModify = "C:/project/tianshufrontModify/.vscode/public/images";
                saveImageToLocalDirectory(compress_file, directoryPathModify);
                itemsDTO.setImage(imagesUrl);
            }
            List<String> imagesUrls = new ArrayList<>();
            for (MultipartFile image : files) {
                try {
                    MultipartFile compress_file = FileCompression.compressImage(image);
                    String imageUrl = saveImageToLocalDirectory(compress_file, directoryPath);
                    String directoryPathDist = "C:/project/tianshufrontvue/dist/img";
                    saveImageToLocalDirectory(compress_file, directoryPathDist);
                    String directoryPathModify = "C:/project/tianshufrontModify/.vscode/public/images";
                    saveImageToLocalDirectory(compress_file, directoryPathModify);
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
        Integer soldedsource = itemsDO.getSource();
        if (soldedsource.equals(1) || soldedsource.equals(2) || soldedsource.equals(3)) {
            itemsmapper.insertsoldPartItem(itemsDTO);
        }
    }

    /** 下架单条道具
     * @param uid 需要删除道具的编号
     */
    @Override
    public void deleteItemById(String uid) {
        itemsmapper.deleteItemById(uid);
    }

    /** 修改道具展示图片
     * @param file 图片
     * @param itemsDO 道具信息
     */
    @Override
    public void updateItem(MultipartFile file, ItemsDO itemsDO) {
        ItemsDTO itemsDTO = new ItemsDTO();
        BeanUtils.copyProperties(itemsDO, itemsDTO);
        String directoryPath = "C:/project/tianshufrontvue/src/assets/images";
        try {
            MultipartFile compress_file = FileCompression.compressImage(file);
            String imagesUrl = saveImageToLocalDirectory(compress_file, directoryPath);
            String directoryPathDist = "C:/project/tianshufrontvue/dist/img";
            saveImageToLocalDirectory(compress_file, directoryPathDist);
            String directoryPathModify = "C:/project/tianshufrontModify/.vscode/public/images";
            saveImageToLocalDirectory(compress_file, directoryPathModify);
            itemsDTO.setImage(imagesUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        itemsmapper.updateItem(itemsDTO);
    }

    /** 更新道具信息
     * @param itemsDO
     */
    @Override
    public void updateInfo(ItemsDO itemsDO) {
        ItemsDTO itemsDTO = new ItemsDTO();
        BeanUtils.copyProperties(itemsDO, itemsDTO);
        itemsmapper.updateInfo(itemsDTO);
    }

    /** 道具售出  1.先计算总表itemsnumber是否需要修改，再对realquantity进行修改
     * 2.修改完总表数量之后，再对分表进行数量修改  3.删完如果realquantity <= 0 则调用道具下架功能
     * @param uid 道具编号
     * @param salesUnitPrice 出售价格
     * @param saleNum 出售数量
     * @param district 大区
     * @param type 道具类型
     * @param source 道具来源
     */
    @Override
    @Transactional
    public void soldItemById(String uid, Double salesUnitPrice, Integer saleNum, String district, String type, Integer source, String name, String saleDate) {
        String uuid = uid.trim();
        ItemsDO itemsDO = itemsmapper.getItemById(uuid);
        //获得销售后的真实数量
        int realQuantity = itemsDO.getRealQuantity() - saleNum;
        //获得前台展示的数量
        int number = Integer.parseInt(itemsDO.getItemNumber().replaceAll("[^0-9]", ""));
        //如果售后真实数量>前台展示的数量 ， 则依旧显示前台数量
        int finalNum = (realQuantity > number) ? number : realQuantity;
        Integer soldedsource = source;
        ItemsDTO solditemsDTO = new ItemsDTO();
        ItemInsertDTO itemInsertDTO = new ItemInsertDTO();
        Map<String, Object> params = new HashMap<>();
        solditemsDTO.setItemNumber(String.valueOf(finalNum));
        solditemsDTO.setUid(uuid);
        solditemsDTO.setRealQuantity(realQuantity);
        itemInsertDTO.setUid(uuid);
        itemInsertDTO.setDistrict(district);
        itemInsertDTO.setNumber(saleNum);
        itemInsertDTO.setPrice(salesUnitPrice);
        itemInsertDTO.setSource(source);
        itemInsertDTO.setType(type);
        itemInsertDTO.setName(name);
        itemInsertDTO.setInstime(Date.valueOf(saleDate));
        itemsmapper.soldItemById(solditemsDTO);
        if (soldedsource.equals(1) || soldedsource.equals(2) || soldedsource.equals(3)) {
            params.put("uid", uuid);
            params.put("source", soldedsource); // 直接使用 soldedsource (假设是 Integer 类型)
            soldPartItem(params, saleNum);
        }
        itemsmapper.insertsoldedItem(itemInsertDTO);
        if (finalNum <= 0){
            itemsmapper.deleteItemById(uid);
        }
    }

    /** 道具数量新增 1.先增总表数量  2.增加分表数量  3.事务原子性
     * @param uid 道具编号
     * @param saleNum 增加数量
     * @param district 大区
     * @param type 道具类型
     * @param source 道具货源
     * @param name 道具名字
     * @return
     */
    @Override
    @Transactional
    public boolean addNumById(String uid, Integer saleNum, String district, String type, Integer source, String name) {
        ItemsDO itemsDO = itemsmapper.getItemById(uid);
        Integer itemNumber = Integer.valueOf(itemsDO.getItemNumber().replaceAll("[^0-9]", ""));
        Integer realQuantity = itemsDO.getRealQuantity() + saleNum;
        String finalNum = String.valueOf((realQuantity > itemNumber) ? itemNumber : realQuantity);
        Map<String, Object> addNum = new HashMap<>();
        addNum.put("itemNumber", finalNum);
        addNum.put("realQuantity", realQuantity);
        addNum.put("uid", uid);
        itemsmapper.addNumById(addNum);
        Integer addSource = source;
        if (addSource.equals(1) || addSource.equals(2) || addSource.equals(3)) {
            Map<String, Object> addPartNum = new HashMap<>();
            addPartNum.put("uid", uid);
            addPartNum.put("realQuantity", realQuantity);
            addPartNum.put("district", district);
            addPartNum.put("type", type);
            addPartNum.put("addSource", addSource);
            addPartNum.put("name", name);
            itemsmapper.addParyNumById(addPartNum);
        }
        return true;
    }

    /** 随机生成八位数
     * @return
     */
    public static String generateShortRandomUID() {
        // 生成一个随机的十六进制字符串
        String randomHex = Integer.toHexString(new Random().nextInt(0xFFFFFF));
        // 确保字符串长度为八位
        while (randomHex.length() < 8) {
            randomHex = "0" + randomHex; // 如果不足八位，在字符串前面添加0
        }
        return randomHex;
    }

    /** 将图片保存到本地
     * @param file 传入图片
     * @param directoryPath 保存到本地地址
     * @return
     * @throws IOException
     */
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

    /** 更新分表中的真实数量
     * @param params 存放uid source的map
     * @param saleNum 出售后的实际库存
     */
    public void soldPartItem(Map<String, Object> params, int saleNum) {
        // 查询商品信息
        ItemsDO itemsDO1 = itemsmapper.selectsoldItemBytableById(params);
        Map<String, Object> partSold = new HashMap<>();
        // 计算销售后剩余数量
        int realPartQuantity = itemsDO1.getRealQuantity() - saleNum;
        // 设置新的商品信息
        partSold.put("uid", params.get("uid"));
        partSold.put("source", params.get("source"));
        partSold.put("realQuantity", realPartQuantity);
        // 更新商品信息
        itemsmapper.soldItemBytableById(partSold);
    }
}
