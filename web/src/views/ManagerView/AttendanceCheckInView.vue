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
                         >创建打卡任务</button>
                    </div>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>打卡名称</th>
                                    <th>打卡简介</th>
                                    <th>参与人数</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr >
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                    <td>
                                        <button type="button" class="btn btn-secondary"   >修改</button>
                                        <button type="button" class="btn btn-danger" >删除</button>
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
            },
            success(resp){
                console.log(resp);
            },
            error(resp){
              console.log(resp);
              error_message.value=resp.error_message;
            }
          })
      }


      return{
        title,
        description,
        name_list,
        AddMission
      }
    }
}
</script>

<style scoped>
.error-message{
    color: red;
}
</style>