package com.face_recognition.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.face_recognition.backend.mapper.UserMapper;
import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String identity, String password, String confirmePassword) {
        Map<String, String> map = new HashMap<>();
        if (username == null) {
            map.put("error_message", "用户名不能为空");
            return map;
        }
        if (password == null || confirmePassword == null) {
            map.put("error_message", "密码不能为空");
            return map;
        }
        username = username.trim();

        if (username.length() == 0) {
            map.put("error_message", "用户名不能为空");
            return map;
        }
        if (username.length() > 100) {
            map.put("error_message", "用户名过长");
            return map;
        }
        if (password.length() > 100 || confirmePassword.length() > 100) {
            map.put("error_message", "密码长度不能大于100");
            return map;
        }

        if (password.length() == 0 || confirmePassword.length() == 0) {
            map.put("error_message", "密码不能为空");
            return map;
        }
        if (password.length() < 6 || confirmePassword.length() < 6) {
            map.put("error_message", "密码长度不能小于6");
            return map;
        }

        if (!password.equals(confirmePassword)) {
            map.put("error_message", "两次输入的密码不一致");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "用户名已存在");
            return map;
        }

        String encodePassword = passwordEncoder.encode(password);
        String photo = "https://bpic.588ku.com/element_origin_min_pic/19/08/13/959273b4e70177693b5039d9936da11c.jpg";
        User user = new User(null, username, encodePassword, photo,null ,identity, null);
        userMapper.insert(user);

        map.put("error_message", "success");
        return map;
    }
}