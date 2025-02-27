package com.face_recognition.backend.service.impl.FaceRecognition.clockin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.face_recognition.backend.mapper.UserMissionRelationMapper;
import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.pojo.UserMissionRelation;
import com.face_recognition.backend.service.FaceRecognition.clockin.RemoveClockInService;
import com.face_recognition.backend.utils.CheckToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveClockInServiceImpl implements RemoveClockInService {

    @Autowired
    UserMissionRelationMapper userMissionRelationMapper;
    @Override
    public Map<String, String> removeClockIn(Integer missionId) {
        User user= CheckToken.GetLoginUser().getUser();


        QueryWrapper<UserMissionRelation> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mission_id",missionId);
        Map<String,String > map=new HashMap<>();

        if(!queryWrapper.isEmptyOfEntity()){
            map.put("error_message","该任务不存在或已被删除");
        }
        userMissionRelationMapper.delete(queryWrapper);
        map.put("error_message","success");
        return map;
    }
}
