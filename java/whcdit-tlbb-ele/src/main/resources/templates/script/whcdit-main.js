var componentShow = {
    template: $("#templateShow").html(),
	data: function(){
	    return{
	    }
	},
	mounted () {
	},
	methods: {
	}
}

var componentGame = {
    template: $("#templateGame").html(),
	data: function(){
	    return{
			mouse: {},
			what: null
	    }
	},
	mounted () {
	},
	methods: {
		formMethodTest() {
	    	let json = JSON.stringify(this.mouse)
this.what = '这次是我真的决定离开，'
			/*let res = window.BRIDGE.saveParameter(json)
			whcditMethods.response(res, this)*/
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
		formMethodSave() {
	    	let json = JSON.stringify(this.parameter)
			let res = window.BRIDGE.saveParameter(json)
			whcditMethods.response(res, this)
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
	response (json, myself) {
		var obj = JSON.parse(json)
		myself.what = obj
		if (obj.code == respPrefix + '10000') {
			myself.$message.success('您好，保存成功！')
		} else {
			myself.$message.error(obj.code)
		}
	},
	aaa () {}
}
