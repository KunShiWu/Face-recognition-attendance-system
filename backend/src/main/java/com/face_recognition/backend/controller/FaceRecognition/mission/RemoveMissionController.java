package com.face_recognition.backend.controller.FaceRecognition.mission;

import com.face_recognition.backend.service.FaceRecognition.Mission.RemoveMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoveMissionController {
    @Autowired
    RemoveMissionService removeMissionService;

    @PostMapping("/facerecognition/mission/remove/")
    public Map<String,String> removeMission(@RequestParam Map<String,String >data)
    {
        Integer missionId=Integer.parseInt(data.get("mission_id"));
        return removeMissionService.removeMission(missionId);
    }
}
