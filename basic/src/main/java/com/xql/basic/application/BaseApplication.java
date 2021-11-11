package com.xql.basic.application;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.qweather.sdk.view.HeConfig;
import com.xql.arouter.ARouter;

import org.litepal.LitePal;

/**
 * @Package: com.xql.basic
 * @ClassName: BaseApplication
 * @CreateDate: 2021/6/4 13:13
 * @UpdateUser: RyanLiu
 */

public class BaseApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 设置全局context
         */
        context = getApplicationContext();
        /**
         * 初始化LitePal数据库
         */
        LitePal.initialize(this);

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
    //返回
    public static Context getContextObject(){
        return context;
    }
}