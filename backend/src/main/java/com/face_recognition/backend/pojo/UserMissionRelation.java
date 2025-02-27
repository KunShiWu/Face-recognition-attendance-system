package com.face_recognition.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMissionRelation {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    private Integer userId;
    private  Integer missionId;
    private String name;
    private String location;
    private Boolean isAdd;
    private Boolean complished;
}
