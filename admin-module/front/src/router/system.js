import defaultLayout from '@/layout/defaultLayout.vue'

const routes = [
  {
    path: '/system',
    name: '시스템 관리',
    component: defaultLayout,
    children: [
      {
        path: 'test1',
        name: '시스템',
        component: () => import('@/views/system/index.vue')
      }
    ],
    meta: {
      menu: true
    }
  }
]

export default routes
