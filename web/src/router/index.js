import { createRouter, createWebHistory } from 'vue-router'
import AttendanceCheckInView from '@/views/ManagerView/AttendanceCheckInView'
import LoginView from '@/views/AccessInterfaceView/LoginView'
import RegisterView from '@/views/AccessInterfaceView/RegisterView'
import AttendanceRecordView from '@/views/ManagerView/AttendanceRecordView'
import FaceRecognitionFailureListView from '@/views/ManagerView/FaceRecognitionFailureListView'
import HomeView from '@/views/HomeView'
import FaceInfomationEntryView from '@/views/UserView/FaceInfomationEntryView'
import FaceRecognitionAttendanceView from '@/views/UserView/FaceRecognitionAttendanceView'
import FaceRecognitionFailureRequestView from '@/views/UserView/FaceRecognitionFailureRequestView'
import NoFoundView from '@/views/error/NoFoundView'
import store from '@/store'
const routes = [
  {
    path:'/',
    name:"home",
    component:HomeView,
    meta:{
      requestAuth:false,
    }//判断是否需要授权
  },
  //管理人员路由
    {
      path:'/attendance_check_in_index/',
      name:"attendance_check_in_index",
      component:AttendanceCheckInView,
      meta:{
        requestAuth:true,
      }//判断是否需要授权
    },

    {
      path:'/attendance_record/',
      name:"attendance_record",
      component:AttendanceRecordView,
      meta:{
        requestAuth:true,
      }//判断是否需要授权
    },
  
    {
      path:'/face_recognition_failure_list/',
      name:"face_recognition_failure_list",
      component:FaceRecognitionFailureListView,
      meta:{
        requestAuth:true,
      }//判断是否需要授权
    },

     //用户人员路由
    {
      path:'/face_info_mation_entry/',
      name:"face_info_mation_entry",
      component:FaceInfomationEntryView,
      meta:{
        requestAuth:true,
      }//判断是否需要授权
    },

    {
      path:'/face_recoginition_attendance/',
      name:"face_recoginition_attendance",
      component:FaceRecognitionAttendanceView,
      meta:{
        requestAuth:true,
      }//判断是否需要授权
    },
  
    {
      path:'/face_recoginition_failuer_request/',
      name:"face_recoginition_failuer_request",
      component:FaceRecognitionFailureRequestView,
      meta:{
        requestAuth:true,
      }//判断是否需要授权
    },




    {
      path:'/login/',
      name:"login",
      component:LoginView,
      meta:{
        requestAuth:false,
      }//判断是否需要授权
    },
  
    {
      path:'/register/',
      name:"register",
      component:RegisterView,
      meta:{
        requestAuth:false,
      }//判断是否需要授权
    },
  
    {
      path:'/404/',
      name:"404",
      component:NoFoundView,
      meta:{
        requestAuth:false,
      }//判断是否需要授权
    },

    {
      path:"/:catchAll(.*)",//任意不属于上面路径的字符直接跳转到404
      redirect:"/404/",
      meta:{
        requestAuth:false,
      }//判断是否需要授权
    }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
router.beforeEach((to, from, next) => {

  let flag = 1;
  const jwt_token = localStorage.getItem("jwt_token");
  console.log(jwt_token);
  if (jwt_token) {
    store.commit("updateToken", jwt_token);
    store.dispatch("getinfo", {
      success() {
      },
      error() {
        alert("token无效请重新登录");
        router.push({ name: 'login' });
      }
    })
  } else {
    flag = 2;
  }

  if (to.meta.requestAuth && !store.state.user.is_login) {
    if (flag === 1) {
      next();
    } else {
      next({name: "login"});
    }
  } else {
    next();
  }
})
