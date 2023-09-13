<script setup>
import { onMounted, ref } from 'vue'
import Editor from '@toast-ui/editor'
import '@toast-ui/editor/dist/toastui-editor.css'

const props = defineProps({
  contents: {
    type: String,
    required: false,
    default: ''
  }
})

const emit = defineEmits(['update:contents'])
const editor = ref()
const editorValid = ref()
const testHtml = ref()

//마운트될때 Editor 생성
onMounted(() => {
  editorValid.value = new Editor({
    el: editor.value,
    height: '500px',
    //'wysiwyg', 'markdown' 택 1
    initialEditType: 'wysiwyg',
    initialValue: props.contents,
    hideModeSwitch: true,
    events: {
      change: () => emit('update:contents', editorValid.value.getHTML())
    }
  })
})

//작성한 내용 불러와서 html 적용
// const testValid = (e) => {
//   testHtml.value = editorValid.value.getHTML()
// }
</script>

<template>
  <!-- // ref사용하여 에디터 value 적용 -->
  <div ref="editor"></div>
  <!-- //작성한 내용 불러와서 html 적용 -->
  <!-- <button @click="testValid">testValid</button> -->
  <!-- {{ editor }} -->
  <!-- <div v-html="testHtml"></div> -->
</template>
