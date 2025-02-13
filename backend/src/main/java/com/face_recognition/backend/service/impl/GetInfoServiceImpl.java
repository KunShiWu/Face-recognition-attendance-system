package com.face_recognition.backend.service.impl;

import com.face_recognition.backend.service.GetInfoService;
import org.springframework.stereotype.Service;

@Service
public class GetInfoServiceImpl implements GetInfoService {
    @Override
    public String getinfo() {
        return "获得信息";
    }
}
