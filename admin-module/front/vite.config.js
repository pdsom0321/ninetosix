import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import dotenv from 'dotenv'
import dotenvExpand from 'dotenv-expand'

dotenvExpand.expand(
  dotenv.config({
    path: path.join(__dirname, `env/.env.${process.env.profile}`)
  })
)
dotenvExpand.expand(
  dotenv.config({
    path: path.join(__dirname, `env/.env`)
  })
)

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    proxy: 'http://localhost:8081'
  },
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  build: {
    outDir: path.resolve(__dirname, '../src/main/resources/static/')
  }
})
