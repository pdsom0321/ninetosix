<script setup>
import { ref, reactive, onMounted } from 'vue'
import _ from 'lodash'

const emit = defineEmits()

const props = defineProps({
  data: {
    type: Object
  }
})

var map = null
var marker = null

const searchTxt = ref('')
const searchResult = reactive([])

const position = reactive({
  lat: 37.51082,
  lng: 127.02928
})

const initMap = () => {
  if (props.data.location) {
    ;(position.id = props.data.location.id),
      (position.name = props.data.location.name),
      (position.lat = props.data.location.latitude),
      (position.lng = props.data.location.longitude)
  }

  var container = document.getElementById('map')
  var options = {
    center: new kakao.maps.LatLng(position.lat, position.lng),
    level: 4
  }
  map = new kakao.maps.Map(container, options)

  marker = new kakao.maps.Marker({
    position: map.getCenter()
  })

  marker.setMap(map)

  map.setDraggable(true)

  kakao.maps.event.addListener(map, 'click', (mouseEvent) => {
    // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
    // 클릭한 위도, 경도 정보를 가져옵니다
    var latlng = mouseEvent.latLng

    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng)

    position.lat = latlng.getLat()
    position.lng = latlng.getLng()
  })
}

const search = () => {
  searchResult.splice(0)
  var places = new kakao.maps.services.Places()

  places.keywordSearch(searchTxt.value, (result, status) => {
    if (status === kakao.maps.services.Status.OK) {
      console.log(result)
      _.merge(searchResult, result)
    }
  })
}

const goPlace = (place) => {
  props.data.method = 'post'

  position.lat = place.y
  position.lng = place.x

  position.name = place.place_name

  var latlng = new kakao.maps.LatLng(position.lat, position.lng)
  map.panTo(latlng)
  marker.setPosition(latlng)
}

const addLocation = () => {
  if (!position.name) {
    alert('위치명을 반드시 입력해주세요')
    return false
  }
  if (confirm(`해당 위치를 ${props.data.method === 'post' ? '등록' : '수정'}하시겠습니까?`)) {
    if (props.data.method === 'post') emit('callback', 'addLocation', position)
    else emit('callback', 'saveLocation', position)
    emit('close')
  }
}

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap()
  } else {
    const script = document.createElement('script')
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_APP_KAKAOMAP_KEY
    }&libraries=services`
    script.addEventListener('load', () => {
      kakao.maps.load(initMap)
    })
    document.head.appendChild(script)
  }
})
</script>

<template>
  <div class="card h-100 border-0">
    <div class="card-header">
      <div class="input-group">
        <input type="text" class="form-control" v-model="searchTxt" />
        <button class="btn btn-outline-secondary" type="button" @click="search">검색</button>
      </div>
    </div>
    <div class="card-body p-0 h-100">
      <div id="map"></div>
    </div>
    <div class="card-footer">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="위치명" v-model="position.name" />
        <input type="text" class="form-control" v-model="position.lat" disabled />
        <input type="text" class="form-control" v-model="position.lng" disabled />
        <button class="btn btn-outline-success" type="button" @click="addLocation">
          {{ props.data.method === 'post' ? '등록' : '수정' }}
        </button>
      </div>
    </div>
    <div
      class="card-footer overflow-auto p-0 text-start"
      style="max-height: 600px"
      v-if="searchResult.length > 0"
    >
      <div class="accordion accordion-flush" id="acrod">
        <div class="accordion-item" v-for="place in searchResult">
          <h2 class="accordion-header">
            <button
              @click="goPlace(place)"
              class="accordion-button collapsed text-primary"
              type="button"
              data-bs-toggle="collapse"
              :data-bs-target="'#' + place.id"
              aria-expanded="false"
              :aria-controls="place.id"
            >
              {{ place.place_name }}
            </button>
          </h2>
          <div :id="place.id" class="accordion-collapse collapse" data-bs-parent="#acrod">
            <div class="accordion-body">
              <p>카테고리 : {{ place.category_name }}</p>
              <p>주소 : {{ place.address_name }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="card-footer" v-else>검색결과가 없습니다.</div>
  </div>
</template>

<style>
#map {
  height: 100%;
}
</style>
