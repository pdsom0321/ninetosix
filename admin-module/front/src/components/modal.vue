<script setup>
import { reactive } from 'vue'
import _ from 'lodash'
import bodyComponenent from '@/views/company/locations.vue'

const state = reactive({})

const emit = defineEmits()

defineExpose({
  show() {
    // if (state.timer) {
    //   clearTimeout(state.timer)
    // }
    // state.show = true
    // requestAnimationFrame(() => {
    //   state.showing = true
    // })

    document.getElementById('modalObtn').click()
  },
  hide() {
    // if (state.timer) {
    //   clearTimeout(state.timer)
    // }
    // state.showing = false
    // state.timer = setTimeout(() => {
    //   state.show = false
    // }, 300)

    document.getElementById('modalCbtn').click()
  },
  modalOk() {
    return 'test'
  }
})
</script>

<template>
  <button
    id="modalObtn"
    type="button"
    class="btn btn-primary"
    data-bs-toggle="modal"
    data-bs-target="#staticBackdrop"
    style="display: none"
  ></button>
  <div
    class="modal fade"
    :class="{ show: state.show, showing: state.showing }"
    id="staticBackdrop"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
          <button
            id="modalCbtn"
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body p-0">
          <bodyComponenent :isModal="true" @modalOk="modalOk" />
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary text-center" @click="emit('modalok')">
            추가
          </button>
          <button type="button" class="btn btn-secondary text-center" data-bs-dismiss="modal">
            닫기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.spmodal {
  opacity: 0;
  transition: all 0.3s 0.1s;

  &.show {
    display: inline-block;
  }
  &.showing {
    opacity: 1;
  }
}

.modal-content {
  height: 100vh;
}
</style>
