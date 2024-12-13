package com.example.tianshu.service.serviceImpl;

import com.example.tianshu.dao.mapper.LoginmapperMapper;
import com.example.tianshu.dao.model.UsersDTO;
import com.example.tianshu.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginmapperMapper loginmapperMapper;

    @Override
    public String checkPassword(UsersDTO usersDTO) {
        String username = usersDTO.getUsername();
        String password = usersDTO.getPassword();
        List<String> usernames = loginmapperMapper.checkUsername();
        if (usernames.contains(username)) {
            String checkPassword = loginmapperMapper.checkPassword(username);
            if (password.equals(checkPassword))
            {
                return "true";
            }else {
                return "false";
            }
        } else {
            return "账号未注册";
        }
    }
}
