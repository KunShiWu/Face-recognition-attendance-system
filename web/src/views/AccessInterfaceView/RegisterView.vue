<template>
  <BaseCard>

<div class="row justify-content-md-center" >
    <div class="col-4"></div>
        <div class="col-4"> 
        <div class="card text-center">
            <div class="card-header">
                <span style="font-weight: bold; font-style: italic;">人脸识别考勤系统</span>
            </div>
            <div class="card-body">
                <form>
                    <div class="mb-3">
                        <input 
                        type="text"
                        class="form-control"
                        placeholder="注册账号" 
                        id="username" 
                        v-model="username"
                        >
                    </div>
                    <div class="mb-3">
                        <input 
                        type="password" 
                        class="form-control" 
                        placeholder="注册密码" 
                        id="password"
                        v-model="password"
                        >
                    </div>
                    <div class="mb-3">
                        <input 
                        type="password" 
                        class="form-control" 
                        placeholder="确认密码" 
                        id="Confirmedpassword"
                        v-model="confirmedPassword"
                        >
                    </div>
                  
                </form>
            </div>
            <div class="card-footer"> 
                <div class="error_message">{{ error_message }}</div>
                <button type="button" @click="register_administ" class="btn btn-outline-primary ms-2">管理员注册</button>
                <button type="button" @click="register_user" class="btn btn-outline-secondary ms-2">用户注册</button>
            </div>
    </div>

    </div>
    <div class="col-4"></div>
   

</div>

</BaseCard>
</template>


<script>
import $ from 'jquery'
import router from '@/router';
import BaseCard from '@/components/BaseCard'
import { ref } from 'vue';

export default{
    components:{
        BaseCard
    },
    setup(){

     let username=ref("");
     let password=ref('');
     let confirmedPassword=ref("");
     let error_message=ref("");

     const register_administ=()=>{
        console.log("register_administ");
         $.ajax({
             url:"http://127.0.0.1:3000/user/account/register/",
             type:"post",
             data:{
                 username:username.value,
                 identity:"考勤人员",
                 password:password.value,
                 confirmedPassword:confirmedPassword.value
             },
             success(resp){
                 if(resp.error_message==="success"){
                    console.log(resp);
                     router.push({name:"login"});
                 }else {
                     error_message.value=resp.error_message;
                 }
             },
             error(resp){
                 error_message.value=resp.error_message;
             }
         })
     }

     
     const register_user=()=>{

        console.log("register_user");
         $.ajax({
             url:"http://127.0.0.1:3000/user/account/register/",
             type:"post",
             data:{
                 username:username.value,
                 identity:"打卡人员",
                 password:password.value,
                 confirmedPassword:confirmedPassword.value
             },
             success(resp){
                 if(resp.error_message==="success"){
                    console.log(resp);
                     router.push({name:"login"});
                 }else {
                     error_message.value=resp.error_message;
                 }
             },
             error(resp){
                 error_message.value=resp.error_message;
             }
         })
     }
     return {
         username,
         password,
         confirmedPassword,
         error_message,
         register_administ,
         register_user
     }
 }
}
</script>

<style scoped>
.error_message{
    color: red; 
    font-size: 18px; 
    font-family: 'Arial', 
    sans-serif; 
    font-weight: bold;
    text-align: center;
}
</style>