<template>


<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container">
    <router-link class="navbar-brand" active-class="active" :to="{ name: 'home' }">Face Recognition</router-link>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'attendance_check_in_index' }">考勤打卡</router-link>        
        </li>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'attendance_record' }">考勤数据</router-link>        
        </li>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{ name: 'face_recognition_failure_list' }">人脸识别失败名单</router-link>        
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