package com.example.tianshu.service.serviceImpl;

import com.example.tianshu.dao.mapper.InterfaceCountsMapper;
import com.example.tianshu.dao.model.InterfaceCountsDTO;
import com.example.tianshu.service.InterfaceCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public InterfaceCountsDTO findByIpAndVisitDate(InterfaceCountsDTO interfaceCountsDTO) {
        return interfaceCountsMapper.findByIpAndVisitDate(interfaceCountsDTO);
    }

    @Override
    public void insertVisit(InterfaceCountsDTO interfaceCountsDTO) {
        interfaceCountsMapper.insertVisit(interfaceCountsDTO);
    }

    @Override
    public void updateVisit(InterfaceCountsDTO interfaceCountsDTO) {
        interfaceCountsMapper.updateVisit(interfaceCountsDTO);
    }

}
