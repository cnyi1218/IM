package haoruyi.com.presenter

import haoruyi.com.contract.RegisterContract
import haoruyi.com.extentions.isValidPassword
import haoruyi.com.extentions.isValidUserName

class RegisterPresenter(val view:RegisterContract.View):RegisterContract.Presenter{
    override fun register(userName: String, password: String, confirmPassword: String) {
        if (userName.isValidUserName()){
            //检查密码
            if (password.isValidPassword())
            {
                //检查确认密码
                if (password.equals(confirmPassword)){
                    //密码和确认密码一致
                    view.onStartRegister()
                    //开始注册




                }else view.onConfirmPasswordError()
            }else view.onPasswordError()
        }else view.onUserNameError()
    }
}