package haoruyi.com.contract

interface LoginContract{

    interface Presenter:BasePresenter
    {
        fun Login(userName:String,password:String)
    }

    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedInSuccess()
        fun onLoggedInFailed()
    }
}