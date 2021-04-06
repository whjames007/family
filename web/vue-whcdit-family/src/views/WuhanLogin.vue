<template>
  <div id="login" >
    <!-- 上 -->
    <div class="local-login-top"><img class="local-login-img" src="static/image/logo-bmw.svg"/></div>
    <!-- 中 -->
    <div class="local-login-mid">
      <div class="local-login-form">
        <el-form :model="param" ref="param" label-width="80px" :label-suffix="labelSuffix" size="mini">
          <el-form-item>欢迎使用正维电子拍照系统</el-form-item>
          <el-form-item prop="userAccount" label="账号"><el-input prefix-icon="el-icon-user" v-model="param.systemUserAccount" placeholder="请输入账号" @keyup.enter.native="loginMethodSecond"></el-input></el-form-item>
          <el-form-item prop="userPassword" label="密码"><el-input prefix-icon="el-icon-lock" v-model="param.systemUserPassword" type="password" placeholder="请输入密码"  @keyup.enter.native="loginMethodSecond"></el-input></el-form-item>
          <el-form-item><el-button type="primary" icon="el-icon-caret-right" @click="loginMethodSecond">登录</el-button><el-button type="danger" icon="el-icon-s-check" @click="loginMethodRegister">注册</el-button></el-form-item>
        </el-form>
      </div>
    </div>
    <!-- 下 -->
    <div class="local-login-fot">©2021 华为技术有限公司 粤A2-20044005号 粤公网安备  44030702002388号</div>
  </div>
</template>

<script>
var log = '【登录页面】'
export default {
  name: 'login',
  data () {
    return {
      labelSuffix: this.whu.cont.labelSuffix,
      param: {systemUserAccount: 'hhhh', systemUserPassword: 'hhhh'},
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    console.info(log, 'mounted')
  },
  methods: {
    loginMethodFirst () {
      // 先做校验
      let check = true
      if (check) {
        this.loginMethodSecond()
      } else {
        console.info(log, '校验不通过')
      }
    },
    loginMethodSecond () {
      this.$axios.post(this.whu.cont.url.userLogin, this.param)
        .then((res) => {
          console.info(log, res)
          const {code, data} = res.data
          if (this.whu.func.respSuccess(code)) {
            this.whu.func.setUser(data)
            this.$router.push({name: 'home'})
            console.info(log, '登陆成功，即将跳转进入主页面')
          } else {
            this.whu.func.respExcep(this, code)
          }
        })
    },
    loginMethodRegister () {
      this.$router.push({name: 'register'})
    }
  }
}
</script>

<style lang="scss" scoped>
#login {
  min-height: 100vh; width: 100%; background: url(../assets/backg.png) no-repeat center 0px;
  background-position: center 0px;
  background-size: cover;
}
.local-login-top {
  height: 10vh; width: 100%; display: flex; align-items: center;
}
.local-login-mid {
  height: 80vh; width: 100%; display: flex; align-items: center; justify-content: center;
}
.local-login-fot {
  height: 10vh; width: 100%; display: flex; align-items: center; justify-content: center;
}
.local-login-form {
  height: 300px; width: 300px; background: #FFFFFF; display: flex; align-items: center; justify-content: center;
}
.local-login-img {
 margin: 0px 0px 0px 10px;
}
</style>
