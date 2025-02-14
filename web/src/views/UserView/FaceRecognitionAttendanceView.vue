<template>
    <div>
      <h1>人脸识别录入</h1>
      <video id="videoElement" width="640" height="480" autoplay muted></video>
      <canvas id="overlay" width="640" height="480"></canvas>
      <button @click="startRecognition">开始人脸录入</button>
    </div>
  </template>
  
  <script>
  import * as faceapi from 'face-api.js';
  
  export default {
    name: 'FaceRecognition',
    mounted() {
      this.loadModels(); // 加载模型
    },
    methods: {
      // 加载 face-api.js 模型
      async loadModels() {
        const MODEL_URL = '../models/'; // 模型文件路径，确保你已下载并放置在项目中
  
        // 加载 face-api.js 模型
        await Promise.all([
          faceapi.nets.ssdMobilenetv1.loadFromUri(MODEL_URL),
          faceapi.nets.faceLandmark68Net.loadFromUri(MODEL_URL),
          faceapi.nets.faceRecognitionNet.loadFromUri(MODEL_URL)
        ]);
        console.log('模型加载完成');
      },
  
      // 开始获取视频流并进行人脸识别
      async startRecognition() {
        try {
          const stream = await navigator.mediaDevices.getUserMedia({
            video: true
          });
  
          const video = document.getElementById('videoElement');
          video.srcObject = stream;
  
          // 在视频播放时进行人脸识别
          video.onplaying = () => {
            this.detectFaces(video);
          };
        } catch (err) {
          console.error('摄像头访问失败：', err);
        }
      },
  
      // 检测视频中的人脸
      async detectFaces(video) {
        const canvas = document.getElementById('overlay');
        faceapi.matchDimensions(canvas, video);
  
        setInterval(async () => {
          // 检测视频中的所有人脸
          const detections = await faceapi.detectAllFaces(video)
            .withFaceLandmarks()
            .withFaceDescriptors();
  
          // 清除之前的绘制
          canvas?.getContext('2d')?.clearRect(0, 0, canvas.width, canvas.height);
  
          // 如果检测到人脸，绘制识别框
          if (detections.length > 0) {
            faceapi.draw.drawDetections(canvas, detections);
            faceapi.draw.drawFaceLandmarks(canvas, detections);
  
            // 获取人脸特征信息
            const faceDescriptor = detections[0].descriptor;
            console.log('人脸特征:', faceDescriptor);
  
            // 可以在这里将 faceDescriptor 上传到后端进行存储
          }
        }, 100);
      }
    }
  };
  </script>
  
  <style scoped>
  #videoElement {
    border: 2px solid #000;
    margin-bottom: 20px;
  }
  
  #overlay {
    position: absolute;
    top: 0;
    left: 0;
    z-index: 1;
  }
  </style>
  