<template>
  <div id="WuhanHomeHeader">
    <el-row>
      <el-col :span="8">
        <el-menu router :default-active="activeMenu" @select="menuMethodSelect" background-color="#409EFF" text-color="#FFFFFF" active-text-color="#E6A23C" mode="horizontal">
          <!-- 根菜单 -->
          <el-submenu index="root"><template slot="title">{{menu.systemMenuName}}</template>
            <!-- 树枝菜单 -->
            <template v-for="branch in menu.childs">
              <el-submenu :key="branch.systemMenuUrl" :index="branch.systemMenuUrl"><template slot="title"><i :class="branch.systemMenuIcon"></i>{{branch.systemMenuName}}</template>
                <!-- 叶子菜单 -->
                <template v-for="leaf in branch.childs"><el-menu-item :key="leaf.systemMenuUrl" :index="leaf.systemMenuUrl"><i :class="leaf.systemMenuIcon"></i>{{leaf.systemMenuName}}</el-menu-item></template>
              </el-submenu>
            </template>
          </el-submenu>
        </el-menu>
      </el-col>
      <el-col :span="8"><div class="local-style-center">正维电子货架商品监控系统</div></el-col>
      <el-col :span="8"><div class="local-style-right">
        <i class="el-icon-user-solid"></i>{{user.systemUserNickname}}&nbsp;[{{user.systemRoleName}}]
        <el-popconfirm title="您好，您确定要退出本系统吗？" @confirm="logoutMethodConfirm"><el-button slot="reference" type="primary" icon="el-icon-s-promotion" class="wuhan-color-danger">退出</el-button></el-popconfirm>
      </div></el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-tabs v-model="activeTabs" type="card" @tab-click="tabMethodClick">
          <el-tab-pane label="用户管理" name="first"></el-tab-pane>
          <el-tab-pane label="配置管理" name="second"></el-tab-pane>
          <el-tab-pane label="角色管理" name="user"></el-tab-pane>
          <el-tab-pane label="定时任务补偿" name="summary"></el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>活动管理</el-breadcrumb-item>
          <el-breadcrumb-item>活动列表</el-breadcrumb-item>
          <el-breadcrumb-item>活动详情</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
  </div>
</template>

<script>
var log = '【主页的header组件】'
var activeFirst = 'summary'
export default {
  name: 'WuhanHomeHeader',
  data () {
    return {
      activeMenu: null,
      activeTabs: null,
      user: {},
      menu: {},
      menuList: [],
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    this.user = this.whu.func.getUser()
    this.menu = this.user.menu
    this.activeMenu = activeFirst
    this.activeTabs = activeFirst
    // 提前将菜单基础数据缓存起来
    this.$axios.post(this.whu.cont.url.userMenus, this.user)
      .then((res) => {
        const {code, list} = res.data
        if (this.whu.func.respSuccess(code)) {
          this.menuList = list
          console.info(log, '提前将菜单基础数据缓存起来')
        } else {
          this.whu.func.respParse(this, code)
        }
      })
    console.info(log, 'mounted', this.menu)
  },
  methods: {
    menuMethodSelect (key, keyPath) {
      this.activeTabs = key
      console.info(log, key, keyPath)
    },
    tabMethodClick (key, keyPath) {
      console.info(log, key, keyPath)
    },
    logoutMethodConfirm () {
      console.info(log, `用户决定退出系统！`)
      sessionStorage.clear()
      this.$router.push({name: 'login'})
    },
    aaa () {
      console.info(log, 'aaa')
    }
  }
}
</script>

<style lang="scss" scoped>
$headerHeight: 60px;
$headerWidth: 100%;
$headerFontColor: #FFFFFF;
.local-style-center {
  height: $headerHeight; width: $headerWidth; display: flex; align-items: center; justify-content: center; color: $headerFontColor; font-size: 24px;
}
.local-style-right {
  height: $headerHeight; width: $headerWidth; display: flex; align-items: center; justify-content: flex-end; color: $headerFontColor; font-size: 14px;
}
</style>
