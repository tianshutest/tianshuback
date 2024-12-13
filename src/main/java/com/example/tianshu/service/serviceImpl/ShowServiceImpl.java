package com.example.tianshu.service.serviceImpl;

import com.example.tianshu.dao.mapper.CostMapper;
import com.example.tianshu.dao.model.*;
import com.example.tianshu.service.ShowService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;



@Service
public class ShowServiceImpl implements ShowService {

    @Resource
    private CostMapper costMapper;

    @Override
    public List<CostDTO> getCost() {
        return costMapper.inventoryRateQuery();
    }

    @Override
    public List<SoldItemDTO> weeklysoldQuery() {
        return costMapper.weeklysoldQuery();
    }
}
