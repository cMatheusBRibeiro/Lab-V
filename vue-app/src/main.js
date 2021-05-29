// Presets
import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'

// Bootstrap
import './plugins/bootstrap-vue'

// App
import App from './App.vue'

// Router
import router from './router'

// Store
import store from './store'

// Sweet Alert 2
import VueSweetalert2 from 'vue-sweetalert2'
import 'sweetalert2/dist/sweetalert2.min.css'

Vue.use(VueSweetalert2)

// Axios
import axios from 'axios'

axios.interceptors.request.use(config => {
  config.baseURL = 'https://8080-gold-caterpillar-3wc8snsl.ws-us08.gitpod.io/blog/'
  return config
})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
