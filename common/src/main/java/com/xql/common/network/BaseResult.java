package com.xql.common.network;

/**
 * @ClassName: BaseResult
 * @Description: 第一层bean
 * @CreateDate: 2021/6/7 9:22
 * @UpdateUser: RyanLiu
 */

public class BaseResult<T> {

    private Boolean Result; // 返回的Result
    private T Data; // 具体的数据结果
    private String Notice; // Notice可用来返回接口的说明

    public Boolean getResult() {
        return Result;
    }

    public void setResult(Boolean result) {
        Result = result;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }
}
