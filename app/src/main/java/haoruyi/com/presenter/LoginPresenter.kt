package haoruyi.com.presenter

import haoruyi.com.contract.LoginContract
import haoruyi.com.extentions.isValidPassword
import haoruyi.com.extentions.isValidUserName

class LoginPresenter(val view:LoginContract.View):LoginContract.Presenter{
    override fun Login(userName: String, password: String) {
        if (userName.isValidUserName())
        {
            //用户名合法，继续校验密码
            if (password.isValidPassword()){
                //密码合法，开始登录
                view.onStartLogin()
                loginEaseMob(userName,password)
            }else view.onPasswordError()

        }else view.onUserNameError()
    }

    private fun loginEaseMob(userName: String, password: String) {

    }

}