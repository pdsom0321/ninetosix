<script setup>
import { ref } from 'vue'
import editor from '@/components/editor.vue'

const contents = ref(
  '<p><strong>안녕하세요. 0918 관리자 입니다.</strong></p><p>0918은 현재 베타 버전으로 서비스 중에 있으며 다음과 같은 서비스를 개발하고 있습니다.</p><ul><li><p>출퇴근 등록</p></li><li><p>출퇴근 기록</p></li><li><p>휴가 등록</p></li><li><p>내 정보</p></li></ul><p>추가 기능은 개발을 마치고 서비스 적용 시 즉시 공지하여 드리겠습니다.</p><p>감사합니다.</p>'
)

const saveContents = (val) => {
  contents.value = val
}
</script>

<template>
  <editor :contents="contents" @update:contents="saveContents" />
</template>
