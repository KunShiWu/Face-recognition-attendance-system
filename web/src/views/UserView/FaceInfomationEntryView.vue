<template>


<div class="container text-center">
  <div class="row justify-content-md-center">
    <div class="col col-lg-2">

    </div>
    <div class="col-md-auto">
      <div class="card text-center">
        <div class="card-header">
          人脸信息录入
        </div>
        <div class="card-body">
          <div class="face-recognition">

            <img v-if="capturedImage" :src="capturedImage" alt="Captured Face" />
            <!-- 视频预览 -->

            <div style="margin: 20px auto ;">
            <button v-if="capturedImage"  class="btn btn-warning"  @click="againCapture">再录一次</button>
            <button v-if="capturedImage"  class="btn btn-success"  style="margin-left: 30px;" data-bs-toggle="modal" data-bs-target="#name">录入无误</button>
            </div>
            <video     ref="videoRef" width="360" height="400" autoplay></video>
            
            <!-- 检测画布（隐藏） -->
            <canvas  ref="canvasRef" style="display: none;"></canvas>
            
            <!-- 状态显示 -->
            <div v-if="statusMessage" class="status">{{ statusMessage }}</div>
            <!-- 捕获结果 -->
         
            <!-- 控制按钮 -->
          </div>
        </div>
        <div class="card-footer text-body-secondary">
          <button v-if="capturedImage===null"  @click="startCapture" class="btn btn-primary" :disabled="isCapturing">开始录入</button>
        </div>
    </div>
    </div>
    <div class="col col-lg-2">
  
    </div>
  </div>
</div>
  



<div class="modal fade" id="name" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel" >请输入您的真实姓名</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <input v-model="names" type="text" class="form-control" placeholder="中文姓名" aria-label="Username">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
        <button type="button" @click="confirmedCapture" class="btn btn-primary"   data-bs-dismiss="modal" >确定</button>
      </div>
    </div>
  </div>
</div>
    
  </template>
  
  <script setup>
  import { ref, onMounted, onBeforeUnmount } from 'vue'
  import * as faceapi from 'face-api.js'
  import $ from 'jquery'
  import {useStore} from 'vuex'
  import router from '@/router/index';

  // 响应式变量
  const store=useStore();
  const videoRef = ref(null)
  const canvasRef = ref(null)
  const capturedImage = ref(null)
  const statusMessage = ref('点击开始录入')
  const isCapturing = ref(false)
  let detectionInterval = null
  let names= ref(null)
  
  // 初始化摄像头
  async function initCamera() {
    try {
      const stream = await navigator.mediaDevices.getUserMedia({
        video: { width: 360, height: 400 }
      })
      videoRef.value.srcObject = stream
    } catch (error) {
      statusMessage.value = '摄像头访问失败，请检查权限'
      console.error('摄像头错误:', error)
    }
  }
  
  // 加载人脸模型
  async function loadModels() {
    try {
      await faceapi.nets.tinyFaceDetector.loadFromUri('/models')
      await faceapi.nets.faceLandmark68Net.loadFromUri('/models')
      statusMessage.value = '模型加载完成'
    } catch (error) {
      statusMessage.value = '模型加载失败'
      console.error('模型加载错误:', error)
    }
  }
  
  // 人脸检测逻辑
  async function detectFace() {
    if (!videoRef.value || videoRef.value.paused) return
    
    // 人脸检测
    const detections = await faceapi.detectAllFaces(
      videoRef.value,
      new faceapi.TinyFaceDetectorOptions()
    ).withFaceLandmarks()
  
    // 绘制检测结果到隐藏canvas
    const canvas = canvasRef.value
    faceapi.matchDimensions(canvas, videoRef.value)
    const resizedDetections = faceapi.resizeResults(detections, videoRef.value)
    faceapi.draw.drawDetections(canvas, resizedDetections)
  
    // 检测到单个人脸时捕获图像
    if (detections.length === 1) {
      captureFrame()
      stopCapture()
    }
  }
  
  // 捕获帧并转为Base64
  function captureFrame() {
    const canvas = document.createElement('canvas')
    const video = videoRef.value
    
    // 设置canvas尺寸与视频一致
    canvas.width = video.videoWidth
    canvas.height = video.videoHeight
    
    // 绘制当前视频帧
    canvas.getContext('2d').drawImage(video, 0, 0)
    
    // 转换为Base64
    capturedImage.value = canvas.toDataURL('image/jpeg', 0.8)
    console.log( capturedImage.value );
  }
  
  // 开始捕获流程
  function startCapture() {
    
    isCapturing.value = true
    statusMessage.value = '正在检测人脸...'
    detectionInterval = setInterval(detectFace, 500)
  }
  
  // 停止捕获
  function stopCapture() {
    clearInterval(detectionInterval)
    isCapturing.value = false
    statusMessage.value = '捕获完成!'
    
    // 停止摄像头流
    const stream = videoRef.value.srcObject
    if (stream) {
      stream.getTracks().forEach(track => track.stop())
    }
  }


  function confirmedCapture () {
    
    console.log(names.value);
    $.ajax({
        url:'http://127.0.0.1:3000/facerecognition/faceinfomationentry/',
        type:"post",
        headers:{
        Authorization:"Bearer "+ store.state.user.token
      },
      data:{
        Base64Data:capturedImage.value,
        name:names.value
      },
      success(resp){
        console.log(resp);
        stopCapture()
        router.push({name:'home'})
      },
      error(resp){
        console.log(resp);
      }

      })
  }


  function againCapture(){
    capturedImage.value=null;
    loadModels()
    initCamera()
  }
  
  // 生命周期钩子
  onMounted(async () => {
    await loadModels()
    await initCamera()
  })
  
  onBeforeUnmount(() => {
    stopCapture()
  })
  </script>
  
  <style scoped>
  .face-recognition {
    position: relative;
    margin: 20px;
  }
  
  .status {
    position: absolute;
    top: 10px;
    left: 10px;
    color: white;
    background: rgba(0,0,0,0.5);
    padding: 5px 10px;
  }
  
  img {
    display: block;
    margin-top: 20px;
    border: 2px solid #ccc;
  }
  </style>