import defaultLayout from '@/layout/defaultLayout.vue'

const routes = [
  {
    path: '/contents',
    name: '컨텐츠 관리',
    component: defaultLayout,
    children: [
      {
        path: 'notice',
        name: '공지사항',
        component: () => import('@/views/contents/notice.vue')
      }
    ],
    meta: {
      menu: true
    }
  }
]

export default routes
