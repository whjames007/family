import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import WuhanLogin from '@/views/WuhanLogin'
import WuhanRegister from '@/views/WuhanRegister'

import WuhanHome from '@/views/WuhanHome'
import WuhanSummary from '@/views/WuhanSummary'
import WuhanTodo from '@/views/WuhanTodo'
import WuhanFamilyJoin from '@/views/WuhanFamilyJoin'
import WuhanFamilyCreate from '@/views/WuhanFamilyCreate'
import WuhanFamilyManager from '@/views/WuhanFamilyManager'
import WuhanUser from '@/views/WuhanUser'
import WuhanRole from '@/views/WuhanRole'
import WuhanMenu from '@/views/WuhanMenu'
Vue.use(Router)

let childs = [
  {path: 'summary', name: 'summary', component: WuhanSummary},
  {path: 'todo', name: 'todo', component: WuhanTodo},
  {path: 'join', name: 'join', component: WuhanFamilyJoin},
  {path: 'create', name: 'create', component: WuhanFamilyCreate},
  {path: 'famimgr', name: 'famimgr', component: WuhanFamilyManager},
  {path: 'user', name: 'user', component: WuhanUser},
  {path: 'role', name: 'role', component: WuhanRole},
  {path: 'menu', name: 'menu', component: WuhanMenu}
]

export default new Router({
  routes: [
    {path: '/', name: 'login', component: WuhanLogin},
    {path: '/register', name: 'register', component: WuhanRegister},
    {path: '/aaa', name: 'HelloWorld', component: HelloWorld},
    {path: '/home', name: 'home', component: WuhanHome, redirect: '/home/summary', children: childs}
  ]
})
