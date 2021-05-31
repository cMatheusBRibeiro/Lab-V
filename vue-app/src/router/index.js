import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login')
  },
  {
    path: '/novo-usuario',
    name: 'Novo Usuario',
    component: () => import('../views/NovoUsuario')
  },
  {
    path: '/inicio',
    name: 'Inicio',
    component: () => import('../views/Inicio')
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})

export default router
