package com.xql.constellation.bean;

import java.io.Serializable;

/**
 * @ClassName: TodayBean
 * @Description: java类作用描述
 * @CreateDate: 2021/6/8 13:34
 * @UpdateUser: RyanLiu
 */

public class TodayBean implements Serializable {

    private String date;
    private String name;
    private String QFriend;
    private String color;
    private String datetime;
    private String health;
    private String love;
    private String work;
    private String money;
    private String number;
    private String summary;
    private String all;
    private String resultcode;
    private String error_code;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQFriend() {
        return QFriend;
    }

    public void setQFriend(String QFriend) {
        this.QFriend = QFriend;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
