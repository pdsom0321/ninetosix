<script setup>
import company from './company.vue'
import team from './team.vue'
import location from './location.vue'
</script>

<template>
  <div class="row m-0 h-100">
    <div class="col-6 h-100 overflow-auto">
      <ul class="nav nav-tabs" id="mainTab" role="tablist">
        <li class="nav-item" role="presentation">
          <button
            class="nav-link active"
            id="comp-tab"
            data-bs-toggle="tab"
            data-bs-target="#comp-tab-pane"
            type="button"
            role="tab"
            aria-controls="comp-tab-pane"
            aria-selected="true"
          >
            회사
          </button>
        </li>
      </ul>
      <div class="tab-content" id="mainTabContent">
        <div
          class="tab-pane fade show active"
          id="comp-tab-pane"
          role="tabpanel"
          aria-labelledby="comp-tab"
          tabindex="0"
        >
          <company />
        </div>
      </div>
    </div>
    <div class="col-6">
      <ul class="nav nav-tabs" id="subTab" role="tablist">
        <li class="nav-item" role="presentation">
          <button
            class="nav-link active"
            id="team-tab"
            data-bs-toggle="tab"
            data-bs-target="#team-tab-pane"
            type="button"
            role="tab"
            aria-controls="team-tab-pane"
            aria-selected="true"
          >
            팀
          </button>
        </li>
        <li class="nav-item" role="presentation">
          <button
            class="nav-link"
            id="location-tab"
            data-bs-toggle="tab"
            data-bs-target="#location-tab-pane"
            type="button"
            role="tab"
            aria-controls="location-tab-pane"
            aria-selected="false"
          >
            위치
          </button>
        </li>
      </ul>
      <div class="tab-content" id="subTabContent">
        <div
          class="tab-pane fade show active"
          id="team-tab-pane"
          role="tabpanel"
          aria-labelledby="team-tab"
          tabindex="0"
        >
          <team />
        </div>
        <div
          class="tab-pane fade"
          id="location-tab-pane"
          role="tabpanel"
          aria-labelledby="location-tab"
          tabindex="0"
        >
          <location />
        </div>
      </div>
    </div>
  </div>
</template>
