import { createApp } from 'vue'
import modal from '@/components/modal.vue'

let app, container, vueApp

const dialogModule = {
  create() {
    app = createApp(modal)
    container = document.createElement('div')
    document.body.append(container)
    vueApp = app.mount(container)
  },
  show() {
    if (!app) {
      dialogModule.create()
    }
    vueApp.show()
  },
  hide() {
    if (!vueApp) {
      return
    }
    vueApp.hide()
    app.unmount()
  }
}

export default dialogModule
