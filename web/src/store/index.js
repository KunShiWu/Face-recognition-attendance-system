import { createStore } from 'vuex'
import ModuleUser from '@/store/user'
import ModuleMission from '@/store/mission'
export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user:ModuleUser,
    mission:ModuleMission
  }
})
