import defaultLayout from '@/layout/defaultLayout.vue'
const routes = [
  {
    path: '/customer',
    name: '고객관리',
    component: defaultLayout,
    children: [
      {
        path: 'test1',
        name: 'test1',
        component: () => import('@/views/customer/index.vue')
      },
      {
        path: 'test2',
        name: 'test2'
      }
    ],
    meta: {
      menu: true
    }
  }
]

export default routes
