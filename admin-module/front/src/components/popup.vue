<script setup>
import { ref, reactive, defineAsyncComponent, onMounted } from 'vue'
import _ from 'lodash'

const props = defineProps({
  settings: {
    type: Object,
    default: () => ({})
  }
})

const emit = defineEmits()

const state = reactive({
  show: false,
  showing: false
})

onMounted(() => {
  state.show = true
  requestAnimationFrame(() => {
    state.showing = true
  })
})

const defineComponent = defineAsyncComponent({
  loader: () => props.settings.component
})

// const state = reactive({
//   popupOptions: {}
// })

const field = reactive({
  callbackData: {}
})

// state.settings = computed(() => {
//   return _.assign(reactive({}), props.settings)
// })

const popupOk = (data) => {
  if (props.settings._ok) {
    props.settings._ok(data)
  }
  close()
}

const popupClose = (data) => {
  if (props.settings._close) {
    props.settings._close(data)
  }
  close()
}

const close = () => {
  state.showing = false
  setTimeout(() => {
    state.show = false
    props.settings.$unmount()
  }, 300)
}
</script>

<template>
  <!-- 
    * 기능추가
        옵션에 width, height
        커스텀 이벤트 바인딩
  -->
  <div class="popup-container text-center" :class="{ show: state.show, showing: state.showing }">
    <div class="card popup-component border-0 h-75">
      <div class="card-header text-end p-0 bg-secondary border-0">
        <button
          class="px-3 py-2 btn btn-sm btn-outline-secondary text-light close-btn fw-bold"
          @click="popupClose"
        >
          <!-- <img src="@/assets/image/icon/close-button.png" /> -->
          X
        </button>
      </div>
      <div class="card-body p-0 overflow-auto">
        <component
          v-if="defineComponent"
          :is="defineComponent"
          :data="props.settings.data"
          @ok="popupOk"
          @close="popupClose"
        />
      </div>
    </div>
  </div>
</template>

<style>
.popup-container {
  position: fixed;
  /* display: ; */
  background: rgba(0, 0, 0, 0.4);
  z-index: 9999;
  top: 0px;
  left: 0px;
  width: 100vw;
  height: 100vh;
  opacity: 0;
  transition: all 0.3s 0.1s;

  &.show {
    display: inline-block;
  }
  &.showing {
    opacity: 1;
  }
}

.popup-component {
  position: absolute;
  transform: translate(-50%, -50%);
  top: 50%;
  left: 50%;
  width: auto;
  min-width: 700px;
  transition: all 0.1s 0.1s;
}
</style>
