<template>
  <div class="container">
        <div class="row">
          <div class="col-3">
            <div class="card" style="margin-top:20px">
                    <div class="card-body">
                        <img  :src="'data:image/png;base64,'+$store.state.user.identity_photo"  style="width: 100% ;border-radius: 50%;" alt="">
                    </div>
                </div>
          </div>

            <div class="col-9">
                <div class="card" style="margin-top:20px">
        
                    <div class="card-header" style=" display: flex;   justify-content: flex-end;">
                      <span  style="font-size: 130%;  margin-right: 20px; font-style: italic"  >请输入打卡邀请码</span>
                      <div style="color:crimson"> {{ error_message}}</div>
                      <form class="d-flex " @submit.prevent="JoinClockIn">
                          <input v-model="rand_number" style="width: 50%;" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                          <button type="submit"  class="btn btn-primary ml-2">加入</button>
                      </form>
                    </div>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                                <tr >
                                    <th>打卡名称</th>
                                    <th>打卡简介</th>
                                    <th>打卡地点</th>
                                    <th>开始打卡</th>
                                    <th>删除打卡</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(mission) in missions" :key="mission.id" >
                                    <td >{{ mission.title}}</td>
                                    <td>{{ mission.description}}</td>
                                    <td>{{ mission.location.split(',')[0]}}</td>

                                    <td>
                                        <button type="button" class="btn btn-info"  >开始</button>
                                      
                                    </td>
                                    <td>  
                                      <button type="button" @click="removeClockIn(mission.id)"  class="btn btn-danger">删除 </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>    
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
import  $ from 'jquery'
import {ref} from 'vue'
import { useStore } from 'vuex';
export default{ 
  components:{
    
    },
      setup(){

        const store=useStore();
        let rand_number=ref();
        let error_message=ref();

        let missions=ref([]);
          function getLocation() {
          if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
              position => {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;
                // 调用后端接口，将经纬度发送给后端


                fetch(`https://nominatim.openstreetmap.org/reverse?lat=${latitude}&lon=${longitude}&format=json`)
                  .then(response => response.json())
                  .then(data => {
                    const address = data.display_name;
                    console.log("地址名称:", address);
                  })
                  .catch(error => console.error("请求错误:", error));

                
              },
              error => {
                console.error("Error getting location: ", error);
              }
            );
          } else {
            alert("Geolocation is not supported by this browser.");
          }
        }
        const JoinClockIn=()=>{
            $.ajax({
              url:"http://127.0.0.1:3000/facerecognition/clockin/joinclockin/",
              type:"POST",
              data:{
                rand_number:rand_number.value
              },
              headers:{
                Authorization:"Bearer "+ store.state.user.token
              },
              success(resp){
                if(resp.error_message==="success"){
                  
                    refresh_missions();
                
                    console.log(resp);
                 }else {
                     error_message.value=resp.error_message;
                 }
   
              },

            })
        }

        const refresh_missions=()=>{
          
          $.ajax({
              url:"http://127.0.0.1:3000/facerecognition/clockin/searchclockin/",
              type:"get",
              headers:{
                Authorization:"Bearer "+ store.state.user.token
              },
              success(resp){
                missions.value=resp;
                console.log(resp);
              },
              error(resp){
                console.log(resp);
              }

            })

        }
    

        const removeClockIn=(mission_id)=>{
          $.ajax({
              url:"http://127.0.0.1:3000/facerecognition/clockin/removeclockin/",
              type:"post",
              data:{
                mission_id
              },
              headers:{
                Authorization:"Bearer "+ store.state.user.token
              },
              success(resp){
                refresh_missions();
                console.log(resp);
              },
              error(resp){
                console.log(resp);
              }

            })
        }
        refresh_missions();
        return{
          rand_number,
          error_message,
          getLocation,
          JoinClockIn,
          refresh_missions,
          missions,
          removeClockIn
        }
    }
}

</script>

<style scoped>
</style>