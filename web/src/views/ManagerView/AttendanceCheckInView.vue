<template>











  <BaseCard>
    <div class="container">
        <div class="row">
          <div class="col-3">
            <div class="card" style="margin-top:20px">
                    <div class="card-body">
                        <img src="@/assess/face.jpg" style="width: 100% ;border-radius: 50%;" alt="">
                    </div>
                </div>
          </div>

            <div class="col-9">
                <div class="card" style="margin-top:20px">
                    <div class="card-header">
                        <span style="font-size: 130%;">我的打卡任务</span>
                        <button type="button" 
                         class="btn btn-outline-primary float-end"
                         data-bs-toggle="modal" data-bs-target="#create_mission" data-bs-whatever="@fat"

                         @click=" getLocation()"
                         >创建打卡任务 </button>
                    </div>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>打卡名称</th>
                                    <th>打卡简介</th>
                                    <th>参与人数</th>
                                    <th>打卡邀请码</th>
                                    <th>修改时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(mission) in missions" :key="mission.id"  >
                                    <td>{{ mission.title }}</td>
                                    <td>{{ mission.description}}</td>
                                    <td>{{ mission.number}}</td>
                                    <td>{{ mission.randNumber}}</td>
                                    <td>{{ mission.modifytime}}</td>
                                    <td>
                                        <button type="button" class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target="'#update-mission-modal-'+mission.id" data-bs-whatever="@mdo" @click="getLocation()"   >修改</button>
                                        
                                          <!--修改任务的Model-->
                                          <div class="modal fade" :id="'update-mission-modal-'+mission.id" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                              <div class="modal-content">
                                                <div class="modal-header">
                                                  <h1 class="modal-title fs-5" id="exampleModalLabel">新的打卡任务</h1>
                                                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                  <form>
                                                    <div class="mb-3">
                                                      <label for="recipient-name"  class="col-form-label">打卡名称</label>
                                                      <input  v-model="mission.title"  :placeholder=mission.title  type="text"    class="form-control" id="recipient-name">
                                                    </div>
                                                    <div class="mb-3">
                                                      <label for="recipient-name" class="col-form-label">打卡简介</label>
                                                      <input  v-model="mission.description" :placeholder=mission.description     type="text" class="form-control" id="recipient-name">
                                                    </div>
                                                    <div class="mb-3">
                                                      <label for="message-text" class="col-form-label">打卡人员名单</label>
                                                      <textarea  style="line-height: 3;"  :placeholder=mission.nameList  v-model="mission.nameList"    class="form-control" id="message-text"></textarea>
                                                    </div>
                                     
                                                  </form>
                                                </div>
                                                <div class="modal-footer">
                                                  <div class="error-message">{{ mission.error_message }}</div>
                                                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">返回</button>
                                                  <button type="button" class="btn btn-primary " @click="UpdateMission(mission); " data-bs-dismiss="modal">确定</button>
                                                  <span>{{ address}}</span>
                                                </div>
                                              </div>
                                            </div>
                                          </div>
                                          <!--Model-->
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        <button type="button"   class="btn btn-danger" @click=removeMission(mission.id)  >删除</button>
                                    </td>
                            
                                </tr>
                            </tbody>
                        </table>    
                    </div>
                </div>
            </div>
        </div>
    </div>
  
  </BaseCard>






<!--创建打卡任务的Model-->
<div class="modal fade" id="create_mission" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">新的打卡任务</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form>
          <div class="mb-3">
            <label for="recipient-name"  class="col-form-label">打卡名称</label>
            <input  v-model="title"   type="text" class="form-control" id="recipient-name">
          </div>
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">打卡简介</label>
            <input  v-model="description"  type="text" class="form-control" id="recipient-name">
          </div>
          <div class="mb-3">
            <label for="message-text" class="col-form-label">打卡人员名单</label>
            <textarea  style="line-height: 3;"  v-model="name_list" class="form-control" id="message-text"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <div class="error-message">{{ error_message }}</div>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">返回</button>
        <button type="button" class="btn btn-primary " @click="AddMission" data-bs-dismiss="modal">创建</button>
        <span>{{ address}}</span>
      </div>
    
    </div>
  </div>
</div>
<!--Model-->
</template>


<script>

import BaseCard from '@/components/BaseCard'
import { ref } from 'vue';
import $ from 'jquery'
import { useStore } from 'vuex';

export default{
    components:{
        BaseCard
    },
    setup(){


      const store=useStore();
      let title=ref(null);
      let description=ref(null);
      let name_list=ref();
      let error_message=ref();
      let missions=ref([]);
      let address=ref();
      let longitude=ref();
      let latitude=ref();
      const refresh_mission=()=>{

        $.ajax({
          url:"http://127.0.0.1:3000/facerecognition/mission/getlist/",
          type:"get",
          headers:{
              Authorization:"Bearer "+ store.state.user.token
            },
          success(resp){
              missions.value=resp;
          }
        })
      
      }
      const AddMission =()=>{
        error_message.value='';
          $.ajax({
            url:"http://127.0.0.1:3000/facerecognition/mission/add/",
            type:"POST",
            headers:{
              Authorization:"Bearer "+ store.state.user.token
            },
            data:{
              title:title.value,
              description:description.value,
              name_list:name_list.value,
              longitude:longitude.value,
              latitude:latitude.value,
              location: address.value 

            },
            success(resp){
                console.log(resp);
                refresh_mission();
            },
            error(resp){
              console.log(resp);
              error_message.value=resp.error_message;
            }
          })
      }

      const UpdateMission=(mission)=>{

        $.ajax({
          url:"http://127.0.0.1:3000/facerecognition/mission/update/",
          type:"post",
          data:{
            mission_id:mission.id,
            title:mission.title,
            description:mission.description,
            name_list:mission.nameList,
            longitude:longitude.value,
            latitude:latitude.value,
            location: address.value 
          },
          headers:{
              Authorization:"Bearer "+ store.state.user.token
            },
            success(resp){
              mission.title='';
              mission.description=''
              mission.name_list=''
              console.log(resp);
              refresh_mission();
            },
            error(resp){
              mission.error_message=resp.error_message;
            }
        })

      }
      const removeMission=(mission_id)=>{
        $.ajax({
          url:"http://127.0.0.1:3000/facerecognition/mission/remove/",
          type:"post",
          data:{
            mission_id
          },
          headers:{
              Authorization:"Bearer "+ store.state.user.token
            },
            success(resp){
              console.log(resp);
              refresh_mission();
            },
       
        })
      }
      function getLocation() {//实现获取打卡经纬度
          if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
              position => {
                 latitude.value = position.coords.latitude;
                 longitude.value = position.coords.longitude;
                // 调用后端接口，将经纬度发送给后端

                fetch(`https://nominatim.openstreetmap.org/reverse?lat=${latitude.value}&lon=${longitude.value}&format=json`)
                  .then(response => response.json())
                  .then(data => {
                     address.value = data.display_name;
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

       

      
      // const setPlaceholderValue=(indx,mission)=>{
      //   mission.nameList=missions.value[indx].nameList
      // }
      
      refresh_mission();


      return{
        title,
        description,
        name_list,
        AddMission,
        refresh_mission,
        missions,
        UpdateMission,
        removeMission,
        // setPlaceholderValue
        address,
        getLocation,
        longitude,
        latitude
      }
    }
}
</script>

<style scoped>
.error-message{
    color: red;
}
</style>