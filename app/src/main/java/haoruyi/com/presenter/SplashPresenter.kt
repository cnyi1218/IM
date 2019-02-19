package haoruyi.com.presenter

import com.hyphenate.chat.EMClient
import haoruyi.com.contract.SplashContract

class SplashPresenter(val view:SplashContract.View) :SplashContract.Presenter{
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    //判断是否登录到环信的服务器
    private fun isLoggedIn(): Boolean = EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore
}