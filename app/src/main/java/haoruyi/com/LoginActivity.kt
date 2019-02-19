package haoruyi.com

import haoruyi.com.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity :BaseActivity(),LoginContract.View {
    override fun onUserNameError() {
        userName.setError(getString(R.string.user_name_error))
    }

    override fun onPasswordError() {
        password.error = (getString((R.string.password_error)))
    }

    override fun onStartLogin() {
       //弹出进度条
        showProgress(getString(R.string.logging))
    }

    override fun onLoggedInSuccess() {
        //隐藏进度条
        dissmissProgress()
        //进入主界面
        startActivity<MainActivity>()
        //退出LoginActivity
        finish()
    }

    override fun onLoggedInFailed() {
       //隐藏进度条
        dissmissProgress()
        // 弹出toast
        toast(getString(R.string.login_failed))
    }

    override fun getLayoutResId(): Int =R.layout.activity_login
}