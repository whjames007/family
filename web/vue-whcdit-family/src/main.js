// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import {cont} from './commons/WuhanConstants.js'
import {func} from './commons/WuhanFunctions.js'
import {dfmt} from './commons/WuhanDateFormat.js'
import App from './App'

import axios from 'axios'
import VueAxios from 'vue-axios'

import router from './router'

Vue.use(ElementUI)
Vue.use(VueAxios, axios)

Vue.config.productionTip = false

Vue.prototype.$axios = axios
Vue.prototype.whu = {cont, func, dfmt}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
