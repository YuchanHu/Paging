import { createRouter, createWebHashHistory } from 'vue-router'
import User from '../views/user.vue'

const routes = [
  {
    path: '/',
    name: 'user',
    component: User
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
