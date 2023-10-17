<script setup>
import { ref, reactive, watchEffect } from 'vue'
import lib from '@/util/apiUtil'
import _ from 'lodash'
import popupModule from '@/util/popupModule'
import { useCompanyStore } from '@/stores/company'

const store = useCompanyStore()

const companies = reactive([])
const teams = reactive([])
const customers = reactive([])
const teamId = ref(null)

watchEffect(() => {
  if (store.compId) {
    teams.splice(0)
    lib
      .api({
        url: `/team/${store.compId}`,
        method: 'get'
      })
      .then((res) => {
        _.merge(teams, res)
      })
  }

  if (teamId.value) {
    customers.splice(0)
    lib
      .api({
        url: `/member/${teamId.value}`,
        method: 'get'
      })
      .then((res) => {
        _.merge(customers, res)
      })
  }
})

const openModal = (tid, mid) => {
  // dialogModule.show()
  popupModule
    .popup({
      component: import('@/views/company/customerDtl.vue'),
      data: {
        isModal: true,
        teamId: tid,
        memberId: mid
      }
    })
    .ok((data) => {
      console.log(data)
    })
}

;(() => {
  store.compId = null
  lib
    .api({
      url: '/company',
      method: 'get'
    })
    .then((res) => {
      _.merge(companies, res)
    })
})()
</script>

<template>
  <div class="row">
    <v-select
      class="my-1"
      style="z-index: 1040"
      :options="companies"
      :reduce="(x) => x.id"
      label="name"
      v-model="store.compId"
    ></v-select>
    <v-select
      class="my-1"
      style="z-index: 1030"
      :options="teams"
      :reduce="(x) => x.id"
      label="name"
      v-model="teamId"
    ></v-select>
    <div style="max-height: 700px" class="overflow-auto">
      <table class="table table-hover table-bordered h-75 text-center">
        <colgroup>
          <col width="*" />
          <col width="*" />
          <col width="*" />
          <col width="*" />
          <col width="5%" />
          <col width="5%" />
        </colgroup>
        <thead class="sticky-top">
          <tr>
            <th>ID</th>
            <th class="text-start">email</th>
            <th>이름</th>
            <th>소속</th>
            <th>use</th>
            <th>delete</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in customers">
            <td>{{ item.id }}</td>
            <td class="text-start">{{ item.email }}</td>
            <td>
              <button
                class="btn border-0 bg-transparent text-primary"
                @click="openModal(teamId, item.id)"
              >
                {{ item.name }}
              </button>
            </td>
            <td>{{ item.companyName }} [{{ item.teamName }}]</td>
            <!-- <td>{{ item.teamName }}</td> -->
            <td>{{ item.useYn }}</td>
            <td>{{ item.deleteYn }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
