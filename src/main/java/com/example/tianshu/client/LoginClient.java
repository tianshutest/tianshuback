package com.example.tianshu.client;

import com.example.tianshu.dao.model.UsersDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



public interface LoginClient {
    @PostMapping("/login")
    String checkPassword(@RequestBody UsersDTO usersDTO);
}
