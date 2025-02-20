package com.face_recognition.backend.service.impl.FaceRecognition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.face_recognition.backend.mapper.MissionMapper;
import com.face_recognition.backend.pojo.Mission;
import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.service.FaceRecognition.GetMissionListService;
import com.face_recognition.backend.utils.CheckToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetMissionListServiceImpl  implements GetMissionListService {

    @Autowired
    MissionMapper missionMapper;
    @Override
    public List<Mission> getmissionlist() {
        User user= CheckToken.GetLoginUser().getUser();
        QueryWrapper<Mission>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        return missionMapper.selectList(queryWrapper);
    }
}
