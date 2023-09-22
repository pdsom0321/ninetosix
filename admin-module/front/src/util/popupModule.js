import { createApp, h } from 'vue'
import _ from 'lodash'
import Popup from '@/components/popup.vue'

const popupModule = {
  popup(settings) {
    if (!settings.popup && !settings.component) {
      console.warn('popup의 component가 정의되지 않았습니다.')
      return
    }

    const promise = new Promise((resolve) => {
      settings._popupFinal = () => {
        resolve()
      }
    })

    Object.assign(promise, {
      ok(resolve) {
        settings._ok = resolve
        return promise
      },
      close(resolve) {
        settings._ok = resolve
        return promise
      },
      final(resolve) {
        settings._final = resolve
        return promise
      }
    })

    Object.assign(settings, {
      $promise() {
        return promise
      }
    })

    const container = document.createElement('div')
    document.body.append(container)

    const popup = settings.popup ?? Popup
    const app = createApp({ render: () => h(popup, { settings }) })

    const vueApp = app.mount(container)

    Object.assign(settings, {
      $app() {
        return app
      },
      $vueApp() {
        return vueApp
      },
      $unmount() {
        app.unmount()
        container.remove()
      }
    })

    return promise
  }
}

export default popupModule
