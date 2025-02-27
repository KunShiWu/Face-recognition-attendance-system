package com.face_recognition.backend.controller.FaceRecognition.clockin;

import com.face_recognition.backend.pojo.Mission;
import com.face_recognition.backend.service.FaceRecognition.clockin.SearchClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SearchClockInController {

    @Autowired
    SearchClockInService searchClockInService;

    @GetMapping("/facerecognition/clockin/searchclockin/")
    public List<Mission> SearchClockIn (){
        return searchClockInService.SearchClockIn();
    }
}
