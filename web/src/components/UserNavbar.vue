<template>


<nav class="navbar  navbar-expand-lg " style="background-color: #d4d3a5;">
  <div class="container">
    <router-link class="navbar-brand" active-class="active" :to="{ name: 'home' }">Face Recognition</router-link>       
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'face_recoginition_attendance' }">打卡活动参与</router-link>       
        </li>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'face_info_mation_entry' }">人脸信息录入</router-link>       
        </li>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'face_recoginition_failuer_request' }">人脸识别失败请求</router-link>       

        </li>
      </ul>
      <ul class="navbar-nav " v-if="$store.state.user.is_login &&  $store.state.user.identity==='打卡人员'">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          {{ $store.state.user.username }}   +   {{  $store.state.user.name }}
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
          </ul>
        </li>
      </ul>
      <ul class="navbar-nav" v-else>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'login' }">登录  </router-link>        
        </li>  <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'register' }">注册</router-link>        
        </li>

      </ul>
    </div>
  </div>
</nav>


</template>

<script>
import { useStore } from 'vuex';

export default{
  setup(){
    const store=useStore();
    const logout = () =>{
      store.dispatch("logout");
    }
    return {
      logout
    }
  }

}
</script>


<style scoped></style>