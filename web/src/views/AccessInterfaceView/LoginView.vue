<template>
    <BaseCard>

        <div class="row justify-content-md-center" >
            <div class="col-4"></div>
                <div class="col-4"> 
                <div class="card text-center">
                    <div class="card-header">
                        <span style="font-weight: bold; font-style: italic;">人脸识别考勤系统</span>
                    </div>
                    <div class="card-body">
                        <form @submit.prevent="">
                            <div class="mb-3">
                                <input type="text"  class="form-control"  placeholder="用户账号" id="username" aria-describedby="emailHelp">
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" placeholder="用户密码" id="password">
                            </div>

                          
                        </form>
                    </div>
                    <div class="card-footer"> 
                        <button type="button" class="btn btn-outline-primary ms-2">考勤登录</button>
                        <button type="button" class="btn btn-outline-secondary ms-2">打卡登录</button>
                    </div>
            </div>

            </div>
            <div class="col-4"></div>
           

        </div>
    
    </BaseCard>
</template>


<script>

import BaseCard from '@/components/BaseCard'
export default{
    components:{
        BaseCard
    }
}
</script>

<style scoped>
</style>