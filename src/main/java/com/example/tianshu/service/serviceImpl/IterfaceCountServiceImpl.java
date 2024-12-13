package com.example.tianshu.service.serviceImpl;

import com.example.tianshu.dao.mapper.InterfaceCountsMapper;
import com.example.tianshu.service.InterfaceCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IterfaceCountServiceImpl implements InterfaceCountService {

    @Resource
    InterfaceCountsMapper interfaceCountsMapper;

    @Override
    public void updateCount() {
        int counts = interfaceCountsMapper.countByExample();
        int count = counts + 1;
        interfaceCountsMapper.updateCount(count);
    }
}
