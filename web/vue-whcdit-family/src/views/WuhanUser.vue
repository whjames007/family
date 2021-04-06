<template>
  <div id="user">
    <!-- 查询条件 -->
    <div>
      <el-form :model="param" label-width="100px" :label-suffix="labelSuffix" size="mini">
        <el-row>
          <el-col :span="8"><el-form-item label="用户账号"><el-input :placeholder="placeholder" v-model="param.systemUserAccount"></el-input></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="用户昵称"><el-input :placeholder="placeholder" v-model="param.systemUserNickname"></el-input></el-form-item></el-col>
          <el-col :span="8"><el-form-item><el-button type="primary" @click="tableMethodSearch">查询</el-button> <el-button type="primary" plain @click="tableMethodReset">重置</el-button></el-form-item></el-col>
        </el-row>
      </el-form>
    </div>
    <!-- 分页表格 -->
    <div class="wuhan-table">
      <el-table :data="pages" stripe highlight-current-row>
        <template>
          <el-table-column prop="length" label="序号" type="index" width="60"/>
          <el-table-column label="注册时间" show-overflow-tooltip><template slot-scope="scope">{{methodDateFormat(scope.row.firstCreateDate)}}</template></el-table-column>
          <el-table-column prop="systemUserAccount" label="用户账号" show-overflow-tooltip/>
          <el-table-column prop="systemUserType" label="用户类型" show-overflow-tooltip/>
          <el-table-column label="登录时间" show-overflow-tooltip><template slot-scope="scope">{{methodDateFormat(scope.row.lastLoginTime)}}</template></el-table-column>
          <el-table-column prop="systemUserNickname" label="用户昵称" show-overflow-tooltip/>
          <el-table-column prop="systemRoleName" label="用户角色" show-overflow-tooltip/>
          <el-table-column prop="systemUserTelephone" label="联系电话" show-overflow-tooltip/>
          <el-table-column prop="systemUserEmail" label="电子邮箱" show-overflow-tooltip/>
          <el-table-column label="更新时间" show-overflow-tooltip><template slot-scope="scope">{{methodDateFormat(scope.row.lastUpdateDate)}}</template></el-table-column>
          <el-table-column label="操作" width="160" show-overflow-tooltip><template slot-scope="scope">
            <span @click="tableMethodOpen(scope.row)" class="tableOperate">编辑</span>
            <span @click="tableMethodDele(scope.row)" class="tableOperate">删除</span>
            <span @click="tableMethodInit(scope.row)" class="tableOperate">初始化</span></template>
          </el-table-column>
        </template>
      </el-table>
      <el-pagination @size-change="tableMethodSize" @current-change="tableMethodCurr" :current-page="param.pageNum" :page-sizes="pageArray" :page-size="param.pageSize" background layout="total, sizes, prev, pager, next, jumper" :total="totalRows"></el-pagination>
    </div>
  </div>
</template>

<script>
var log = '【用户管理子页面】'
export default {
  name: 'user',
  data () {
    return {
      placeholder: this.whu.cont.placeholder,
      labelSuffix: this.whu.cont.labelSuffix,
      param: {pageNum: null, pageSize: null, systemUserAccount: null, systemUserNickname: null},
      pages: [],
      totalRows: 0, // 总行数
      pageArray: this.whu.cont.pageArray,
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    this.whu.func.mountedInit(this)
    console.info(log, 'mounted')
  },
  methods: {
    methodDateFormat (val) {
      return this.whu.dfmt.dts(val)
    },
    tableMethodPage () {
      this.$axios.post(this.whu.cont.url.userPage, this.param)
        .then((res) => {
          console.info(log, res)
          const {code, page} = res.data
          if (this.whu.func.respSuccess(code)) {
            this.pages = page.list
            this.totalRows = page.total
          } else {
            this.whu.func.respExcep(this, code)
          }
        })
    },
    tableMethodReset () {
      this.param.systemUserAccount = null
      this.param.systemUserNickname = null
      this.tableMethodPage()
    },
    tableMethodSearch () {
      if (this.param.systemUserAccount || this.param.systemUserNickname) { this.param.pageNum = 1 }
      this.tableMethodPage()
    },
    tableMethodOpen (val) {
      this.dialogShow = true
      if (val) {
        // 调用编辑方法
        this.dialogMethodEdit(val)
        this.dialogTitle = '编辑'
        this.passwordStatus = true
        this.dialogPasswordShow = false
      } else {
        // 调用新增方法
        this.dialogMethodAdd()
        this.dialogTitle = '新增'
        this.passwordStatus = false
        this.dialogPasswordShow = true
      }
    },
    dialogMethodAdd () {
      this.dialogData = {
        uid: this.param.uid,
        userAccount: null,
        userPassword: null,
        roleId: 0,
        nickName: null,
        userTelephone: '13900000000',
        userEmail: 'adm139@gewei.com'
      }
    },
    dialogMethodEdit (val) {
      let crow = this.gwu.gmet.geweiClone(val)
      this.dialogData = {
        uid: this.param.uid,
        userId: crow.userId,
        userAccount: crow.userAccount,
        userPassword: '123456', // 先给个字符串蒙混过关
        nickName: crow.nickName
      }
    },
    dialogMethodSave () {
      // 输入项的校验规则
      this.$refs['dialogData'].validate((valid) => {
        if (valid) {
          this.dialogMethodCheck()
        } else {
          console.log(log, 'dialogMethodSave校验失败')
        }
      })
    },
    dialogMethodCheck () {
      // 针对用户账号的处理和校验，先去除空格
      let account = this.dialogData.userAccount.replace(/\s*/g, '')
      // 再判断用户账号是否是空串
      if (this.gwu.gmet.geweiNotNull(account)) {
        // 再判断用户账号是否是由数字和字母组成
        var reg1 = new RegExp(/^[0-9A-Za-z]+$/)
        if (reg1.test(account)) {
          // 第二次判断字符长度
          if (account.length >= 4 && account.length <= 30) {
            this.dialogData.userAccount = account
            console.info(log, '用户账号校验通过')
          } else {
            this.$message.error('用户账号的字母或数字字符的长度应在4到30之间！')
            return false
          }
        } else {
          this.$message.error('用户账号不能包含特殊字符，请输入字母或数字！')
          return false
        }
      } else {
        this.$message.error('用户账号不能全是空格！')
        return false
      }
      // 针对用户昵称的处理和校验，先去除空格
      let name = this.dialogData.nickName.replace(/\s*/g, '')
      if (this.gwu.gmet.geweiNotNull(name)) {
        this.dialogData.nickName = name
        console.info(log, '用户昵称校验通过')
      } else {
        this.$message.error('用户昵称不能全是空格！')
        return false
      }
      // 针对用户密码的处理和校验，先去除空格
      let password = this.dialogData.userPassword.replace(/\s*/g, '')
      if (this.gwu.gmet.geweiNotNull(password)) {
        // 再判断用户密码是否是由数字和字母组成
        var regPass = new RegExp(/^[0-9A-Za-z]+$/)
        if (regPass.test(password)) {
          this.dialogData.userPassword = password
          console.info(log, '用户密码校验通过')
        } else {
          this.$message.error('用户密码不能包含特殊字符，请输入字母或数字！！')
          return false
        }
      } else {
        this.$message.error('用户密码不能全是空格！')
        return false
      }
      console.info(log, '对话框校验全部通过')
      this.dialogMethodGoto()
    },
    dialogMethodGoto () {
      let url = null
      let msg = null
      if (this.dialogData.userId) {
        console.info(log, `调用后台编辑接口并置空密码`)
        url = this.gwu.cont.url.userEdit
        msg = this.gwu.cont.updateSuccess
        this.dialogData.userPassword = null
      } else {
        console.info(log, `调用后台新增接口`)
        url = this.gwu.cont.url.userAdd
        msg = this.gwu.cont.createSuccess
      }
      this.$axios.post(url, this.dialogData)
        .then((res) => {
          const { code } = res.data
          if (this.gwu.gmet.geweiRespSuccess(code)) {
            this.tableMethodPage()
            this.dialogMethodClose()
            this.$message.success(msg)
          } else {
            this.gwu.gmet.geweiRespParse(this, code)
            if (this.dialogData.userId) {
              this.dialogData.userPassword = 'what'
            }
          }
        })
    },
    dialogMethodClose () {
      this.dialogShow = false
      this.$refs['dialogData'].resetFields()
      console.info(log, `用户关闭了对话框！`)
    },
    tableMethodDele (val) {
      this.$confirm(this.gwu.cont.deleteQuestion, '删除'
      ).then(() => {
        val.uid = this.param.uid
        this.axios.post(this.gwu.cont.url.userDel, val)
          .then((res) => {
            if (this.gwu.gmet.geweiRespSuccess(res.data.code)) {
              this.$message.success(this.gwu.cont.deleteSuccess)
              this.tableMethodPage()
            } else {
              this.gwu.gmet.geweiRespParse(this, res.data.code)
            }
          })
      }).catch(() => {
        console.info(log, `用户放弃了删除操作！`)
      })
    },
    tableMethodInit (val) {
      this.$confirm(this.gwu.cont.userInitQuestion, '初始化'
      ).then(() => {
        val.uid = this.param.uid
        val.userPassword = '123456'
        this.axios.post(this.gwu.cont.url.userEdit, val)
          .then((res) => {
            if (this.gwu.gmet.geweiRespSuccess(res.data.code)) {
              this.$message.success(this.gwu.cont.userInitSuccess)
              this.tableMethodPage()
            } else {
              this.gwu.gmet.geweiRespParse(this, res.data.code)
            }
          })
      }).catch(() => {
        console.info(`用户放弃了初始化操作！`)
      })
    },
    tableMethodCurr (val) {
      this.param.pageNum = val
      this.tableMethodPage()
      console.info(log, `【tableMethodCurr函数】`, `【当前页: ${val}】`)
    },
    tableMethodSize (val) {
      this.param.pageSize = val
      this.tableMethodPage()
      console.info(log, `【tableMethodSize函数】`, `【每页共 ${val} 条】`)
    },
    bbb () {
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
