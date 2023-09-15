<script setup>
import { reactive } from 'vue'
import { useCompanyStore } from '@/stores/company'
import lib from '@/util/apiUtil'
import _ from 'lodash'

const store = useCompanyStore()

const companyies = reactive([
  // {
  //   id: 1,
  //   name: 'GS ITM'
  // },
  // {
  //   id: 2,
  //   name: 'GS 칼텍스'
  // }
])

const getCompanies = () => {
  companyies.splice(0)

  lib
    .api({
      url: '/company',
      method: 'get'
    })
    .then((res) => {
      _.merge(companyies, res)
    })
}

const addList = reactive([])

const addComp = (idx) => {
  //샘플, 실제로는 서버에 저장 후 다시 get api 호출해서 데이터 바인딩
  companyies.push(addList[idx])
  addList.splice(idx, 1)
}

const saveComp = (idx) => {
  lib
    .api({
      url: `/company/${companyies[idx].id}`,
      method: 'put',
      data: {
        name: companyies[idx].name
      }
    })
    .then(() => {
      getCompanies()
    })
}

const editComp = (idx) => {
  companyies[idx].editMode = true
}

const delComp = (idx) => {
  if (confirm('정말 삭제하시겠습니까?')) {
    lib
      .api({
        url: `/company/${companyies[idx].id}`,
        method: 'delete'
      })
      .then(() => {
        getCompanies()
      })
  }
}

const companyAPI = ({ id, method }, callback) => {
  lib
    .api({
      url: `/company/${id}`,
      method: method
    })
    .then((res) => {
      // 콜백
      callback(res)
      getCompanies()
    })
}

;(() => {
  getCompanies()
})()
</script>

<template>
  <table class="table table-bordered table-hover text-center align-middle">
    <colgroup>
      <col />
      <col width="90%" />
      <!-- <col /> -->
    </colgroup>
    <thead>
      <tr class="border-0">
        <th class="text-end border-0" colspan="100">
          <button class="btn btn-sm btn-outline-secondary" @click="addList.push({})">추가</button>
        </th>
      </tr>
      <tr>
        <th>ID</th>
        <th>회사명</th>
        <!-- <th></th> -->
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="(company, index) in companyies"
        :class="store.compId == company.id ? 'bg-primary-subtle' : ''"
        @click="store.compId = company.id"
      >
        <td class="bg-transparent">{{ company.id }}</td>
        <td class="bg-transparent">
          <div class="input-group">
            <input
              type="text"
              :role="!company.editMode ? 'button' : ''"
              class="form-control cursor-pointer"
              :class="company.editMode ? '' : 'bg-transparent border-0'"
              v-model="company.name"
              :readonly="!company.editMode"
            />
            <button
              v-if="!company.editMode"
              class="btn btn-sm btn-outline-secondary rounded-0"
              @click="editComp(index)"
            >
              수정
            </button>
            <button
              v-if="!company.editMode"
              class="btn btn-sm btn-outline-danger rounded-0"
              @click="delComp(index)"
            >
              삭제
            </button>
            <button v-else class="btn btn-sm btn-outline-success" @click="saveComp(index)">
              저장
            </button>
          </div>
        </td>
      </tr>
      <tr v-for="(item, index) in addList">
        <td class="opacity-75">auto</td>
        <td>
          <div class="input-group">
            <input type="text" class="form-control" v-model="item.name" />
            <button class="btn btn-sm btn-outline-success rounded-0" @click="addComp(index)">
              저장
            </button>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
</template>
