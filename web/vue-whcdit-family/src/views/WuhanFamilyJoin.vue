<template>
  <div id="join">
    <!-- 查询条件 -->
    <div>
      <el-form :model="param">
        <el-row>
          <el-col :span="8">&nbsp;</el-col>
          <el-col :span="8"><el-form-item><el-input :placeholder="placeholder" v-model="search"></el-input></el-form-item></el-col>
          <el-col :span="8"><el-form-item><el-button type="primary" icon="el-icon-search" @click="tableMethodSearch">查询</el-button></el-form-item></el-col>
        </el-row>
      </el-form>
    </div>
    <div class="local-family-group">
      <template v-for="item in pages">
        <div class="local-family-join" :key="item.familyInfoId" @click="familyMethodJoin(item)">
          <div style="color: #FFD700; font-weight: 700;">ID：{{item.familyInfoId}}</div>
          <div>家族：{{item.familyInfoName}}</div>
          <div>创立：{{whu.dfmt.dts(item.firstCreateDate)}}</div>
          <div>族长：{{item.leaderUserName}}</div>
          <div>简介：{{item.familyInfoDesc}}</div>
        </div>
      </template>
    </div>
    <div class="wuhan-div-center">
      <el-pagination background layout="prev, pager, next" :total="totalRows" @current-change="tableMethodCurr"></el-pagination>
    </div>
  </div>
</template>

<script>
var log = '【家族加入子页面】'
export default {
  name: 'join',
  data () {
    return {
      placeholder: '支持家族名称、族长名称的模糊查询',
      search: null,
      param: {pageNum: null, pageSize: null},
      pages: [],
      totalRows: 0, // 总行数
      pageArray: this.whu.cont.pageArray,
      familys: [],
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    this.whu.func.mountedInit(this)
    console.info(log, 'mounted')
  },
  methods: {
    tableMethodPage () {
      this.param.familyInfoName = this.search
      this.param.leaderUserName = this.search
      this.$axios.post(this.whu.cont.url.familySearch, this.param).then((res) => {
        if (this.whu.func.respSuccess(res.data.code)) {
          this.familys = res.data.list
          this.pages = res.data.page.list
          this.totalRows = res.data.page.total
        } else {
          this.whu.func.respExcep(this, res.data.code)
        }
      })
    },
    tableMethodSearch () {
      if (this.search) { this.param.pageNum = 1 }
      this.tableMethodPage()
    },
    familyMethodJoin (val) {
      console.info(log, `【familyMethodJoin】`, val.familyInfoName)
    },
    tableMethodCurr (val) {
      this.param.pageNum = val
      this.tableMethodPage()
      console.info(log, `【tableMethodCurr函数】`, `【当前第 ${val} 页】`)
    },
    tableMethodSize (val) {
      this.param.pageSize = val
      this.tableMethodPage()
      console.info(log, `【tableMethodSize函数】`, `【每页共 ${val} 条】`)
    },
    aaa () {
      console.info(log, 'aaa')
    }
  }
}
</script>

<style lang="scss" scoped>
.local-family-group {
  width: 100%;
  //  background: #098;
  display: flex; justify-content: center; flex-wrap: wrap;
}
.local-family-join {
  height: 200px; width: 16%; background: $wuhanPrimaryColor;
  color: #FFFFFF;
  // text-align: center;
  border: 2px solid #FFD700;
  margin:10px 10px 10px 10px;
  padding: 10px 0px 0px 10px;
}
</style>
