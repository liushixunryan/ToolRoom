package com.xql.basic.application;

import android.app.Application;

import androidx.multidex.MultiDex;

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
        /**
         * Arouter
         */
        //arouter注册
        ARouter.getInstance().init(this);
        /**
         * 和风天气
         */
        HeConfig.init("HE2107070852471616", "d54750558dd14ba5ad40f47c4058620c");
        //切换至开发版服务
        HeConfig.switchToDevService();

        /**
         *MultiDex
         */
        // Dex文件超出限制
        MultiDex.install(this);
    }
}