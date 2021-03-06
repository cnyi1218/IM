package haoruyi.com.app

import android.app.Application
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import haoruyi.com.BuildConfig

class IMApplication : Application(){
    override fun onCreate() {
        super.onCreate()


//初始化
        EMClient.getInstance().init(applicationContext, EMOptions());
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);
    }
}