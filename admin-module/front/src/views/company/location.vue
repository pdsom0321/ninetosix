<script setup>
import { reactive, watchEffect } from 'vue'
import { useCompanyStore } from '@/stores/company'
import lib from '@/util/apiUtil'

const store = useCompanyStore()

const locations = reactive([
  {
    id: 1,
    name: '역삼 GS 타워',
    latitude: 37.502,
    longitude: 127.037
  },
  {
    id: 2,
    name: '보헌 빌딩',
    latitude: 37.5784,
    longitude: 126.987
  },
  {
    id: 3,
    name: '원서 빌딩',
    latitude: 37.5792,
    longitude: 126.988
  }
])

watchEffect(() => {
  if (store.compId) {
    locations.splice(0)
    // _.merge(locations, [{ id: 1, name: 'test' }])
  }
})

const addList = reactive([])

const addLocation = (idx) => {
  //샘플, 실제로는 서버에 저장 후 다시 get api 호출해서 데이터 바인딩
  locations.push(addList[idx])
  addList.splice(idx, 1)
}

const saveLocation = (idx) => {
  locations[idx].editMode = false
}

const editLocation = (idx) => {
  locations[idx].editMode = true
}

const delLocation = (idx) => {
  if (confirm('정말 삭제하시겠습니까?')) {
    //삭제로직
    console.log(idx)
    locations.splice(idx, 1)
  }
}
</script>

<template>
  compId : {{ store.compId }}
  <!-- <div class="w-100 bg-danger">
    <button class="sticky-top btn btn-sm btn-outline-secondary" @click="addList.push({})">
      추가
    </button>
  </div> -->
  <table class="table table-bordered table-hover text-center align-middle">
    <colgroup>
      <col />
      <col width="90%" />
      <!-- <col /> -->
    </colgroup>
    <thead>
      <tr class="border-0">
        <th class="text-end border-0" colspan="100">
          <button class="btn btn-sm btn-outline-secondary" @click="addList.push({})">추가</button>
        </th>
      </tr>
      <tr>
        <th>ID</th>
        <th class="text-start">위치명 / 위도 / 경도</th>
        <!-- <th></th> -->
      </tr>
    </thead>
    <tbody>
      <tr v-for="(location, index) in locations">
        <td>{{ location.id }}</td>
        <td>
          <div class="input-group">
            <input
              type="text"
              class="form-control"
              :class="location.editMode ? '' : 'bg-transparent'"
              v-model="location.name"
              :disabled="!location.editMode"
            />
            <input
              type="text"
              class="form-control"
              :class="location.editMode ? '' : 'bg-transparent'"
              v-model="location.latitude"
              :disabled="!location.editMode"
            />
            <input
              type="text"
              class="form-control"
              :class="location.editMode ? '' : 'bg-transparent'"
              v-model="location.longitude"
              :disabled="!location.editMode"
            />
            <button
              v-if="!location.editMode"
              class="btn btn-sm btn-outline-secondary rounded-0"
              @click="editLocation(index)"
            >
              수정
            </button>
            <button
              v-if="!location.editMode"
              class="btn btn-sm btn-outline-danger rounded-0"
              @click="delLocation(index)"
            >
              삭제
            </button>
            <button v-else class="btn btn-sm btn-outline-success" @click="saveLocation(index)">
              저장
            </button>
          </div>
        </td>
      </tr>
      <tr v-for="(item, index) in addList">
        <td class="opacity-75">auto</td>
        <td>
          <div class="input-group">
            <input type="text" class="form-control" v-model="item.name" />
            <button class="btn btn-sm btn-outline-success rounded-0" @click="addLocation(index)">
              저장
            </button>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
</template>
