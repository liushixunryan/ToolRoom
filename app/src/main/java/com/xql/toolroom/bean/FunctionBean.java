package com.xql.toolroom.bean;

import java.io.Serializable;

/**
 * @ClassName: FunctionBean
 * @Description: 功能类bean
 * @CreateDate: 2021/6/7 9:29
 * @UpdateUser: RyanLiu
 */

public class FunctionBean implements Serializable {
    private Integer function_icon;
    private String function_name;
    private int function_ID;

    public int getFunction_ID() {
        return function_ID;
    }

    public void setFunction_ID(int function_ID) {
        this.function_ID = function_ID;
    }

    public Integer getFunction_icon() {
        return function_icon;
    }

    public void setFunction_icon(Integer function_icon) {
        this.function_icon = function_icon;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }
}

