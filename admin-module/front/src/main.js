// import './assets/main.css'
import '@/assets/css/styles.css'
// import '@/assets/js/common.js'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import 'vue-select/dist/vue-select.css'
import vSelect from 'vue-select'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.component('v-select', vSelect)

app.mount('#app')
