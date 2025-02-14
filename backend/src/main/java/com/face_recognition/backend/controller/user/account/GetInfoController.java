package com.face_recognition.backend.controller.user.account;

import com.face_recognition.backend.service.user.account.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetInfoController {

    @Autowired
    GetInfoService getInfoService;

    @GetMapping("/user/account/info/")
    public Map<String,String > getInfo(){
        return getInfoService.getinfo();
    }
}
