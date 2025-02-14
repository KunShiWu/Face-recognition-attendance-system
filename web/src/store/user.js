
import $ from "jquery"
export default ({
  state: {
    id:"",
    username:"",
    photo:"",
    identity:"",
    token:"",
    is_login:false  
  },
  getters: {
  },
  mutations: {
    updateUser(state,user){
        state.id=user.id;
        state.username=user.username;
        state.photo=user.photo;
        state.identity=user.identity;
        state.is_login=user.is_login;
    },
    updateToken(state,token){
        state.token=token;
    },
    logout(state){
        state.id='',
        state.username='',
        state.photo='',
        state.identity='',
        state.token='',
        state.is_login=false
    }
  },
  actions: {
    login(context,data)
        {
            $.ajax({
                url:"http://127.0.0.1:3000/user/account/token/",
                type:"post",
                data:{
                  username:data.username,
                  password:data.password
                },
                success(resp){
                    if(resp.error_message==="success")
                  {
                    localStorage.setItem("jwt_token",resp.token)//存在浏览器里面避免刷新的时候token被清除
                    context.commit("updateToken",resp.token)//要在actions调用mutations里面要用commit
                    data.success();
                  }
                    else data.error(resp)
                },
                error(resp){
                  data.error(resp);
                }
              });
        },
        getinfo(context,data){
            $.ajax({
            url:"http://127.0.0.1:3000/user/account/info/",
            type:"get",
            headers:{
                Authorization:"Bearer "+context.state.token,
            },
            success(resp){
                if(resp.error_message==="success")
                {
      
                    context.commit("updateUser",{
                        ...resp,//等于后面注释的三行
                        // id:resp.id,
                        // username:resp.username,
                        // photo:resp.photo,
                        is_login:true,
                    });
                    data.success(resp);
                } else data.error();
               
            },
            error(){
                data.error();
            }
            })

    },

    logout(context)
    {
        context.commit("logout");
        localStorage.removeItem("jwt_token")
    }
  },
  modules: {
  }
})
