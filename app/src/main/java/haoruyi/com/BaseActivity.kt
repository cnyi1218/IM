package haoruyi.com

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    open fun init() {
        //初始化一些公共的功能，比如摇一摇，子类也可以覆写该方法，完成子类的初始化
    }

    //子类必须实现该方法返回一个布局资源的id
   abstract fun getLayoutResId(): Int
}