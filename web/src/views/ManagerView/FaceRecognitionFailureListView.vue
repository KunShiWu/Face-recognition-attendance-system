<template>
    <div>
      <!-- 人员列表 -->
      <div v-for="(person, index) in paginatedPeople" :key="index">
        <div>{{ person.name }}</div>
      </div>
  
      <!-- 分页导航 -->
      <nav aria-label="Page navigation example">
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
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        // 所有人员数据
        people: [
          { name: '张三' },
          { name: '李四' },
          { name: '王五' },
          { name: '赵六' },
          // 其他人员...
        ],
        // 当前页码
        currentPage: 1,
        // 每页显示的数量
        perPage: 2,
      };
    },
    computed: {
      // 计算总页数
      totalPages() {
        return Math.ceil(this.people.length / this.perPage);
      },
      // 计算当前页的数据
      paginatedPeople() {
        const start = (this.currentPage - 1) * this.perPage;
        const end = start + this.perPage;
        return this.people.slice(start, end);
      }
    },
    methods: {
      // 改变页码
      changePage(page) {
        if (page < 1 || page > this.totalPages) return;
        this.currentPage = page;
      }
    }
  };
  </script>
  
  <style scoped>
  .pagination {
    display: flex;
    justify-content: center;
  }
  </style>
  