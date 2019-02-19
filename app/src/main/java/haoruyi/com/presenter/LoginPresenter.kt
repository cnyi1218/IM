package haoruyi.com.presenter

import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import haoruyi.com.adapter.EMCallBackAdapter
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
        EMClient.getInstance().login(userName,password,object : EMCallBackAdapter() {

            //在子线程回调
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                //在主线程通知View层
                uiThread { view.onLoggedInSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onLoggedInFailed() }
            }
        })

    }

}