<template>


<nav class="navbar  navbar-expand-lg " style="background-color: #d4d3a5;">
  <div class="container">
    <router-link class="nav-link" active-class="active" :to="{ name: 'home' }">Face Recognition</router-link>       
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'face_recognition_failure_list' }">人脸识别打卡</router-link>       
        </li>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'face_info_mation_entry' }">人脸信息录入</router-link>       
        </li>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'face_recoginition_failuer_request' }">人脸识别失败请求</router-link>       

        </li>
      </ul>
      <ul class="navbar-nav">
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

<script></script>


<style scoped></style>