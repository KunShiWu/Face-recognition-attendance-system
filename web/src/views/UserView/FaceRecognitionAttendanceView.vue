<template>
  <BaseCard>人脸识别</BaseCard>
</template>


<script>

import BaseCard from '@/components/BaseCard'
export default{
  components:{
      BaseCard
  },
      setup(){
          function getLocation() {
          if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
              position => {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;
                // 调用后端接口，将经纬度发送给后端
            
                console.log(longitude);
                console.log(latitude);

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

        getLocation();
    }
}

</script>

<style scoped>
</style>