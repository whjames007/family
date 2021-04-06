const urlRouter = '/fmyapi'
const cont = {
  web: 'WEB',
  manual: 'MANUAL',
  info: 'INFO',
  waiting: 'WAITING',
  success: 'SUCCESS',
  failure: 'FAILURE',
  sender: 'SENDER',
  snull: 'SNULL',
  valid: 'VALID',
  enable: 'ENABLE',
  disable: 'DISABLE',
  yes: 'YES',
  no: 'NO',
  pageArray: [5, 10, 20, 50, 100],
  url: {
    // 账号类后台地址
    userLogin: urlRouter + '/user/login',
    userReg: urlRouter + '/user/reg',
    userAdd: urlRouter + '/user/add',
    userDel: urlRouter + '/user/del',
    userEdit: urlRouter + '/user/edit',
    userPage: urlRouter + '/user/page',
    userMenus: urlRouter + '/user/menus',
    rolePage: urlRouter + '/role/page',
    menuList: urlRouter + '/menu/list',
    // 家族类后台地址
    familyAdd: urlRouter + '/family/add',
    familyDel: urlRouter + '/family/del',
    familyEdit: urlRouter + '/family/edit',
    familyPage: urlRouter + '/family/page',
    familySearch: urlRouter + '/family/search',
    aaa: null
  },
  placeholder: '支持模糊查询',
  labelSuffix: '：',
  createSuccess: '您的新增操作已执行成功！',
  disableQuestion: '您确定将要执行禁用操作吗？',
  disableSuccess: '您的禁用操作已执行成功！',
  deleteQuestion: '您确定将要执行删除操作吗？',
  deleteSuccess: '您的删除操作已执行成功！',
  updateSuccess: '您的更新操作已执行成功！',
  replaceQuestion: '您确定将要执行替换操作吗？',
  replaceSuccess: '您的替换操作已执行成功！',
  userInitQuestion: '您确定将要执行初始化密码的操作吗？操作后该账号的密码将变更为123456这六位数字密码。',
  userInitSuccess: '您的用户账号初始化操作已执行成功！',
  header: {
    'Authorization': localStorage.getItem('token')
  }
}
export {
  cont
}
