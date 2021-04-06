let gresp = 'WUHAN_RESPONSE_'
let keyUser = 'userstr'
let func = {
  respSuccess (code) {
    if (code === gresp + '10000') {
      return true
    } else {
      return false
    }
  },
  respExcep (my, code) {
    let res = ''
    switch (code) {
      case gresp + '20001':
        res = '系统故障，请与管理员联系！'
        break
      case gresp + '30001':
        res = '账号或密码不正确，请重新输入！'
        break
      case gresp + '30002':
        res = '您输入的用户账号已存在，请重新输入！'
        break
      case gresp + '30003':
        res = '您输入的产品序列号已存在，请重新输入！'
        break
      default:
        res = '未知的异常：' + code
        break
    }
    my.$message.error(res)
  },
  getSession (key) {
    let json = sessionStorage.getItem(key)
    let res = null
    if (json) {
      res = JSON.parse(json)
    } else {
      console.log('未能从会话缓存中获取到信息：' + key)
    }
    return res
  },
  setSession (key, obj) {
    let json = JSON.stringify(obj)
    sessionStorage.setItem(key, json)
  },
  getUser () {
    let json = sessionStorage.getItem(keyUser)
    let res = null
    if (json) {
      res = JSON.parse(json)
    } else {
      console.error('未能从会话缓存中获取到用户信息')
    }
    return res
  },
  setUser (val) {
    let json = JSON.stringify(val)
    sessionStorage.setItem(keyUser, json)
  },
  transDataStatus (list) {
    list.forEach(e => {
      if (e.dataStatus === 'VALID') {
        e.dataStatusName = '有效数据'
        e.dataStatusColor = 'wuhan-color-success'
      } else {
        e.dataStatusName = '无效数据'
        e.dataStatusColor = 'wuhan-color-danger'
      }
    })
  },
  mountedInit (myself) {
    let user = func.getUser()
    myself.user = user
    myself.param.uid = user.uid
    myself.param.pageNum = user.pageNum
    myself.param.pageSize = user.pageSize
    myself.tableMethodPage()
  },
  geweiParamInit (val) {
    // 前后端约定在此初始化通用参数
    let json = sessionStorage.getItem(keyUser)
    let user = JSON.parse(json)
    val.uid = user.userId
    val.companyId = user.companyId
    val.pageNum = 1
    val.pageSize = 10
  },
  geweiClone (val) {
    let str = JSON.stringify(val)
    let json = JSON.parse(str)
    return json
  },
  geweiNotNull (val) {
    let res = true
    if (val === null) {
      res = false
    } else {
      if (val === '') {
        res = false
      } else {
        res = true
      }
    }
    return res
  },
  bbb (val) {
    console.info('【bbb】')
  }
}
export {
  func
}
