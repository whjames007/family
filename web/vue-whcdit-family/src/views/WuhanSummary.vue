<template>
  <div id="summary" class="wuhan-div-center">
    <div style="width: 800px;">
      <div class="wuhan-div-word">武汉正维电子技术有限公司成立于2003年，是一家扎根于通信行业的国家级高新技术企业，现位于武汉市东湖开发区关东科技园。公司专注于无线通信产品的研发、生产和销售，产品广泛应用于3G、4G、5G无线通信基站及DAS系统，数字集群通信系统及行业专用无线通信系统；同时公司低功耗物联网方案，广泛用于智能交通、智能电网、智慧能源、智慧小区、智慧超市等领域，现有客户遍布国内外 。</div>
      <video width="800" controls><source src="static/video/bmwv001.mp4" type="video/mp4"></video>
      <div class="wuhan-div-word">地址：中国湖北武汉关东科技工业园3号区2号楼</div>
      <div class="wuhan-div-word">邮编：430074</div>
      <div class="wuhan-div-word">前台总机：+86-27-59716860</div>
    </div>
  </div>
</template>

<script>
var log = '【summary子页面】'
export default {
  name: 'wsummary',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    console.info(log, 'mounted')
  },
  methods: {
    aaa () {
      console.info(log, 'aaa')
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
