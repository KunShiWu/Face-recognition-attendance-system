package com.face_recognition.backend.service.FaceRecognition.clockin;

import java.util.Map;

public interface RemoveClockInService {
    Map<String,String> removeClockIn(Integer missionId);
}
