package com.face_recognition.backend.service.user.account;

import java.util.Map;

public interface RegisterService {
    Map<String,String> register(String username,String identity,String password,String confirmedPassword);
}
