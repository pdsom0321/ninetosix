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
        component: () => import('@/views/contents/notice.vue'),
        meta: {
          type: '01'
        }
      },
      {
        path: 'privacy',
        name: '개인정보처리방침',
        // component: () => import('@/views/contents/notice.vue'),
        meta: {
          type: '02'
        }
      },
      {
        path: 'terms',
        name: '이용약관',
        // component: () => import('@/views/contents/notice.vue'),
        meta: {
          type: '03'
        }
      }
    ],
    meta: {
      menu: true
    }
  }
]

export default routes
