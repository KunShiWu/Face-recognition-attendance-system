package com.face_recognition.backend.controller.FaceRecognition.clockin;

import com.face_recognition.backend.service.FaceRecognition.clockin.RemoveClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoveClockInController {
    @Autowired
    RemoveClockInService removeClockInService;


    @PostMapping("/facerecognition/clockin/removeclockin/")
    public Map<String,String> removeclockin(@RequestParam Map<String,String > data){
        Integer missionId=Integer.parseInt(data.get("mission_id"));
        return removeClockInService.removeClockIn(missionId);
    }
}
