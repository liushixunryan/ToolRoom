package com.xql.basic.application;

import android.app.Application;

import com.qweather.sdk.view.HeConfig;
import com.xql.arouter.ARouter;

/**
 * @Package: com.xql.basic
 * @ClassName: BaseApplication
 * @CreateDate: 2021/6/4 13:13
 * @UpdateUser: RyanLiu
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.getInstance().init(this);

        //和风天气注册
        HeConfig.init("HE2106110926101032", "1cd7ff7ac5334b83882a607bf64d5b9f");
        //切换至开发版服务
        HeConfig.switchToDevService();
    }
}