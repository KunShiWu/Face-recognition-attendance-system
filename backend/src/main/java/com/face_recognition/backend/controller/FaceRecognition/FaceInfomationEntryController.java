package com.face_recognition.backend.controller.FaceRecognition;

import com.face_recognition.backend.service.FaceRecognition.FaceInfomationEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FaceInfomationEntryController {

    @Autowired
    FaceInfomationEntryService faceInfomationEntryService;


    @PostMapping("/facerecognition/faceinfomationentry/")
    public Map<String,String> faceinfomationentry(@RequestParam Map<String,String> data)
    {
        String Base64Data=data.get("Base64Data");
        String name=data.get("name");
        return faceInfomationEntryService.faceinfomationentry(Base64Data,name);
    }

}
