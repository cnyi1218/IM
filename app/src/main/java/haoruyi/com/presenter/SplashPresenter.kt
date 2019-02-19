package haoruyi.com.presenter

import haoruyi.com.contract.SplashContract

class SplashPresenter(val view:SplashContract.View) :SplashContract.Presenter{
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean = false
}