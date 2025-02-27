package com.face_recognition.backend.service.impl.FaceRecognition.Mission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.face_recognition.backend.mapper.MissionMapper;
import com.face_recognition.backend.pojo.Mission;
import com.face_recognition.backend.pojo.User;
import com.face_recognition.backend.service.FaceRecognition.Mission.FaceRecognitionMissionPostService;
import com.face_recognition.backend.utils.CheckToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class FaceRecognitionMissionPostServiceImpl implements FaceRecognitionMissionPostService {

   @Autowired
    MissionMapper  missionMapper;
    @Override
    public Map<String, String> TaskAdd(Map<String, String> data) {
        String title=data.get("title");
        String description=data.get("description");
        String nameInput=data.get("name_list");
        String longitude=data.get("longitude");
        String latitude=data.get("latitude");
        String location=data.get("location");

        String[] names = nameInput.split("[#;,\\s]+");
        List<String> nameList = new ArrayList<>();
        for (String name : names) {
            if (!name.isEmpty()) { // 防止空字符串加入列表
                nameList.add(name);
            }
        }
        StringBuilder NameList = new StringBuilder();
        for (int i = 0; i < nameList.toArray().length; i++) {

            if(i!=nameList.toArray().length-1)
                NameList.append(nameList.get(i)).append('#');
            else
                NameList.append(nameList.get(i));
        }
        String namelist= String.valueOf(NameList);

        User user= CheckToken.GetLoginUser().getUser();

        Map<String,String> map=new HashMap<>();
        if(title==null||title.length()==0)
        {
            map.put("error_message","打卡名称不能为空");
            return map;
        }
        if(title.length()>100){
            map.put("error_message","打卡名称不能过长");
            return map;
        }
        if(description==null||description.length()==0)
        {
            description="任务未描述";
        }
        if(description.length()>300)
        {
            map.put("error_message","任务描述不能过长");
            return map;
        }


        QueryWrapper<Mission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        if(missionMapper.selectCount(queryWrapper)>=10){
            map.put("error_message","每个用户最多只能创建十个打卡任务");
            return map;
        }

        Set<Integer> uniqueNumbers = ThreadLocalRandom.current()
                .ints(100000, 1000000) // 指定范围 [100000, 999999]
                .distinct()
                .limit(1)
                .boxed()
                .collect(Collectors.toSet());

        Integer rand_num=uniqueNumbers.hashCode();
        Date nowtime=new Date();
        Mission mission=new Mission(
                null,
                user.getId(),
                title,
                description,
                namelist,
                nowtime,
                nowtime,
                nameList.toArray().length,
                rand_num,
                longitude,
                latitude,
                location
        );

        missionMapper.insert(mission);
        map.put("error_message","success");
        return map;
    }
}
