package com.xql.common;


import java.nio.channels.Pipe;

public class GlobalConfig {
    private static GlobalConfig globalConfig = GlobalConfig.getGlobalConfig();
    //星座运势地址
    private static final String BASEURL = "http://web.juhe.cn:8080/constellation/";
    //星座运势key
    String constellation_key = "243cfbed804a8bd6904fb059871b2a41";
    //星座配对key
    String query_key = "fdbf50f0fcf93eb225fe9a4a065b3970";
    //星座配对地址
    final String prefix = "http://apis.juhe.cn/xzpd/query?key=" + query_key;

    //单例
    public static GlobalConfig getGlobalConfig() {
        if (null == globalConfig) {
            globalConfig = new GlobalConfig();
        }
        return globalConfig;
    }

    //今日
    public String today = BASEURL + "today&key=" + constellation_key + "&consName=";
    //明日
    public String tomorrow = BASEURL + "tomorrow&key=" + constellation_key + "&consName=";
    //本周
    public String week = BASEURL + "week&key=" + constellation_key + "&consName=";
    //本月
    public String month = BASEURL + "month&key=" + constellation_key + "&consName=";
    //今年
    public String year = BASEURL + "year&key=" + constellation_key + "&consName=";

    //星座配对
    public String query = prefix + "&men=";

    /**
     * App相关配置
     */
    //AppType
    public static String AppType = "2";
}
