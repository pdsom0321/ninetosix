<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import lib from '@/util/apiUtil'
import { useCompanyStore } from '@/stores/company'
import _ from 'lodash'

const store = useCompanyStore()

const props = defineProps({
  data: {
    type: Object
  }
})
const search = ref('')
const locations = reactive([])
const addList = reactive([])

const locationAPI = ({ id, method, data }, callback) => {
  const options = {
    url: id ? `/location/${id}` : '/location',
    method: method
  }

  if (data) options.data = data

  lib.api(options).then((res) => {
    callback(res)
  })
}

const getLocations = () => {
  locations.splice(0)

  const param = {
    method: 'get'
  }

  locationAPI(param, (res) => {
    let a = res.map((obj) => {
      return {
        ...obj,
        companies: obj.companyList.length
      }
    })

    //해당 회사에 등록된 경우
    let b = a
      .filter((x) => {
        return x.companyList.findIndex((x) => x.id === store.compId) > -1
      })
      .sort((a, b) => {
        //id 기준
        // return a.id - b.id

        //name 기준
        return a.name < b.name ? -1 : a.name > b.name ? 1 : 0
      })

    //해당 회사에 등록되지 않은 경우
    let c = a
      .filter((x) => {
        return x.companyList.findIndex((x) => x.id === store.compId) == -1
      })
      .sort((a, b) => {
        //id 기준
        // return a.id - b.id

        //name 기준
        return a.name < b.name ? -1 : a.name > b.name ? 1 : 0
      })

    _.merge(locations, [...b, ...c])

    // _.merge(
    //   locations,
    //   res
    //     .map((obj) => {
    //       return {
    //         ...obj,
    //         companies: obj.companyList.length
    //       }
    //     })
    //     .sort((a, b) => {
    //       return a.name < b.name ? -1 : a.name > b.name ? 1 : 0
    //     })
    //     .sort((s) => {
    //       return s.companyList.findIndex((x) => x.id === store.compId)
    //     })
    //     .toReversed()
    // )
  })
}

const filteredLocList = computed(() => {
  return locations.filter((x) => x.name.indexOf(search.value) > -1)
})

const addLocation = (idx) => {
  const param = {
    method: 'post',
    data: {
      name: addList[idx].name,
      latitude: addList[idx].latitude,
      longitude: addList[idx].longitude
    }
  }

  locationAPI(param, () => {
    addList.splice(idx, 1)
    getLocations()
  })
}

const saveLocation = (idx) => {
  const param = {
    id: locations[idx].id,
    method: 'put',
    data: {
      name: locations[idx].name,
      latitude: locations[idx].latitude,
      longitude: locations[idx].longitude
    }
  }

  locationAPI(param, () => {
    getLocations()
  })
}

const delLocation = (idx) => {
  if (confirm('정말 삭제하시겠습니까?')) {
    const param = {
      id: locations[idx].id,
      method: 'delete'
    }

    locationAPI(param, () => {
      getLocations()
    })
  }
}

const addCompanyLocation = (idx) => {
  lib
    .api({
      url: 'company-location',
      data: {
        companyId: store.compId,
        locationId: locations[idx].id
      }
    })
    .then(() => {
      getLocations()
      let compId = store.compId
      store.compId = 0
      store.compId = compId
    })
}

const setSearch = (event) => {
  search.value = event.target.value
}

;(() => {
  getLocations()
})()
</script>

<template>
  <div class="text-center h-100">
    <div class="card h-100 rounded-0">
      <div class="card-header">
        <input type="text" class="form-control" placeholder="위치 검색" @input="setSearch" />
      </div>
      <!-- <div class="card-header text-end">
        <button class="btn btn-sm btn-outline-secondary">추가</button>
      </div> -->
      <div class="card-body h-100 overflow-auto">
        <table class="table table-hover align-middle">
          <!-- <colgroup>
            <col width="" />
            <col width="" />
            <col width="" />
            <col width="" />
            <col width="" />
            <col width="10%" />
          </colgroup> -->
          <thead class="text-end">
            <tr>
              <th colspan="100">
                <button class="btn btn-sm btn-outline-secondary" @click="addList.push({})">
                  신규
                </button>
              </th>
            </tr>
          </thead>
          <thead>
            <tr>
              <th>ID</th>
              <th>위치명</th>
              <th>위도</th>
              <th>경도</th>
              <th v-if="!props.data?.isModal">매핑된 회사 수</th>
              <th colspan="100"></th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="(location, index) in filteredLocList">
              <td>{{ location.id }}</td>
              <td>
                <input
                  class="form-control text-center"
                  :class="!location.editMode ? 'border-0 bg-transparent' : ''"
                  type="text"
                  :readonly="!location.editMode"
                  v-model="location.name"
                />
              </td>
              <td>
                <input
                  class="form-control text-center"
                  :class="!location.editMode ? 'border-0 bg-transparent' : ''"
                  type="text"
                  :readonly="!location.editMode"
                  v-model="location.latitude"
                />
              </td>
              <td>
                <input
                  class="form-control text-center"
                  :class="!location.editMode ? 'border-0 bg-transparent' : ''"
                  type="text"
                  :readonly="!location.editMode"
                  v-model="location.longitude"
                />
              </td>
              <td>
                <button class="text-primary border-0 bg-transparent" v-if="!props.data?.isModal">
                  {{ location.companies }}
                </button>
              </td>
              <td v-if="!props.data?.isModal">
                <div class="btn-group" v-if="!location.editMode">
                  <button
                    class="btn btn-sm btn-outline-secondary"
                    @click="location.editMode = true"
                  >
                    수정
                  </button>
                  <button class="btn btn-sm btn-outline-danger" @click="delLocation(index)">
                    삭제
                  </button>
                </div>
                <button v-else class="btn btn-sm btn-outline-success" @click="saveLocation(index)">
                  저장
                </button>
              </td>
              <td v-else>
                <span v-if="location.companyList.findIndex((x) => x.id === store.compId) > -1"
                  ><img src="@/assets/image/icon/accept.png" /><!--등록 됨--></span
                >
                <button
                  v-else
                  @click="addCompanyLocation(index)"
                  class="btn btn-sm btn-outline-secondary fw-bold"
                >
                  <span class="fw-bold">+</span>
                </button>
              </td>
            </tr>
            <tr v-for="(item, index) in addList">
              <td>auto</td>
              <td>
                <input class="form-control" type="text" v-model="item.name" />
              </td>
              <td>
                <input class="form-control" type="text" v-model="item.latitude" />
              </td>
              <td>
                <input class="form-control" type="text" v-model="item.longitude" />
              </td>
              <td v-if="!props.data?.isModal">-</td>
              <td colspan="100">
                <button class="btn btn-sm btn-outline-success" @click="addLocation(index)">
                  저장
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
