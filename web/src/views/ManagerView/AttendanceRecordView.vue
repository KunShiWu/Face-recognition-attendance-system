<template>
    <BaseCard>
      <!-- 返回图标 -->
      <div class="return-icon" @click="goBack" title="返回">
        <i class="bi bi-arrow-left-circle-fill"></i>
      </div>
  
      <!-- 表格 -->
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Last</th>
            <th scope="col">Handle</th>
          </tr>
        </thead>
        <tbody>

          <tr  v-for="(person, index) in paginatedPeople" :key="index" >
            <th scope="row">{{ index+1}}</th>
            <td>{{ person}}</td>
            <td>Otto</td>
            <td>@mdo</td>
          </tr>   
        </tbody>
      </table>


  <nav aria-label="Page navigation example"   style="float: right;">
        <ul class="pagination">
          <li class="page-item" :class="{'disabled': currentPage === 1}">
            <a class="page-link" href="#" @click.prevent="changePage(currentPage - 1)" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
  
          <!-- 页码按钮 -->
          <li class="page-item" :class="{'active': currentPage === page}" v-for="page in totalPages" :key="page">
            <a class="page-link" href="#" @click.prevent="changePage(page)">
              {{ page }}
            </a>
          </li>
  
          <li class="page-item" :class="{'disabled': currentPage === totalPages}">
            <a class="page-link" href="#" @click.prevent="changePage(currentPage + 1)" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </BaseCard>
 
  </template>
  
  <script>
import { useRouter } from 'vue-router'
import BaseCard from '@/components/BaseCard.vue'
import { useStore } from 'vuex';
import { ref,computed  } from 'vue';


  export default {
    components: {
      BaseCard
    },

    setup() {
      const router = useRouter()
    
      const store=useStore();
      let missions=store.state.mission.missions;
      let missiondataid=store.state.mission.missiondataid;
      let mission=ref();

        //   // 当前页码
        // let   currentPage= 1;
        // // 每页显示的数量
        // let perPage=2;



      for(let obj of missions){
        if(obj.id===missiondataid){
          mission.value=obj;
          break;
        }
      }
      console.log(mission.value);
      const goBack = () => {
        router.go(-1) // 可根据需求更改跳转逻辑
      }

      // 使用 ref 来声明响应式数据
    let people = mission.value.nameList.split("#");
    let currentPage = ref(1);
    let perPage = ref(15);

    // 计算总页数
    const totalPages = computed(() => Math.ceil(people.length / perPage.value));

    // 计算当前页的数据
    const paginatedPeople = computed(() => {
      const start = (currentPage.value - 1) * perPage.value;
      const end = start + perPage.value;
      return people.slice(start, end);
    });

    // 改变页码
    const changePage = (page) => {
      if (page < 1 || page > totalPages.value) return;
      currentPage.value = page;
    };

      return {
        goBack,
        mission,
        people,
        currentPage,
        perPage,
        totalPages,
        paginatedPeople,
        changePage,
      }
    },
  }
  </script>
  
  <style scoped>
  .return-icon {
    position: absolute;
    top: 10px;
    right: 10px;
    cursor: pointer;
    font-size: 24px;
    color: #007BFF;
    transition: transform 0.2s ease, color 0.2s ease;
  }
  
  .return-icon:hover {
    transform: scale(1.1);
    color: #0056b3;
  }

 
  </style>
  