package com.face_recognition.backend.utils;

import com.face_recognition.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class CheckToken {
    //调用springsecurity里面的类，通过服务器传过来的token自动获取信息
        public static   UserDetailsImpl GetLoginUser()
        {
            UsernamePasswordAuthenticationToken authenticationToken=(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            return (UserDetailsImpl)  authenticationToken.getPrincipal();
        }

}
