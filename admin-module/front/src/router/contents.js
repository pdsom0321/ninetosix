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
        component: () => import('@/views/contents/index.vue'),
        meta: {
          type: 'NOTICE'
        }
      },
      {
        path: 'privacy',
        name: '개인정보처리방침',
        component: () => import('@/views/contents/index.vue'),
        meta: {
          type: 'PRIVACY'
        }
      },
      {
        path: 'terms',
        name: '이용약관',
        component: () => import('@/views/contents/index.vue'),
        meta: {
          type: 'TERMS'
        }
      }
    ],
    meta: {
      menu: true
    }
  }
]

export default routes
