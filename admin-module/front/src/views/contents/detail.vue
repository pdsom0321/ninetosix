<script setup>
import { ref, reactive, onMounted } from 'vue'
import lib from '@/util/apiUtil'
import _ from 'lodash'
import Editor from '@toast-ui/editor'
import '@toast-ui/editor/dist/toastui-editor.css'

import moment from 'moment'

const props = defineProps({
  data: {
    type: Object,
    default: () => ({})
  }
})
const emit = defineEmits()

const detail = reactive({
  title: '',
  startDate: '',
  endDate: ''
})

const editor = ref()
const editorValid = ref()

onMounted(async () => {
  if (props.data.id) {
    await getBoardList()
  } else {
    _.merge(detail, {
      title: '',
      startDate: moment().format('YYYY-MM-DD'),
      endDate: moment().add(1, 'W').format('YYYY-MM-DD'),
      useYn: 'Y',
      deleteYn: 'N'
    })
  }

  editorValid.value = new Editor({
    el: editor.value,
    height: '472px',
    //'wysiwyg', 'markdown' 택 1
    initialEditType: 'wysiwyg',
    initialValue: detail.content,
    hideModeSwitch: true
  })
})

const getBoardList = async () => {
  await lib
    .api({
      url: `/board/${props.data.type}/${props.data?.id}`,
      method: 'get'
    })
    .then((res) => {
      _.merge(detail, res)
      // editor.value.current.getInstance().setHtml(res.content)
    })
}

const saveContents = () => {
  detail.content = editorValid.value.getHTML()
  lib
    .api({
      url: props.data.id ? `/board/${detail.id}` : `/board/${props.data.type}`,
      method: props.data.id ? 'put' : 'post',
      data: detail
    })
    .then(() => {
      alert('저장되었습니다.')
      emit('ok')
    })
}

const delContents = () => {
  if (confirm('정말 삭제하시겠습니까?')) {
    lib
      .api({
        url: `/board/${detail.id}`,
        method: 'delete'
      })
      .then(() => {
        alert('삭제되었습니다.')
        emit('ok')
      })
  }
}

// ;(() => {
//   if (props.data.id) getBoardList()
// })()
</script>

<template>
  <div class="card">
    <div class="card-header border-0">
      <div class="row m-0">
        <div class="col-12 px-1">
          <div class="input-group input-group-sm mb-2">
            <span class="input-group-text">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</span>
            <input type="text" class="form-control" v-model="detail.title" />
          </div>
        </div>
        <div class="col-8 px-1">
          <div class="input-group input-group-sm">
            <span class="input-group-text">시 작 일</span>
            <input type="date" class="form-control" v-model="detail.startDate" />
          </div>
        </div>
        <div class="col px-1">
          <div class="input-group input-group-sm">
            <span class="input-group-text">게시여부</span>
            <select class="form-select text-center" v-model="detail.useYn">
              <option value="Y">Y</option>
              <option value="N">N</option>
            </select>
          </div>
        </div>
        <div class="col-8 px-1">
          <div class="input-group input-group-sm">
            <span class="input-group-text">종 료 일</span>
            <input type="date" class="form-control" v-model="detail.endDate" />
          </div>
        </div>
        <div class="col px-1">
          <div class="input-group input-group-sm">
            <span class="input-group-text">삭제여부</span>
            <select class="form-select text-center" v-model="detail.deleteYn">
              <option value="Y">Y</option>
              <option value="N">N</option>
            </select>
          </div>
        </div>
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
      <button
        v-if="props.data.id"
        class="btn btn-outline-danger float-end"
        @click="delContents(id)"
      >
        삭제
      </button>
    </div>
  </div>
</template>
