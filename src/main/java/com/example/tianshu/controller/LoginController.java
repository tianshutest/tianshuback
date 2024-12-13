package com.example.tianshu.controller;

import com.example.tianshu.client.LoginClient;
import com.example.tianshu.dao.model.UsersDTO;
import com.example.tianshu.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(origins = {"http://www.tsjiaoyi.asia:10","http://wl.tsjiaoyi.asia:10","http://101.35.54.61:7531", "http://101.35.54.61:5500","http://127.0.0.1:5501","http://127.0.0.1:7531"})
@RestController
@RequestMapping("/login")
public class LoginController implements LoginClient {
    @Resource
    private LoginService loginService;

    @Override
    public String checkPassword(@RequestBody UsersDTO usersDTO) {
        System.out.println(usersDTO);
        System.out.println(loginService.checkPassword(usersDTO));
        return loginService.checkPassword(usersDTO);
    }
}
