package com.xql.idiomsolitaire;

import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.idiomsolitaire.databinding.ActivityIdiomBinding;
import com.xql.idiomsolitaire.vm.IdiomVM;

import java.io.File;

@BindPath(key = "idiom/idiom")
public class IdiomActivity extends BaseActivity<ActivityIdiomBinding, IdiomVM> {

    @Override
    protected int layoutId() {
        return R.layout.activity_idiom;
    }

    @Override
    protected void initView() {
//        LogUtils.Config config = LogUtils.getConfig();  //获取 log 配置
//        config.setLogSwitch(true);  //获取log总开关
//        config.setConsoleSwitch(true);  // 设置 log 控制台开关
//        config.setGlobalTag("sansuiban"); //设置全局tag
//        config.setLogHeadSwitch(true);  //设置头部信息开关
//        config.setLog2FileSwitch(true); //设置设置文件开关
//        File file = new File("/sdcard/1112");
//        config.setDir(file);  //设置文件存储目录
//        config.setFilePrefix("文件前缀log");  //设置文件前缀
//        config.setBorderSwitch(true);  //设置边框开关
//        //config.setConsoleFilter(); //设置单一tag开关
//        //config.setConsoleFilter();  //设置控制台过滤器
//        //config.setFileFilter();  //设置文件过滤器
//        //config.setStackDeep();  //设置栈深度
//        //config.setStackOffset();  //栈偏移
//        config.setSaveDays(3);  //设置可保留天数
//        //config.addFormatter();  //设置增加格式化器
//        //config.setOnConsoleOutputListener();  //设置控制台输出监听器
//        //config.setOnFileOutputListener();  //设置文件输出监听器
//        //config.addFileExtraHead("log头部文件");  //增加 log 文件头部

    }

    @Override
    protected void initData() {
        String a = "00 07 FE 0C 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0F F0";
        String b = a.substring(6, 8).trim().replace(" ", "").replace(":", "");
        String c = a.substring(9, 12).trim().replace(" ", "").replace(":", "");
        LogUtils.getLogFiles();
        LogUtils.e(complemwnt(b + c + "dasdad"));
    }

    /**
     * 16进制补码转10进制数
     *
     * @param str
     * @return
     */
    public static int complemwnt(String str) {
        int result = 0;
        String binaryString = Integer.toBinaryString(Integer.valueOf(str, 16));
        while (binaryString.length() < 16) {
            binaryString = "0" + binaryString;
        }
        String binary = binaryString.substring(0, 1);//取第一位判断正负
        if ("0".equals(binary)) {
            result = Integer.valueOf(binaryString, 2);
        } else {
            String[] split = binaryString.split("");
            StringBuilder builder = new StringBuilder();
            for (String s : split) {
                if ("0".equals(s)) {
                    builder.append("1");
                } else {
                    builder.append("0");
                }
            }
            // 调用Integer.valueOf(value, 2) 将二进制转为十进制.
            result = Integer.valueOf(builder.toString(), 2);
            // 先取负数在减1
            result = (0 - result) - 1;
        }
        return result;

    }

    public void qqqqq(View view) {
        LogUtils.e("点击按钮");
        LogUtils.e(complemwnt(""));
    }
}