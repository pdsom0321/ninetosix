<script setup>
import { reactive } from 'vue'
import router from '@/router'
// import _ from 'lodash'

let routes = reactive(
  router.options.routes
    .filter((item) => {
      return item.meta?.menu
    })
    .map((item) => {
      const newObj = { ...item }
      newObj.toggle = false
      return newObj
    })
)

const emit = defineEmits()
const toggleSidebar = () => {
  routes.forEach((item) => {
    item.toggle = false
  })
  emit('toggle')
}
</script>

<template>
  <nav class="sidebar">
    <div>
      <div class="nav__brand">
        <ion-icon
          name="menu-outline"
          class="nav__toggle"
          id="nav-toggle"
          @click="toggleSidebar"
        ></ion-icon>
        <router-link to="/" class="nav__logo">NineToSix Admin</router-link>
      </div>
      <div class="nav__list">
        <div v-for="route in routes" :to="route.path" class="nav__link collapse__">
          <router-link :to="route.path + '/' + route.children[0].path" title="test"
            ><ion-icon name="folder-outline" class="nav__icon"></ion-icon
          ></router-link>
          <span class="nav_name" @click="route.toggle = !route.toggle">{{ route.name }}</span>

          <ion-icon
            name="chevron-down-outline"
            class="collapse__link"
            :class="route.toggle ? 'rotate' : ''"
            @click="route.toggle = !route.toggle"
          ></ion-icon>

          <ul class="collapse__menu" :class="route.toggle ? 'showCollapse' : ''">
            <li class="collapse__list my-2" v-for="children in route.children">
              <router-link :to="route.path + '/' + children.path">{{ children.name }}</router-link>
            </li>
          </ul>
        </div>
      </div>
      <a href="#" class="nav__link">
        <ion-icon name="log-out-outline" class="nav__icon"></ion-icon>
        <span class="nav_name">Logout</span>
      </a>
    </div>
  </nav>
</template>
