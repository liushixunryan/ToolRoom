package com.xql.common;

public class GlobalConfig {
    private static GlobalConfig globalConfig = GlobalConfig.getGlobalConfig();

    //星座运势key
    String constellation_key = "243cfbed804a8bd6904fb059871b2a41";

    //路径地址
    final String prefix = "http://web.juhe.cn:8080/constellation/getAll?type=";


    //单例
    public static GlobalConfig getGlobalConfig() {
        if (null == globalConfig) {
            globalConfig = new GlobalConfig();
        }
        return globalConfig;
    }

    //今日
    public String today = prefix + "today&key=" + constellation_key + "&consName=";
    //明日
    public String tomorrow = prefix + "tomorrow&key=" + constellation_key + "&consName=";
    //本周
    public String week = prefix + "week&key=" + constellation_key + "&consName=";
    //本月
    public String month = prefix + "month&key=" + constellation_key + "&consName=";
    //今年
    public String year = prefix + "year&key=" + constellation_key + "&consName=";
    /**
     * App相关配置
     */
    //AppType
    public static String AppType = "2";
    //SystemID
    public static String SystemID = "2";
    //LoginKey
    public static String LoginKey = "9527";
    //Type
    public static String Type = "1";
}
