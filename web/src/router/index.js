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
const routes = [
  {
    path:'/',
    name:"home",
    component:HomeView
  },
  //管理人员路由
    {
      path:'/attendance_check_in_index/',
      name:"attendance_check_in_index",
      component:AttendanceCheckInView,
    },

    {
      path:'/attendance_record/',
      name:"attendance_record",
      component:AttendanceRecordView,
    },
  
    {
      path:'/face_recognition_failure_list/',
      name:"face_recognition_failure_list",
      component:FaceRecognitionFailureListView,
    },

     //用户人员路由
    {
      path:'/face_info_mation_entry/',
      name:"face_info_mation_entry",
      component:FaceInfomationEntryView,
    },

    {
      path:'/face_recoginition_attendance/',
      name:"face_recoginition_attendance",
      component:FaceRecognitionAttendanceView,
    },
  
    {
      path:'/face_recoginition_failuer_request/',
      name:"face_recoginition_failuer_request",
      component:FaceRecognitionFailureRequestView,
    },




    {
      path:'/login/',
      name:"login",
      component:LoginView,
    },
  
    {
      path:'/register/',
      name:"register",
      component:RegisterView,
    },
  
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
