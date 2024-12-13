package com.example.tianshu.service;

import com.example.tianshu.dao.model.UsersDTO;

public interface LoginService {
    String checkPassword(UsersDTO usersDTO);
}
