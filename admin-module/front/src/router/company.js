import defaultLayout from '@/layout/defaultLayout.vue'
const routes = [
  {
    path: '/company',
    name: '회사 및 사용자 관리',
    component: defaultLayout,
    children: [
      {
        path: '',
        name: '회사 관리',
        component: () => import('@/views/company/index.vue')
      },
      {
        path: 'locations',
        name: '위치 관리',
        component: () => import('@/views/company/locations.vue')
      },
      {
        path: 'customer',
        name: '사용자 관리',
        component: () => import('@/views/company/customer.vue')
      }
    ],
    meta: {
      menu: true
    }
  }
]

export default routes
