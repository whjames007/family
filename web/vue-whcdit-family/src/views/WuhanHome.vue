<template>
  <div id="home">
      <div class="local-home-head">
        <el-row>
          <el-col :span="8">
            <el-menu router :default-active="activeMenu" @select="menuMethodSelect" background-color="#409EFF" text-color="#FFFFFF" active-text-color="#FFCCFF" mode="horizontal">
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
          <el-col :span="8"><div class="local-home-center"><img src="static/image/logo-bmw.svg"/>正维电子货架商品监控系统</div></el-col>
          <el-col :span="8"><div class="local-home-right"><i class="el-icon-user-solid"></i>{{user.systemUserNickname}}&nbsp;[{{user.systemRoleName}}]<el-popconfirm title="您好，您确定要退出本系统吗？" @confirm="logoutMethodConfirm"><el-button slot="reference" type="primary" size="mini" icon="el-icon-s-promotion" class="wuhan-color-danger">退出</el-button></el-popconfirm></div></el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-tabs v-model="activeTabs" type="card" @tab-remove="tabMethodRemove" @tab-click="tabMethodClick">
              <el-tab-pane :key="item.systemMenuUrl" v-for="(item) in tabList" :label="item.systemMenuName" :name="item.systemMenuUrl" :closable="item.systemBoolClose"></el-tab-pane>
            </el-tabs>
          </el-col>
        </el-row>
        <el-row class="local-home-bread"><el-col :span="24"><el-breadcrumb separator-class="el-icon-arrow-right"><el-breadcrumb-item>{{bread.first.systemMenuName}}</el-breadcrumb-item><el-breadcrumb-item>{{bread.second.systemMenuName}}</el-breadcrumb-item><el-breadcrumb-item><span  class="wuhan-color-primary">{{bread.third.systemMenuName}}</span></el-breadcrumb-item></el-breadcrumb></el-col></el-row>
      </div>
      <!-- 子页面路由入口 -->
      <div class="local-home-main"><keep-alive><router-view/></keep-alive></div>
      <div class="local-home-foot">版权所有 武汉正维电子技术有限公司</div>
  </div>
</template>

<script>
import WuhanHomeHeader from '@/components/WuhanHomeHeader'
var log = '【home页面】'
var activeFirst = 'summary'
var tabKey = 'tabstr'

export default {
  name: 'home',
  components: {WuhanHomeHeader},
  data () {
    return {
      activeMenu: null,
      activeTabs: null,
      user: {},
      menu: {},
      menuList: [],
      tabList: [],
      bread: {first: {}, second: {}, third: {}},
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    this.user = this.whu.func.getUser()
    this.menu = this.user.menu
    // 提前构造菜单数据集合
    this.menuList.push(this.menu)
    let branchs = this.menu.childs
    this.menuList = this.menuList.concat(branchs)
    branchs.forEach(e => {
      this.menuList = this.menuList.concat(e.childs)
    })
    // 确定目标子页面
    let target = null
    let tabs = this.whu.func.getSession(tabKey)
    if (tabs) {
      // 缓存中获取选项卡集合和目标子页面
      this.tabList = tabs
      this.tabList.forEach(e => {
        if (e.activeFlag) {
          target = e
        }
      })
    } else {
      // 无缓存，就用默认目标子页面
      target = this.getMenuItem(activeFirst)
      target.activeFlag = true
      // 基于默认目标子页面构造选项卡集合
      this.tabList.push(target)
    }
    this.activeMenu = target.systemUrlThird
    this.activeTabs = target.systemUrlThird
    // 基于目标子页面构造面包屑对象
    this.setBreadItem(target.systemUrlFirst, target.systemUrlSecond, target.systemUrlThird)
    console.info(log, 'mounted')
  },
  methods: {
    getMenuItem (url) {
      let res = null
      this.menuList.forEach(e => {
        if (url === e.systemMenuUrl) {
          res = e
        }
      })
      return res
    },
    setBreadItem (val1, val2, val3) {
      this.bread = {first: this.getMenuItem(val1), second: this.getMenuItem(val2), third: this.getMenuItem(val3)}
    },
    menuMethodSelect (key, keyPath) {
      this.activeTabs = key
      console.info(log, key, keyPath)
      // 更新选项卡集合
      this.tabMethodUpdate(key)
      // 更新面包屑对象
      this.setBreadItem(keyPath[0], keyPath[1], keyPath[2])
    },
    tabMethodClick (tab, event) {
      // 更换当前导航菜单目标，并路由到目标的子页面
      this.activeMenu = tab.name
      let tar = this.getMenuItem(tab.name)
      // 更新面包屑对象
      this.setBreadItem(tar.systemUrlFirst, tar.systemUrlSecond, tar.systemUrlThird)
      this.$router.push({name: tar.systemMenuUrl})
      this.tabList.forEach(e => {
        if (tab.name === e.systemMenuUrl) {
          e.activeFlag = true
        } else {
          e.activeFlag = false
        }
      })
      // 缓存变更后的选项卡集合
      this.whu.func.setSession(tabKey, this.tabList)
      console.info(log, tab, event)
    },
    tabMethodUpdate (key) {
      // 如果选项卡集合拥有目标key，选中它
      let notar = true
      this.tabList.forEach(e => {
        if (key === e.systemMenuUrl) {
          e.activeFlag = true
          notar = false
        } else {
          e.activeFlag = false
        }
      })
      if (notar) {
        // 如果选项卡集合没有目标key，先增加它，再选中它
        let target = this.getMenuItem(key)
        target.activeFlag = true
        this.tabList.push(target)
      }
      this.activeTabs = key
      // 缓存变更后的选项卡集合
      this.whu.func.setSession(tabKey, this.tabList)
    },
    tabMethodRemove (name) {
      // 选项卡关闭，先将所有成员的activeFlag刷成false，再确定被关闭者的索引
      let targetIndex = -1
      for (let i = 0; i < this.tabList.length; i++) {
        const e = this.tabList[i]
        e.activeFlag = false
        if (e.systemMenuUrl === name) {
          targetIndex = i
        }
      }
      // 删除被关闭者
      this.tabList.splice(targetIndex, 1)
      // 判断被关闭者是否是当前页面
      if (name === this.activeTabs) {
        // 如果是，则选中被关闭者前面的一个成员为选中者
        let curr = this.tabList[targetIndex - 1]
        curr.activeFlag = true
        this.activeMenu = curr.systemUrlThird
        this.activeTabs = curr.systemUrlThird
        this.setBreadItem(curr.systemUrlFirst, curr.systemUrlSecond, curr.systemUrlThird)
        this.$router.push({name: curr.systemMenuUrl})
      } else {
        console.info(log, '选项卡关闭', '被关闭者不是当前页面')
      }
      // 缓存变更后的选项卡集合
      this.whu.func.setSession(tabKey, this.tabList)
      console.info(log, '选项卡关闭', name)
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
.local-home-center {
  height: $headerHeight; width: $headerWidth; display: flex; align-items: center; justify-content: center; background: $wuhanPrimaryColor; color: $headerFontColor; font-size: 24px;
}
.local-home-right {
  height: $headerHeight; width: $headerWidth; display: flex; align-items: center; justify-content: flex-end; background: $wuhanPrimaryColor; color: $headerFontColor; font-size: 14px;
}
.local-home-bread { margin: 0px 0px 10px 10px; }
.local-home-head1 { height: 15vh; }
.local-home-main1 { height: 80vh;}
.local-home-foot { height: 40px; display: flex; align-items: center; justify-content: center; background: $wuhanPrimaryColor; color: $headerFontColor; font-size: 12px; }
</style>
