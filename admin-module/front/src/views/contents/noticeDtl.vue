<script setup>
import { ref, reactive } from 'vue'
import lib from '@/util/apiUtil'
import _ from 'lodash'
import Editor from '@toast-ui/editor'
import '@toast-ui/editor/dist/toastui-editor.css'

const props = defineProps({
  data: {
    type: Object
  }
})

const emit = defineEmits()

const detail = reactive({})

const editor = ref()
const editorValid = ref()

const getBoardList = () => {
  lib
    .api({
      url: `/board/${props.data.type}/${props.data?.id}`,
      method: 'get'
    })
    .then((res) => {
      _.merge(detail, res)

      editorValid.value = new Editor({
        el: editor.value,
        height: '488px',
        //'wysiwyg', 'markdown' 택 1
        initialEditType: 'wysiwyg',
        initialValue: detail.content,
        hideModeSwitch: true
      })
    })
}

const saveContents = () => {
  detail.content = editorValid.value.getHTML()
  lib
    .api({
      url: `/board/${detail.id}`,
      method: 'put',
      data: detail
    })
    .then(() => {
      alert('저장되었습니다.')
      emit('ok')
    })
}

;(() => {
  getBoardList()
})()
</script>

<template>
  <div class="card">
    <div class="card-header">
      <div class="input-group">
        <span class="input-group-text">제목</span>
        <input type="text" class="form-control" v-model="detail.title" />
      </div>
    </div>
    <div class="card-body p-0">
      <div ref="editor" class="text-start"></div>
    </div>
    <div class="card-footer">
      <div class="btn-group">
        <button class="btn btn-success" @click="saveContents">저장</button>
        <button class="btn btn-secondary" @click="emit('close')">닫기</button>
      </div>
    </div>
  </div>
</template>
