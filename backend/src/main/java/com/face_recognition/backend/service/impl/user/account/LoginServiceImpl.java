package com.face_recognition.backend.service.impl.user.account;

import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.service.impl.utils.UserDetailsImpl;
import com.face_recognition.backend.service.user.account.LoginService;
import com.face_recognition.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService
{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public Map<String, String> getToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);//封装成加密后的类

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);//如果登录失败会自动处理

        UserDetailsImpl loginUser=(UserDetailsImpl) authenticate.getPrincipal();

        User user=loginUser.getUser();

        String jwt= JwtUtil.createJWT(user.getId().toString());
        Map<String,String> map=new HashMap<>();
        map.put("error_message","success");
        map.put("token",jwt);

        return  map;
    }
}
