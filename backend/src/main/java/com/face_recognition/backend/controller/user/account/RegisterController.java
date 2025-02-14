package com.face_recognition.backend.controller.user.account;

import com.face_recognition.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;


    @PostMapping("/user/account/register/")
    public Map<String,String > register(@RequestParam Map<String,String > data)
    {
        String username=data.get("username");
        String identity=data.get("identity");
        String password=data.get("password");
        String confirmedPassword=data.get("confirmedPassword");

        return registerService.register(username,identity,password,confirmedPassword);



    }


}
