package com.face_recognition.backend.controller;

import com.face_recognition.backend.service.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetInfoController {

    @Autowired
    GetInfoService getInfoService;
    @GetMapping("/user/info/")
    public String getString(){
        return getInfoService.getinfo();
    }

}
