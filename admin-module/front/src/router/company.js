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
        path: 'company-locations',
        name: '회사위치관리'
      },
      {
        path: 'teams',
        name: '팀관리'
      }
    ],
    meta: {
      menu: true
    }
  }
]

export default routes
