package com.example.tianshu.controller;

import com.example.tianshu.client.InterfaceClient;
import com.example.tianshu.service.InterfaceCountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tianshu/iterfaceCount")
public class IterfaceCountController implements InterfaceClient {

    @Resource
    private InterfaceCountService interfaceCountService;

    @Override
    public boolean updateCount() {
        interfaceCountService.updateCount();
        return true;
    }
}
