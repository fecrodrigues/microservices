import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue') 
  },
  {
    path: '/cadastro',
    name: 'Signin',
    component: () => import('../views/Signin.vue')
  },
  {
    path: '/painel',
    name: 'ControlPanel',
    meta: { secure: true },
    component: () => import('../views/ControlPanel.vue')
  },
  {
    path: '/perfil',
    name: 'LawyerProfile',
    meta: { secure: true },
    component: () => import('../views/LawyerProfile.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {

  if(to.meta.secure === true) {
    if(!localStorage.getItem('userLogged') && to.path !== '/login') {
      next({ path: '/login' });  
    } else {
        next();
    }
  } else {
    next();
  }
  
})

export default router
