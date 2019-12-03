import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Signup from './views/Signup.vue'
import Policies from './views/Policies.vue'
import Contact from './views/Contact.vue'
import Search from './views/Search_1.vue'
import Soa from './views/Soa.vue'
import NewSoa from './views/newSoa.vue'
import Classificationscheme from './views/Classificationscheme.vue'
import Viewclassificationscheme from './views/viewclassificationscheme.vue'
import Profile from './views/profile.vue'
import MySoa from './views/mySoa.vue'
import Favorite from './views/favorite.vue'
import Userprofile from './views/userprofile.vue'
import UserSoa from './views/UserSoa.vue'
import ModHistory from './views/modHistory'
Vue.use(Router)

let router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login
      
    },

    {
        path: '/signup',
        name: 'signup',
        component: Signup
    },

    {
      path: '/policies',
      name: 'policies',
      component: Policies
    },
    {
      path: '/contact',
      name: 'contact',
      component: Contact
    },
    {
      path: '/soa',
      name : 'soa',
      component: Soa
    },

    {
      path: '/modHistory',
      name : 'modHistory',
      component: ModHistory
    },
    {
      path: '/userSoa',
      name : 'userSoa',
      component: UserSoa
    },

    {
      path: '/newSoa',
      name : 'newSoa',
      component: NewSoa
    },

    {
      path: '/classificationscheme',
      name: 'classificationscheme',
      component: Classificationscheme
    },
    {
      path: '/viewclassificationscheme',
      name: 'viewclassificationscheme',
      component: Viewclassificationscheme
    },
    {
      path: '/profile',
      name: 'profile',
      component: Profile
    },
    {
      path: '/userprofile',
      name: 'userprofile',
      component: Userprofile
    },
    {
      path: '/mySoa',
      name: 'mySoa',
      component: MySoa
    },
    {
      path: '/favorite',
      name: 'favorite',
      component: Favorite
    },
    {
        path: '/search',
        name: 'search',
        component: Search
    }
  ]
})

export default router
