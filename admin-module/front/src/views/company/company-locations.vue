<script setup>
import { reactive, watchEffect } from 'vue'
import { useCompanyStore } from '@/stores/company'
import lib from '@/util/apiUtil'
import _ from 'lodash'
import popupModule from '@/util/popupModule'
import dialogModule from '@/util/dialogModule'

const store = useCompanyStore()

const locations = reactive([])

const locationsAPI = ({ id, method, data }, callback) => {
  const options = {
    url: id ? `/company-location/${id}` : '/company-location',
    method: method
  }

  if (data) options.data = data

  try {
    lib.api(options).then((res) => {
      callback(res)
    })
  } catch (error) {
    console.log(error)
  }
}

const getLocations = () => {
  locations.splice(0)
  const param = {
    id: store.compId,
    method: 'get'
  }
  locationsAPI(param, (res) => {
    _.merge(locations, res)
  })
}

watchEffect(() => {
  if (store.compId) {
    getLocations()
  }
})

const delLocation = (idx) => {
  if (confirm('정말 삭제하시겠습니까?')) {
    //삭제로직
    const param = {
      id: locations[idx].id,
      method: 'delete'
    }
    locationsAPI(param, (res) => {
      getLocations()
    })
  }
}

const openModal = () => {
  // dialogModule.show()
  popupModule
    .popup({
      component: import('@/views/company/locations.vue'),
      data: {
        isModal: true
      }
    })
    .ok((data) => {
      console.log(data)
    })
}
</script>

<template>
  <table class="table table-bordered table-hover text-center align-middle">
    <colgroup>
      <col />
      <col width="90%" />
      <!-- <col /> -->
    </colgroup>
    <thead>
      <tr class="border-0">
        <th class="text-end border-0" colspan="100">
          <button
            class="btn btn-sm btn-outline-secondary"
            @click="openModal"
            :disabled="!store.compId"
          >
            추가
          </button>
        </th>
      </tr>
      <tr>
        <th>ID</th>
        <th>위치명 / 위도 / 경도</th>
        <!-- <th></th> -->
      </tr>
    </thead>
    <tbody v-if="store.compId">
      <tr v-for="(location, index) in locations">
        <td>{{ location.id }}</td>
        <td>
          <div class="input-group">
            <input
              type="text"
              class="form-control text-center border-0 bg-transparent"
              v-model="location.locationName"
              readonly
            />
            <input
              type="text"
              class="form-control text-center border-0 bg-transparent"
              v-model="location.latitude"
              readonly
            />
            <input
              type="text"
              class="form-control text-center border-0 bg-transparent"
              v-model="location.longitude"
              readonly
            />
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
    </tbody>
    <tbody v-else>
      <tr>
        <td colspan="100">회사를 선택해주세요</td>
      </tr>
    </tbody>
  </table>
</template>
