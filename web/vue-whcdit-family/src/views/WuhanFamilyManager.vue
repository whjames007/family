<template>
  <div id="famimgr">
    <!-- 查询条件 -->
    <div>
      <el-form :model="param" label-width="100px" :label-suffix="labelSuffix" size="mini">
        <el-row>
          <el-col :span="8"><el-form-item label="家族族长"><el-input :placeholder="placeholder" v-model="param.leaderUserName"></el-input></el-form-item></el-col>
          <el-col :span="8"><el-form-item label="家族名称"><el-input :placeholder="placeholder" v-model="param.familyInfoName"></el-input></el-form-item></el-col>
          <el-col :span="8" class="wuhan-col-search"><el-form-item><el-button type="primary" icon="el-icon-search" @click="tableMethodSearch">查询</el-button><el-button type="warning" icon="el-icon-refresh-left" @click="tableMethodReset">重置</el-button></el-form-item></el-col>
        </el-row>
      </el-form>
    </div>
    <!-- 分页表格 -->
    <div class="wuhan-table">
      <el-table :data="pages" stripe border highlight-current-row>
        <template>
          <el-table-column prop="length" label="序号" type="index" width="60"/>
          <el-table-column prop="firstCreateName" label="家族创始人" width="100"/>
          <el-table-column label="家族创立时间" width="160"><template slot-scope="scope">{{whu.dfmt.dts(scope.row.firstCreateDate)}}</template></el-table-column>
          <el-table-column prop="familyInfoId" label="家族ID" width="80"/>
          <el-table-column prop="familyInfoName" label="家族名称" width="160" show-overflow-tooltip/>
          <el-table-column prop="leaderUserName" label="家族现任族长" width="120"/>
          <el-table-column label="族长上任时间" width="160"><template slot-scope="scope">{{whu.dfmt.dts(scope.row.leaderOfficeTime)}}</template></el-table-column>
          <el-table-column prop="familyInfoDesc" label="家族简介" show-overflow-tooltip/>
          <el-table-column prop="familyAccountAmount" label="家族资金" width="100"/>
          <el-table-column label="更新时间" width="160"><template slot-scope="scope">{{whu.dfmt.dts(scope.row.lastUpdateDate)}}</template></el-table-column>
          <el-table-column label="操作" width="160"><template slot-scope="scope">
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
var log = '【家族管理子页面】'
export default {
  name: 'famimgr',
  data () {
    return {
      user: {},
      placeholder: this.whu.cont.placeholder,
      labelSuffix: this.whu.cont.labelSuffix,
      param: {pageNum: null, pageSize: null, leaderUserName: null, familyInfoName: null, familyInfoId: null},
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
    tableMethodPage () {
      this.$axios.post(this.whu.cont.url.familyPage, this.param).then((res) => {
        if (this.whu.func.respSuccess(res.data.code)) {
          this.pages = res.data.page.list
          this.totalRows = res.data.page.total
        } else {
          this.whu.func.respExcep(this, res.data.code)
        }
      })
    },
    tableMethodSearch () {
      if (this.param.leaderUserName || this.param.familyInfoName) { this.param.pageNum = 1 }
      this.tableMethodPage()
    },
    tableMethodReset () {
      this.param.leaderUserName = null
      this.param.familyInfoName = null
      this.tableMethodPage()
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

</style>
