<script setup>
import sidebar from './sidebar.vue'
import topbar from './topbar.vue'
</script>

<template>
  <div class="card h-100 rounded-0">
    <div class="card-header">
      <topbar />
    </div>
    <div class="card-body h-100 p-0">
      <div class="row m-0 h-100">
        <div class="col-2 shadow p-2">
          <sidebar />
        </div>
        <div class="col-10 h-100 p-2">
          <router-view />
        </div>
      </div>
    </div>
  </div>
</template>

<!-- <style>
.containertest {
  display: flex;
  height: 100%;
}
.leftbar {
  width: auto;
  height: 100%;
  padding-right: 20px;
  border-right: 1px solid lightgray;
}
.main {
  width: auto;
  height: 100%;
  padding: 10px;
}
</style> -->
