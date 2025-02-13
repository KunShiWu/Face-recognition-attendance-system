package com.face_recognition.backend.service.impl;

import com.face_recognition.backend.mapper.UserMapper;
import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.service.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetInfoServiceImpl implements GetInfoService {

    @Autowired
    UserMapper userMapper;

    @Override
    public     List<User> getinfo() {
       return userMapper.selectList(null);
    }
}
