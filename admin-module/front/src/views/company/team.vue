<script setup>
import { reactive, watchEffect } from 'vue'
import { useCompanyStore } from '@/stores/company'
import lib from '@/util/apiUtil'
import _ from 'lodash'

const store = useCompanyStore()

const teams = reactive([])
const addList = reactive([])

const teamsAPI = ({ id, method, data }, callback) => {
  // get, post, put, delete
  const options = {
    url: id ? `/team/${id}` : '/team',
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

const getTeams = () => {
  teams.splice(0)
  const param = {
    id: store.compId,
    method: 'get'
  }
  teamsAPI(param, (res) => {
    _.merge(teams, res)
  })
}

watchEffect(() => {
  if (store.compId) {
    teams.splice(0)
    addList.splice(0)
    getTeams()
  }
})

const addTeam = (idx) => {
  const param = {
    method: 'post',
    data: {
      name: addList[idx].name,
      companyId: store.compId
    }
  }

  teamsAPI(param, () => {
    addList.splice(idx, 1)
    getTeams()
  })
}

const saveTeam = (idx) => {
  // teams[idx].editMode = false
  const param = {
    id: teams[idx].id,
    method: 'put',
    data: {
      name: teams[idx].name
    }
  }

  teamsAPI(param, () => {
    getTeams()
  })
}

const delTeam = (idx) => {
  if (confirm('정말 삭제하시겠습니까?')) {
    const param = {
      id: teams[idx].id,
      method: 'delete'
    }

    teamsAPI(param, () => {
      getTeams()
    })
  }
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
            @click="addList.push({})"
            :disabled="!store.compId"
          >
            추가
          </button>
        </th>
      </tr>
      <tr>
        <th>ID</th>
        <th>팀명</th>
        <!-- <th></th> -->
      </tr>
    </thead>
    <tbody v-if="store.compId">
      <tr v-for="(team, index) in teams">
        <td>{{ team.id }}</td>
        <td>
          <div class="input-group">
            <input
              type="text"
              class="form-control"
              :class="team.editMode ? '' : 'bg-transparent border-0'"
              v-model="team.name"
              :disabled="!team.editMode"
            />
            <button
              v-if="!team.editMode"
              class="btn btn-sm btn-outline-secondary rounded-0"
              @click="team.editMode = true"
            >
              수정
            </button>
            <button
              v-if="!team.editMode"
              class="btn btn-sm btn-outline-danger rounded-0"
              @click="delTeam(index)"
            >
              삭제
            </button>
            <button v-else class="btn btn-sm btn-outline-success" @click="saveTeam(index)">
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
            <button class="btn btn-sm btn-outline-success rounded-0" @click="addTeam(index)">
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
    <tbody></tbody>
  </table>
</template>
