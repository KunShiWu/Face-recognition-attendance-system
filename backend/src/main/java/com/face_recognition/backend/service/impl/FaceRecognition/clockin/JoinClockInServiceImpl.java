package com.face_recognition.backend.service.impl.FaceRecognition.clockin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.face_recognition.backend.mapper.MissionMapper;
import com.face_recognition.backend.mapper.UserMissionRelationMapper;
import com.face_recognition.backend.pojo.Mission;
import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.pojo.UserMissionRelation;
import com.face_recognition.backend.service.FaceRecognition.clockin.JoinClockInService;
import com.face_recognition.backend.utils.CheckToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JoinClockInServiceImpl implements JoinClockInService {

    @Autowired
    MissionMapper missionMapper;
    @Autowired
    UserMissionRelationMapper userMissionRelationMapper;

    @Override
    public Map<String, String> JoinClockIn(Map<String,String> data) {
        String randNumber=data.get("rand_number");

        Map<String,String > map=new HashMap<>();
        if(randNumber==null){
            map.put("error_message","输入的邀请码不能为空");
            return map;
        }

        randNumber=randNumber.trim();
        QueryWrapper<Mission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("rand_number",Integer.parseInt(randNumber));
        if(!queryWrapper.isEmptyOfEntity()){
            map.put("error_message","输入的邀请码无效或者已过期");
            return map;
        }
        User user= CheckToken.GetLoginUser().getUser();

        Mission mission=  missionMapper.selectOne(queryWrapper);
        UserMissionRelation userMissionRelation=new UserMissionRelation(
                null,
                user.getId(),
                mission.getId(),
                user.getName(),
                mission.getLocation(),
                true,
                false
        );
        userMissionRelationMapper.insert(userMissionRelation);
        map.put("error_message","success");
        return map;
    }
}
