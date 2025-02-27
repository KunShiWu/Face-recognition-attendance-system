package com.face_recognition.backend.controller.FaceRecognition.mission;

import com.face_recognition.backend.service.FaceRecognition.Mission.UpdateMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateMissionController {

    @Autowired
    UpdateMissionService updateMissionService;


    @PostMapping("/facerecognition/mission/update/")
    public Map<String,String > updateMission(@RequestParam Map<String,String> data){
        return updateMissionService.updateMission(data);
    }

}
