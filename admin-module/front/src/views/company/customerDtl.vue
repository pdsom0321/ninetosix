<script setup>
import { reactive } from 'vue'

import lib from '@/util/apiUtil'
import _ from 'lodash'

const props = defineProps({
  data: {
    type: String
  }
})

const memberInfo = reactive({})

;(() => {
  lib
    .api({
      url: `/member/${props.data.teamId}/${props.data.memberId}`,
      method: 'get'
    })
    .then((res) => {
      console.log(res)
      _.merge(memberInfo, res)
    })
})()
</script>

<template>
  <div class="p-3">
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">ID</span>
      <input type="text" class="form-control" v-model="memberInfo.id" readonly />
    </div>
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">email</span>
      <input type="text" class="form-control" v-model="memberInfo.email" readonly />
    </div>
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">이름</span>
      <input type="text" class="form-control" v-model="memberInfo.name" readonly />
    </div>
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">회사</span>
      <input type="datetime" class="form-control" v-model="memberInfo.companyName" readonly />
    </div>
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">팀</span>
      <input type="datetime" class="form-control" v-model="memberInfo.teamName" readonly />
    </div>
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">연락처</span>
      <input type="text" class="form-control" v-model="memberInfo.contact" readonly />
    </div>
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">Use YN</span>
      <input type="text" class="form-control" v-model="memberInfo.useYn" readonly />
    </div>
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">Delete YN</span>
      <input type="text" class="form-control" v-model="memberInfo.deleteYn" readonly />
    </div>
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">Push 동의여부</span>
      <input type="text" class="form-control" v-model="memberInfo.pushAgreeYn" readonly />
    </div>
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">등록일</span>
      <input type="datetime" class="form-control" v-model="memberInfo.insertDate" readonly />
    </div>
    <div class="input-group input-group-sm mb-1">
      <span class="input-group-text w-25 fw-bold">수정일</span>
      <input type="datetime" class="form-control" v-model="memberInfo.updateDate" readonly />
    </div>
  </div>
</template>
