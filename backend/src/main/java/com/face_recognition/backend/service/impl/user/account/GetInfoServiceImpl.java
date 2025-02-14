package com.face_recognition.backend.service.impl.user.account;

import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.service.user.account.GetInfoService;
import com.face_recognition.backend.utils.CheckToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetInfoServiceImpl implements GetInfoService {
    @Override
    public Map<String, String> getinfo() {
        //调用springsecurity里面的类，通过服务器传过来的token自动获取信息
//        UsernamePasswordAuthenticationToken authenticationToken=(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//
//        UserDetailsImpl loginUser=(UserDetailsImpl)  authenticationToken.getPrincipal();
        User user= CheckToken.GetLoginUser().getUser();
        Map<String,String> map=new HashMap<>();
        map.put("error_message","success");
        map.put("id",user.getId().toString());
        map.put("username",user.getUsername());
        map.put("photo",user.getPhoto());
        map.put("identity",user.getIdentity());
        return map;
    }
}
