import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useCompanyStore = defineStore('company', () => {
  const compId = ref()

  return { compId }
})
