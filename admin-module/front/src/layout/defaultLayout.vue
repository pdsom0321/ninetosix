<script setup>
import sidebar from './sidebar.vue'
import { ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const mode = ref(false)
</script>

<template>
  <div class="body-layout h-100" :class="mode ? 'body-pd' : ''">
    <div class="l-navbar" :class="mode ? 'expander' : ''" id="navbar">
      <sidebar @toggle="mode = !mode" />
    </div>

    <div class="card h-100 rounded-0 border-0">
      <div v-if="route.name" class="card-header py-3">
        <span>{{ route.matched[0].name }} &gt; </span>
        <span class="fw-bold">{{ route.name }}</span>
      </div>
      <div class="card-body h-100 container">
        <router-view :key="$route.fullPath" />
      </div>
    </div>
  </div>
</template>
