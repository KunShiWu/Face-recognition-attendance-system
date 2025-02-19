<template>
    <BaseCard>
        <div class="row  justify-content-md-center"   >
            <div class="col-3"></div>
                <div class="col-6"> 
                <div class="card ">
                    <div class="card-header">
                        <span style="font-weight: bold; font-style: italic;">人脸识别考勤系统</span>
                    </div>
                    <div class="card-body">
                        <form >
                            <div class="mb-3">
                                <input type="text"
                                v-model="username" 
                                class="form-control"
                                placeholder="用户账号" 
                                id="username" 
                                @keydown.enter="login"
                                >
                            </div>
                            <div class="mb-3">
                                <input type="password"
                                 v-model="password"
                                  class="form-control"
                                   placeholder="用户密码"
                                    id="password"
                                    @keydown.enter="login"
                                    >
                            </div>
                           
                        </form>
                        
                                <div class="card-footer"> 
                                    <div class="error_message">{{ error_message }}</div>
                                    <button type="button" @click="login"  style="width:100%"  class="btn btn-outline-primary  btn_position">登录</button>
                                </div>
                    </div>
                
            </div>

            </div>
            <div class="col-3"></div>
           

        </div>
    
    </BaseCard>
</template>


<script>

import BaseCard from '@/components/BaseCard'
import { useStore } from 'vuex';
import { ref } from 'vue';
import router from '@/router';
export default{
    components:{
        BaseCard
    },
    setup(){
        const store=useStore();
        let error_message=ref('');
        let username=ref('');
        let password=ref('');

        const login =()=>{
            console.log(username.value);
            error_message.value=null;
            store.dispatch("login",{
             
                username:username.value,
                password:password.value,    
                success(){

                    store.dispatch("getinfo",{
                        success(resp){
                            console.log(resp);
                            router.push({name:'home'});
                        },
                        error(){
                            console.log("获取用户信息出错");
                        }
                      
                    })
                  
                },
                error(){
                    if(username.value==='')
                    {
                        error_message.value="用户名不能为空"
                    }else if(password.value==='')
                    {
                        error_message.value="密码不能为空"
                    }else{
                        error_message.value="用户名或者密码错误"
                    }
                }

            })//调用store里面的acctions函数需要用dispatch
        }

        return {
        login,
        username,
        password,
        error_message
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