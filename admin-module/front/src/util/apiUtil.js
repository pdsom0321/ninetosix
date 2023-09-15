import axios from 'axios'

const instance = axios.create({
  baseURL: import.meta.env.VITE_APP_API_URL,
  headers: {
    'Content-Type': 'application/json;charset=UTF-8',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': '*',
    'Access-Control-Allow-Methods': 'GET,POST,PUT,PATCH,DELETE,HEAD,OPTIONS'
  },
  method: 'post',
  timeout: 5000
})

instance.interceptors.request.use(
  (config) => {
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

instance.interceptors.response.use(
  (response) => {
    return response
  },
  async (error) => {
    return Promise.reject(error)
  }
)

const axiosModule = {
  instance,
  async api(options) {
    try {
      let response = await instance(options)

      return Promise.resolve(response.data)
    } catch (error) {
      return Promise.reject(error)
    }
  }
}

export default axiosModule
