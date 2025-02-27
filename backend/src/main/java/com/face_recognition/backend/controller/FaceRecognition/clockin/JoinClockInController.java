package com.face_recognition.backend.controller.FaceRecognition.clockin;

import com.face_recognition.backend.service.FaceRecognition.clockin.JoinClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JoinClockInController {

    @Autowired
    JoinClockInService CheckClockInService;

    @PostMapping("/facerecognition/clockin/joinclockin/")
    public Map<String,String> JoinClockIn(@RequestParam Map<String,String> data){

        return CheckClockInService.JoinClockIn(data);
    }
}
