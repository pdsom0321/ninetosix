<script setup>
import { ref, reactive } from 'vue'
const mode = ref(false)
const menus = reactive([
  {
    name: 'Dashboard',
    expand: false
  }
])
</script>

<template>
  <RouterView class="h-100" />
</template>
