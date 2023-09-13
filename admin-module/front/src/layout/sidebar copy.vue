<script setup>
import router from '@/router'

const routes = router.options.routes.filter((x) => {
  return x.meta?.menu
})
</script>

<template>
  <ul>
    <li v-for="item1 in routes">
      {{ item1.name }}
      <ul v-if="item1.children">
        <li v-for="item2 in item1.children">
          <router-link :to="item1.path + '/' + item2.path">{{ item2.name }}</router-link>
        </li>
      </ul>
    </li>
  </ul>
</template>
