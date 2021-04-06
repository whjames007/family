<template>
  <div id="wmenu">
    <el-table :data="pages" stripe highlight-current-row>
      <template>
        <el-table-column prop="length" label="序号" type="index" width="60"/>
        <el-table-column label="创建时间" show-overflow-tooltip><template slot-scope="scope">{{methodDateFormat(scope.row.firstCreateDate)}}</template></el-table-column>
        <el-table-column prop="systemMenuId" label="菜单ID" show-overflow-tooltip/>
        <el-table-column prop="systemMenuName" label="菜单名称" show-overflow-tooltip/>
        <el-table-column prop="systemMenuType" label="菜单类型" show-overflow-tooltip/>
        <el-table-column prop="systemMenuIcon" label="菜单图标" show-overflow-tooltip/>
        <el-table-column prop="systemMenuUrl" label="菜单路由" show-overflow-tooltip/>
        <el-table-column prop="systemParentId" label="上级菜单ID" show-overflow-tooltip/>
        <el-table-column prop="systemMenuClose" label="菜单可关闭" show-overflow-tooltip/>
        <el-table-column prop="systemMenuDesc" label="菜单描述" show-overflow-tooltip/>
        <el-table-column label="数据状态" show-overflow-tooltip><template slot-scope="scope"><span :class="scope.row.dataStatusColor">{{scope.row.dataStatusName}}</span></template></el-table-column>
        <el-table-column label="更新时间" show-overflow-tooltip><template slot-scope="scope">{{methodDateFormat(scope.row.lastUpdateDate)}}</template></el-table-column>
      </template>
    </el-table>
  </div>
</template>

<script>
var log = '【菜单管理子页面】'
export default {
  name: 'wmenu',
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
    let user = this.whu.func.getUser()
    this.param.pageNum = user.pageNum
    this.param.pageSize = user.pageSize
    this.tableMethodPage()
    console.info(log, 'mounted')
  },
  methods: {
    methodDateFormat (val) {
      return this.whu.dfmt.dts(val)
    },
    tableMethodPage () {
      this.$axios.post(this.whu.cont.url.menuList, this.param)
        .then((res) => {
          console.info(log, res)
          const {code, list} = res.data
          if (this.whu.func.respSuccess(code)) {
            this.whu.func.transDataStatus(list)
            this.pages = list
          } else {
            this.whu.func.respExcep(this, code)
          }
        })
    },
    aaa () {
      console.info(log, 'aaa')
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
