import { createRouter, createWebHistory } from 'vue-router'
import defaultLayout from '@/layout/defaultLayout.vue'
import company from './company'
// import customer from './customer'
import contents from './contents'
import system from './system'
import loadingModule from '@/util/loadingModule'

const routes = [
  {
    path: '/',
    component: defaultLayout,
    children: [
      {
        path: '',
        component: () => import('@/views/main/index.vue')
      }
    ]
  },
  ...company,
  // ...customer,
  ...contents,
  ...system
]

routes.push({
  path: '/:pathMatch(.*)*',
  component: defaultLayout,
  children: [
    {
      path: '',
      component: () => import('@/views/404.vue')
    }
  ]
})

const router = createRouter({
  history: createWebHistory('/nts-adm/'),
  routes
})

router.beforeEach((to, from, next) => {
  loadingModule.hide()
  next()
})

router.afterEach((to, from) => {
  loadingModule.hide()
})
export default router
