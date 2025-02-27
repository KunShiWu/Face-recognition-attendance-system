package com.face_recognition.backend.service.impl.FaceRecognition.Mission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.face_recognition.backend.mapper.MissionMapper;
import com.face_recognition.backend.mapper.UserMissionRelationMapper;
import com.face_recognition.backend.pojo.Mission;
import com.face_recognition.backend.pojo.UserMissionRelation;
import com.face_recognition.backend.service.FaceRecognition.Mission.RemoveMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveMissionServiceImpl implements RemoveMissionService {

    @Autowired
    MissionMapper missionMapper;
    @Autowired
    UserMissionRelationMapper userMissionRelationMapper;
    @Override
    public Map<String, String> removeMission(Integer missionId) {

        QueryWrapper<Mission>   queryWrapper=new QueryWrapper<>();
        QueryWrapper<UserMissionRelation> userMissionRelationQueryWrapper=new QueryWrapper<>();

        Map<String,String> map=new HashMap<>();
        Mission mission=missionMapper.selectById(missionId);
        if(mission==null)
        {
            map.put("error_message","任务不存在或者已被删除");
        }
        userMissionRelationQueryWrapper.eq("mission_id",missionId);
        queryWrapper.eq("id",missionId);
        missionMapper.delete(queryWrapper);
        userMissionRelationMapper.delete(userMissionRelationQueryWrapper);
        map.put("error_message","success");
        return  map;
    }
}
