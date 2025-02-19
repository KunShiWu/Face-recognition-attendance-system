package com.face_recognition.backend.service.impl.FaceRecognition;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.face_recognition.backend.mapper.UserMapper;
import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.service.FaceRecognition.FaceInfomationEntryService;
import com.face_recognition.backend.utils.CheckToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FaceInfomationEntryServiceImpl  implements FaceInfomationEntryService
{

    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, String> faceinfomationentry(String Base64Data,String name) {
        User user= CheckToken.GetLoginUser().getUser();

        Map<String,String> map=new HashMap<>();

        String cleanBase64Data = Base64Data.replace("data:image/jpeg;base64,", "");
        System.out.println(cleanBase64Data);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId())  // 条件：更新指定 id 的记录
                .set("identity_photo",cleanBase64Data );  // 设置要更新的字段
        updateWrapper.eq("id", user.getId()).set("name",name);
        userMapper.update(null,updateWrapper);
        map.put("error_message","success");
        return map;
    }
}
