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
  {{ memberInfo }}
</template>
