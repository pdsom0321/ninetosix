import defaultLayout from '@/layout/defaultLayout.vue'
const routes = [
  {
    path: '/company',
    name: '회사 및 사용자 관리',
    component: defaultLayout,
    children: [
      {
        path: '',
        name: '회사관리',
        component: () => import('@/views/company/index.vue')
      },
      {
        path: 'locations',
        name: '위치관리',
        component: () => import('@/views/company/locations.vue')
      }
    ],
    meta: {
      menu: true
    }
  }
]

export default routes
