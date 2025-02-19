package com.face_recognition.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    private  String username;
    private  String password;
    private  String photo;
    private  String name;
    private  String identity;
    private  String identityPhoto;

}
