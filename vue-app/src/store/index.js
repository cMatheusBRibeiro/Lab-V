import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: null,
    usuario: null
  },
  mutations: {
    setToken: (state, token) => state.token = token,
    setUsuario: (state, usuario) => state.usuario = usuario,
    removerToken: (state) => state.token = null,
    removerUsuario: (state) => state.usuario = null
  },
  actions: {
    setDadosLogin: (context, dados) => {
      context.commit('setToken', dados.token)
      context.commit('setUsuario', dados.usuario)
    },
    removerDadosLogin: (context) => {
      context.commit('removerToken')
      context.commit('removerUsuario')
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
