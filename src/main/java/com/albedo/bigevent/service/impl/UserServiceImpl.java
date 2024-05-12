package com.albedo.bigevent.service.impl;

import com.albedo.bigevent.mapper.UserMapper;
import com.albedo.bigevent.pojo.User;
import com.albedo.bigevent.service.UserService;
import com.albedo.bigevent.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密密码
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username,md5String);
    }
}
