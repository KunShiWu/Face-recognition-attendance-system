package com.face_recognition.backend.controller.FaceRecognition;

import com.face_recognition.backend.service.FaceRecognition.FaceRecognitionMissionPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FaceRecognitionMissionPostController {
    @Autowired
    FaceRecognitionMissionPostService faceRecognitionMissionPostService;

    @PostMapping("/facerecognition/mission/add/")
    public Map<String ,String > TaskPost(@RequestParam Map<String,String> data)
    {
        return faceRecognitionMissionPostService.TaskAdd(data);
    }



}
