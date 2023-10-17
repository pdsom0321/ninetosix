<script setup>
import { ref, reactive, onMounted, computed, watch } from 'vue'
import lib from '@/util/apiUtil'
import popupModule from '@/util/popupModule'
import { useRoute } from 'vue-router'
import _ from 'lodash'

const page = ref(0)
const totalPage = ref(0)
const keyword = ref('')
const boardList = reactive([])
const route = useRoute()

const getBoardList = () => {
  boardList.splice(0)
  lib
    .api({
      url: `/board/${route.meta?.type}?keyword=${keyword.value}&page=${page.value}&size=10`,
      method: 'get'
    })
    .then((res) => {
      totalPage.value = res.totalPage
      _.merge(boardList, res.list)
    })
}

watch(page, (newValue, oldValue) => {
  getBoardList()
})

const openPopup = (id) => {
  let data = {
    type: route.meta?.type
  }

  if (id) data.id = id

  popupModule
    .popup({
      component: import('@/views/contents/detail.vue'),
      data: data
    })
    .ok(() => {
      getBoardList()
    })
}

onMounted(() => {
  getBoardList()
})

const pages = computed(() => {
  let pageArray = []
  let startPage = Math.floor(page.value / 10) * 10 + 1
  let endPage = Math.min(totalPage.value, startPage + 9)
  for (var i = startPage; i <= endPage; i++) {
    pageArray.push(i)
  }

  return pageArray
})
</script>

<template>
  <div class="card h-100">
    <div class="card-header">
      <div class="input-group">
        <input type="text" class="form-control" v-model="keyword" />
        <button class="btn btn-outline-secondary" @click="getBoardList">검색</button>
      </div>
    </div>
    <div class="card-body h-100 overflow-auto p-0">
      <table class="table table-hover text-center align-middle">
        <colgroup>
          <col width="*" />
          <col width="40%" />
          <col width="*" />
          <col width="*" />
          <col width="5%" />
          <col width="5%" />
        </colgroup>
        <thead>
          <tr>
            <td colspan="100">
              <button class="btn btn-sm btn-primary float-end" @click="openPopup(null)">
                신규
              </button>
            </td>
          </tr>
        </thead>
        <thead>
          <tr>
            <th>ID</th>
            <th class="text-start">Title</th>
            <th>Start</th>
            <th>End</th>
            <th>Use</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(item, index) in boardList"
            @click="openPopup(item.id)"
            style="cursor: pointer; height: 60px"
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
    <div class="card-footer">
      <ul class="pagination justify-content-center my-2">
        <li class="page-item" :class="pages[0] === 1 ? 'disabled' : ''">
          <a class="page-link" href="#" @click="page = Math.floor((page - 10) / 10) * 10">이전</a>
        </li>
        <li class="page-item" :class="page == item - 1 ? 'active' : ''" v-for="item in pages">
          <a class="page-link" href="#" @click="page = item - 1">{{ item }}</a>
        </li>
        <li class="page-item" :class="pages[pages.length - 1] == totalPage ? 'disabled' : ''">
          <a class="page-link" href="#" @click="page = Math.floor((page + 10) / 10) * 10">다음</a>
        </li>
      </ul>
    </div>
  </div>
</template>
