<script setup>
import router from '@/router'

const routes = router.options.routes.filter((x) => {
  return x.meta?.menu
})
</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="#"><strong>[0918]</strong> 관리자</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item" v-for="route in routes">
            <router-link class="nav-link" :to="route.path + '/' + route.children[0].path">
              {{ route.name }}
            </router-link>
          </li>
        </ul>
        <!-- <form class="d-flex" role="search">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form> -->
      </div>
    </div>
  </nav>
  <!-- <div>
    <router-link
      v-for="route in routes"
      class="p-0 ms-0 mx-2"
      :to="route.path + '/' + route.children[0].path"
    >
      {{ route.name }}
    </router-link>
  </div> -->
</template>
