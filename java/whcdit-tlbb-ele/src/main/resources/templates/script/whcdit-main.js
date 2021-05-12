
var componentGame = {
    template: $("#templateGame").html(),
	data: function(){
	    return{
			parameter: {},
			what: '暂无内容！'
	    }
	},
	mounted () {
		let json = window.BRIDGE.readParameter()
		this.parameter = JSON.parse(json)
	},
	methods: {
		gameMethodTest() {
	    }
	}
}

var componentGlobal = {
    template: $("#templateGlobal").html(),
	data: function(){
	    return{
			parameter: {},
			what: null
	    }
	},
	mounted () {
		let json = window.BRIDGE.readParameter()
		this.parameter = JSON.parse(json)
	},
	methods: {
		globMethodSave() {
	    	let json = JSON.stringify(this.parameter)
			let res = window.BRIDGE.saveParameter(json)
			whcditMethods.response(res, this, '【保存设置成功】')
	    }
	}
}
	
var componentHelp = {
    template: $("#templateHelp").html(),
	data: function(){
	    return{
	    }
	},
	mounted () {
	},
	methods: {
	}
}

// {name: 'show', path: '/',component: {template: $("#showt").html()}}
var whcRoutes = [
{name: 'show', path: '/',component: componentShow},
{name: 'game', path: '/game',component: componentGame},
{name: 'global', path: '/global',component: componentGlobal},
{name: 'help', path: '/help',component: componentHelp}]

var router = new VueRouter({routes: whcRoutes})
var whcditVue = new Vue({
    router,
    el: '#app',
    data: function(){
        return{
            activeName: 'show'
        }
    },
	mounted () {
	},
	methods: {
		tabMethodClick (tab, event) { this.$router.push({name: tab.name}) }
	}
})

var respPrefix = 'WUHAN_RESPONSE_'

var whcditMethods = {
	response (json, myself, msg) {
		var obj = JSON.parse(json)
		if (!msg) { msg = '您好，操作成功了！' }
		myself.what = obj
		if (obj.code == respPrefix + '10000') { myself.$message.success(msg) } else { myself.$message.error(obj.code) }
	},
	aaa () {}
}
