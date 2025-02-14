package com.face_recognition.backend.controller.user.account;


import com.face_recognition.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/user/account/token/")
    public Map<String, String> getToken(@RequestParam Map<String, String> data)
    {
        String username=data.get("username");
        String password=data.get("password");

        return loginService.getToken(username,password);
    }

}
