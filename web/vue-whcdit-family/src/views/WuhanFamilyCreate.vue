<template>
  <div id="create" class="wuhan-div-center">
    <div class="wuhan-div-form">
      <el-form :model="param" label-width="100px" :label-suffix="labelSuffix">
        <el-form-item label="当前身份"><el-tag effect="plain">{{user.systemRoleName}}</el-tag>{{user.systemRoleId}}</el-form-item>
        <el-form-item label="家族名称"><el-input :placeholder="placeholder1" v-model="param.familyInfoName"></el-input></el-form-item>
        <el-form-item label="家族族长"><el-tag>{{user.systemUserNickname}}</el-tag></el-form-item>
        <el-form-item label="创始资金"><el-input :placeholder="placeholder3" v-model="param.familyAccountAmount"><template slot="append">（元）</template></el-input></el-form-item>
        <el-form-item label="家族简介"><el-input :placeholder="placeholder2" v-model="param.familyInfoDesc" type="textarea" :rows="5"></el-input></el-form-item>
      </el-form>
      <el-button style="width: 100%;" type="primary" icon="el-icon-collection-tag" @click="formMethodCreate">创建家族</el-button>
    </div>
  </div>
</template>

<script>
var log = '【家族创建子页面】'
export default {
  name: 'create',
  data () {
    return {
      user: {},
      labelSuffix: this.whu.cont.labelSuffix,
      param: {uid: null, familyInfoName: '超级兵王', familyInfoDesc: '穿越回大宋。。。', familyAccountAmount: 1200.34},
      placeholder1: '请输入家族名称，不超过30个字符！',
      placeholder2: '请输入家族简介，不超过100个字符！',
      placeholder3: '请再次输入密码',
      placeholder4: '请输入家族名称，不超过30个字符！',
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    this.whu.func.mountedInit(this)
  },
  methods: {
    formMethodCreate () {
      this.$axios.post(this.whu.cont.url.familyAdd, this.param)
        .then((res) => {
          if (this.whu.func.respSuccess(res.data.code)) {
            this.alertMethodOpen()
          } else {
            this.whu.func.respExcep(this, res.data.code)
          }
        })
    },
    alertMethodOpen () {
      this.$alert('您好，家族建立成功，请重新登录系统！', '提示', {
        type: 'success',
        showClose: false,
        callback: action => {
          sessionStorage.clear()
          this.$router.push({name: 'login'})
        }
      })
    },
    tableMethodPage () {
      console.info(log, '假tableMethodPage')
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
