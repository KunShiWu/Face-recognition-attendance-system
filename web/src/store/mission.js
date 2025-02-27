
export default ({
  state: {
    missions:[],
    missiondataid:null
  },
  getters: {
  },
  mutations: {
    updateMissions(state,missioins){
      state.missions=missioins;
  },
  updateMissionDataId(state,missionId){
      state.missiondataid=missionId;
  }
  },
  actions: {
  },
  modules: {
  
}
})
