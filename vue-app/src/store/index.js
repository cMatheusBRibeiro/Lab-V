import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    usuario: { }
  },
  mutations: {
    setToken: (state, token) => state.token = token,
    setUsuario: (state, usuario) => state.usuario = usuario
  },
  actions: {
    setDadosLogin: (context, dados) => {
      context.commit('setToken', dados.token)
      context.commit('setUsuario', dados.usuario)
    }
  },
  modules: {

  },
  getters: {
    doneToken: state => {
      return state.token
    },
    doneUsuario: state => {
      return state.usuario
    }
  }
})
