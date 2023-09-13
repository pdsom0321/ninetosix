import { createRouter, createWebHistory } from 'vue-router'
import defaultLayout from '@/layout/defaultLayout.vue'
import company from './company'
// import customer from './customer'
import contents from './contents'
import system from './system'

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
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  console.log('[to] : ' + to, '[from] : ' + from)
  next()
})

export default router
