package haoruyi.com

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
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
        newUser.setOnClickListener(){startActivity<RegisterActivity>()}
        login.setOnClickListener{login()}
        password.setOnEditorActionListener(object :TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                login()
                return true
            }

        })
    }

    fun login(){
        //隐藏软键盘
        hideSoftKeyBoard()
        if(hasWriteExternalStoragePermission()){
            val userNameString = userName.text.trim().toString()
            val passwordString = password.text.trim().toString()
            presenter.Login(userNameString,passwordString)
        }else applyWriteExternalStoragePermission()

    }

    private fun applyWriteExternalStoragePermission() {

        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,permissions,0)
    }


    //检查是否有写磁盘的权限
    private fun hasWriteExternalStoragePermission(): Boolean {

        val result = ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //用户同意权限，开始登录
            login()
        }else{
            toast(R.string.permission_denied)
        }

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