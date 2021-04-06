<template>
  <div id="todo">
    totototdododod
  </div>
</template>

<script>
var log = '【aaa子页面】'
export default {
  name: 'todo',
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
