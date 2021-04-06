<template>
  <div class="hello">
    <div class="local-login-center2">
        <div class="title">账号登录</div>
        <el-form :model="param" ref="param">
          <el-form-item prop="userAccount" label="账号">
            <el-input prefix-icon="el-icon-user" v-model="param.systemUserAccount" placeholder="请输入账号" @keyup.enter.native="loginMethodSecond"></el-input>
          </el-form-item>
          <el-form-item prop="userPassword" label="密码">
            <el-input prefix-icon="el-icon-lock" v-model="param.systemUserPassword" type="password" placeholder="请输入密码"  @keyup.enter.native="loginMethodSecond"></el-input>
          </el-form-item>
          <el-button type="primary" @click="loginMethodSecond">登录</el-button>
        </el-form>
      </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
