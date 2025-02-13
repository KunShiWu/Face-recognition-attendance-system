package com.face_recognition.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.face_recognition.backend.mapper.UserMapper;
import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //传统的session很难解决跨域问题
    //需要存储在各个服务器上，不利于微服务操作
    //实现不同用户名通过自己的密码去对接数据库,用传统的session去验证的，获取数据库信息
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //通过username去数据库查询一下用户名和密码然后去实现UserDetails接口
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user =userMapper.selectOne(queryWrapper);
        String error_info="用户"+username+"不存在";
        if(user==null){
            throw new RuntimeException(error_info);
        }
            return new UserDetailsImpl(user);


    }
}
