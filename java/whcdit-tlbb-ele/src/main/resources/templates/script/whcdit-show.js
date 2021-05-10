var componentShow = {
    template: $("#templateShow").html(),
	data: function(){
	    return{
			logs: [],
			what: null
	    }
	},
	mounted () {
		let myself = this
		// 定义将被后台调用的前端方法
		window.jsshowMethodPush = function (val) {
			myself.showMethodPush(val)
			// return '湖北省' + val
		}
	},
	methods: {
		showMethodGrant () {
			let res = window.BRIDGE.grant('ENABLE')
			whcditMethods.response(res, this, '【授权成功】')
			this.showMethodPush('【授权成功】【请同时按下Ctrl+Shift+R三个按键进行启动】')
		},
		showMethodPush (val) {
			let item = {msg: val}
			this.logs.push(item)
			let last = this.logs.length - 1
			this.$refs.logsTable.setCurrentRow(this.logs[last]) // 表格选中最后一行
			document.getElementById('divShowFoot').scrollIntoView() // 滚动后，底部定位
			// this.what = 'aaa11'
		}
	}
}
