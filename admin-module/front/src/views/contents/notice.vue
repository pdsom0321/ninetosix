<script setup>
import { reactive } from 'vue'
import lib from '@/util/apiUtil'
import popupModule from '@/util/popupModule'
import { useRoute } from 'vue-router'
import _ from 'lodash'

const boardList = reactive([])
const route = useRoute()

const getBoardList = () => {
  lib
    .api({
      url: `/board/${route.meta?.type}`,
      method: 'get'
    })
    .then((res) => {
      boardList.splice(0)
      _.merge(boardList, res)
    })
}

const openPopup = (id) => {
  popupModule
    .popup({
      component: import('@/views/contents/noticeDtl.vue'),
      data: {
        id: id,
        type: route.meta?.type
      }
    })
    .ok(() => {
      getBoardList()
    })
}

;(() => {
  getBoardList()
})()
</script>

<template>
  <div class="card h-100">
    <div class="card-header">
      <div class="input-group">
        <input type="text" class="form-control" />
        <button class="btn btn-outline-secondary">검색</button>
      </div>
    </div>
    <div class="card-body overflow-auto">
      <table class="table table-hover text-center align-middle">
        <colgroup>
          <col width="*" />
          <col width="40%" />
          <col width="*" />
          <col width="*" />
          <col width="*" />
          <col width="*" />
        </colgroup>
        <thead>
          <tr>
            <th>ID</th>
            <th class="text-start">제목</th>
            <th>게시시작일</th>
            <th>게시종료일</th>
            <th>노출여부</th>
            <th>삭제여부</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(item, index) in boardList"
            @click="openPopup(item.id)"
            style="cursor: pointer"
          >
            <td>{{ item.id }}</td>
            <td class="text-start">
              {{ item.title }}
            </td>
            <td>{{ item.startDate }}</td>
            <td>{{ item.endDate }}</td>
            <td>{{ item.useYn }}</td>
            <td>{{ item.deleteYn }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
