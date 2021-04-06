<template>
  <div id="register" class="wuhan-div-center">
    <div>
      <div class="local-reg-step"><el-steps :active="stepActive"><el-step title="填写注册信息"></el-step><el-step title="完成注册"></el-step></el-steps></div>
      <div class="local-div-reg" v-show="stepActive == 1">
        <el-form :model="param" label-width="100px" :label-suffix="labelSuffix">
          <el-form-item label="用户账号"><el-input :placeholder="placeholder1" v-model="param.systemUserAccount"></el-input></el-form-item>
          <el-form-item label="用户密码"><el-input :placeholder="placeholder2" v-model="param.systemUserPassword"></el-input></el-form-item>
          <el-form-item label="确认密码"><el-input :placeholder="placeholder3" v-model="param.systemConfPassword"></el-input></el-form-item>
          <el-form-item label="用户昵称"><el-input :placeholder="placeholder4" v-model="param.systemUserNickname"></el-input></el-form-item>
        </el-form>
        <el-button style="width: 100%;" type="danger" icon="el-icon-s-check" @click="formMethodRegister">用户注册</el-button>
      </div>
      <div class="local-div-lgn" v-show="stepActive == 2">
        您好，您的账号{{param.systemUserAccount}}已成功注册，现在可以登录本系统了。
        <el-button style="width: 100%;" type="primary" icon="el-icon-caret-right" @click="formMethodLogin">用户登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>
var log = '【注册页面】'
export default {
  name: 'register',
  data () {
    return {
      stepActive: 1,
      labelSuffix: this.whu.cont.labelSuffix,
      param: {uid: 10, systemUserAccount: null, systemUserNickname: null, systemUserPassword: null, systemConfPassword: null},
      placeholder1: '请输入3至16位字母或数字',
      placeholder2: '请输入3至16位字母或数字',
      placeholder3: '请再次输入密码',
      placeholder4: '请输入3至16位字符',
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    console.info(log, 'mounted')
  },
  methods: {
    formMethodRegister () {
      this.$axios.post(this.whu.cont.url.userReg, this.param)
        .then((res) => {
          console.info(log, res)
          const {code, data} = res.data
          if (this.whu.func.respSuccess(code)) {
            if (data.systemUserId > 0) {
              this.stepActive = 2
            }
          } else {
            this.whu.func.respExcep(this, code)
          }
        })
      console.info(log, 'aaa')
    },
    formMethodLogin () {
      this.$router.push({name: 'login'})
    }
  }
}
</script>

<style lang="scss" scoped>
.local-reg-step {
  width: 30vw; margin: 60px 0px 0px 0px;
}
.local-div-reg {
  width: 30vw; padding: 30px 30px 0px 30px;
}
.local-div-lgn {
  width: 30vw; margin: 160px 0px 0px 0px;
}
</style>
