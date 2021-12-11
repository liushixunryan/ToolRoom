package com.xql.basic.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.LogUtils;
import com.qweather.sdk.view.HeConfig;
import com.xql.arouter.ARouter;

import org.litepal.LitePal;

import java.io.File;

/**
 * @Package: com.xql.basic
 * @ClassName: BaseApplication
 * @CreateDate: 2021/6/4 13:13
 * @UpdateUser: RyanLiu
 */

public class BaseApplication extends Application {
    private static Context context;

    @SuppressLint("Range")
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 设置全局Log
         */
        LogUtils.Config config = LogUtils.getConfig();  //获取 log 配置
        config.setLogSwitch(true);  //获取log总开关
        config.setConsoleSwitch(true);  // 设置 log 控制台开关
        config.setGlobalTag("gulu"); //设置全局tag
        config.setLogHeadSwitch(true);  //设置头部信息开关
        config.setLog2FileSwitch(true); //设置设置文件开关
        File file = new File("/sdcard/1112");
        config.setDir(file);  //设置文件存储目录
        config.setFilePrefix("ryanliu");  //设置文件前缀
        config.setBorderSwitch(true);  //设置边框开关
        //config.setConsoleFilter(); //设置单一tag开关
        //config.setConsoleFilter();  //设置控制台过滤器
        //config.setFileFilter();  //设置文件过滤器
        //config.setStackDeep();  //设置栈深度
        //config.setStackOffset();  //栈偏移
        config.setSaveDays(3);  //设置可保留天数
        //config.addFormatter();  //设置增加格式化器
        //config.setOnConsoleOutputListener();  //设置控制台输出监听器
        //config.setOnFileOutputListener();  //设置文件输出监听器
        //config.addFileExtraHead();  //增加 log 文件头部
        LogUtils.getLogFiles();  //获取所有日志
        //LogUtils.file(file);  //log 到文件
        LogUtils.getCurrentLogFilePath();  //获取当前日志文件路径
        LogUtils.e(LogUtils.getCurrentLogFilePath());


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
    public static Context getContextObject() {
        return context;
    }
}