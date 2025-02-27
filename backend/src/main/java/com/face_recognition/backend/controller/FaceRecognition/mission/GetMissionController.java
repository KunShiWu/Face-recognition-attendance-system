package com.face_recognition.backend.controller.FaceRecognition.mission;

import com.face_recognition.backend.pojo.Mission;
import com.face_recognition.backend.service.FaceRecognition.Mission.GetMissionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetMissionController {

    @Autowired
    public GetMissionListService getMissionListService;


    @GetMapping("/facerecognition/mission/getlist/")
    public List<Mission> getmissionlist(){
        return getMissionListService.getmissionlist();
    }
}
