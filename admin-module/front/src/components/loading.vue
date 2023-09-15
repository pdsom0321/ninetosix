<script setup>
import { ref, reactive, onMounted } from 'vue'
import Lottie from 'lottie-web'
import animationData from '@/assets/image/loading_white.json'

const state = reactive({})
const lottieContainer = ref(null)
const anim = ref(null)

onMounted(() => {
  anim.value = Lottie.loadAnimation({
    container: lottieContainer.value,
    renderer: 'svg',
    loop: true,
    autoplay: true,
    animationData: animationData
  })
})

defineExpose({
  show() {
    if (state.timer) {
      clearTimeout(state.timer)
    }
    state.show = true
    requestAnimationFrame(() => {
      state.showing = true
    })
  },
  hide() {
    if (state.timer) {
      clearTimeout(state.timer)
    }
    state.showing = false
    state.timer = setTimeout(() => {
      state.show = false
    }, 300)
  }
})
</script>

<template>
  <div
    ref="lottieContainer"
    class="loading"
    :class="{ show: state.show, showing: state.showing }"
  ></div>
</template>

<style>
.loading {
  z-index: 20000;
  background: transparent;
  display: none;
  position: fixed;
  width: 100vw;
  height: 100vh;
  top: 0;
  /* left: calc(50% - 50vh); */
  opacity: 0;
  transition: all 0.3s 0.1s;

  &.show {
    display: inline-block;
  }
  &.showing {
    opacity: 1;
  }
}
</style>
