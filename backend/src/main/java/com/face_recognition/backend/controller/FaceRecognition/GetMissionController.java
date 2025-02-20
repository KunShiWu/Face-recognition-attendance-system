package com.face_recognition.backend.controller.FaceRecognition;

import com.face_recognition.backend.pojo.Mission;
import com.face_recognition.backend.service.FaceRecognition.GetMissionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetMissionController {

    @Autowired
    public GetMissionListService getMissionListService;


    @GetMapping("/facerecognition/mission/getlist/")
    public List<Mission> getmissionlist(){
        return getMissionListService.getmissionlist();
    }
}
