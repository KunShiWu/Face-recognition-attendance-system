package com.face_recognition.backend.service.FaceRecognition;

import java.util.Map;

public interface FaceInfomationEntryService {
    Map<String,String> faceinfomationentry(String Base64Data,String name);
}
