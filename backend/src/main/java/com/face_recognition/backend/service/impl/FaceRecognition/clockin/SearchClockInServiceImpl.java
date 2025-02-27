package com.face_recognition.backend.service.impl.FaceRecognition.clockin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.face_recognition.backend.mapper.MissionMapper;
import com.face_recognition.backend.mapper.UserMissionRelationMapper;
import com.face_recognition.backend.pojo.Mission;
import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.pojo.UserMissionRelation;
import com.face_recognition.backend.service.FaceRecognition.clockin.SearchClockInService;
import com.face_recognition.backend.utils.CheckToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchClockInServiceImpl implements SearchClockInService {

    @Autowired
    MissionMapper missionMapper;
    @Autowired
    UserMissionRelationMapper userMissionRelationMapper;

    @Override
    public List<Mission> SearchClockIn() {
        User user= CheckToken.GetLoginUser().getUser();
        QueryWrapper<UserMissionRelation> queryWrapper=new QueryWrapper<>();
        QueryWrapper<Mission> missionQueryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        List<Mission> missions=new ArrayList<>();
        List<UserMissionRelation> userMissionRelations= userMissionRelationMapper.selectList(queryWrapper);
        for(UserMissionRelation userMissionRelation: userMissionRelations){
           Integer missionId= userMissionRelation.getMissionId();
           missionQueryWrapper.eq("id",missionId);
           missions.add(missionMapper.selectOne(missionQueryWrapper));
        }
        return missions;
    }
}
