package com.xql.basic.application;

import android.app.Application;

import androidx.multidex.MultiDex;
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
         *MultiDex
         */
        // Dex文件超出限制
        MultiDex.install(this);
    }
}