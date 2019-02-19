package haoruyi.com

import android.view.KeyEvent
import android.widget.TextView
import haoruyi.com.contract.LoginContract
import haoruyi.com.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity :BaseActivity(),LoginContract.View {

    val presenter = LoginPresenter(this)

    override fun init() {
        super.init()
        login.setOnClickListener{login()}
        password.setOnEditorActionListener(object :TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                login()
                return true
            }

        })
    }

    fun login(){
        val userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()
        presenter.Login(userNameString,passwordString)
    }

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