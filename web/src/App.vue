<template >

      <div class="Administ_background" v-if="Indetify==='Administ'">
        <AdministratorNavBar ></AdministratorNavBar>
        <div class="bottom-right">
          <button
          @click="chance_indetify() " 
          type="button" 
          class="btn btn-outline-warning"
          >CHANGE</button>
        </div>
      </div>

      <div class="user_background" v-else>
        <UserNavbar ></UserNavbar>
        <div class="bottom-right">
          <button
          @click="chance_indetify()" 
          type="button" 
          class="btn btn-outline-warning"
          >CHANGE</button>
        </div>
      </div>
  
  
      <router-view/>
 

  
 
</template>

<script>

// import $ from "jquery"
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap"
import 'bootstrap-icons/font/bootstrap-icons.css'
import UserNavbar from '@/components/UserNavbar'
import AdministratorNavBar from '@/components/AdministratorNavBar'
import {ref} from 'vue'
export default{
  name:"App",
  components:{
    UserNavbar,
    AdministratorNavBar
  },

 

  setup(){


    let Indetify=ref("Administ");

    const chance_indetify=()=>{
        if(Indetify.value==="Administ")
        {
          Indetify.value="user";
          console.log(Indetify.value);
        }
        else if (Indetify.value==="user") {
          Indetify.value="Administ";
          console.log(Indetify.value);
        }
    }
    // $.ajax({
    //   url:"http://127.0.0.1:3000/user/account/token/",
    //   type:"post",
    //   data:{
    //     username:"wsk",
    //     password:"pwsk"
    //   },
    //   success(resp){
    //     console.log(resp);
    //   }
    // }),//登录，


     
    // $.ajax({
    //   url:"http://127.0.0.1:3000/user/account/info/",
    //   type:"get",
    //   headers:{
    //     Authorization:"Bearer "+ "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzc5YjU0YmMyYTM0YTcwYTIwNWVmYTZiYjQ3ZGYxZiIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTczOTQ1MzQyMSwiZXhwIjoxNzQwNjYzMDIxfQ.c8BliSiu0UAXfasiKwgZcjTr6tqHqfnwTbK2ibIf-7c"
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // })

   
    // $.ajax({
    //   url:"http://127.0.0.1:3000/user/account/register/",
    //   type:"post",
    //   data:{
    //     username:"dd",
    //     identity:"打卡人员",
    //     password:"pdd123",
    //     confirmedPassword:"pdd123"
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp)
    //   {
    //     console.log(resp);
    //   }
    // })




    return{
      chance_indetify,
      Indetify
    }
  }
}

</script>
<style>

body{
    background-image: url("@/assess/background.jpg");
    background-size: cover;
    background-attachment: fixed;
}
 
  .bottom-right {
    position: fixed; /* 固定定位，随屏幕滚动不变 */
    bottom: 100px;    /* 距离屏幕底部 10px */
    right: 50px;     /* 距离屏幕右侧 10px */
    padding: 10px;   /* 内边距 */
    border-radius: 5px; /* 圆角边框 */

  }

</style>
