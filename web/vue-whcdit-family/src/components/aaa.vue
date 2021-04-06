<template>
  <div id="aaa">
    aaa
  </div>
</template>

<script>
var log = '【aaa子页面】'
export default {
  name: 'aaa',
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
