package com.xql.toolroom.vm;

import androidx.lifecycle.MutableLiveData;

import com.xql.basic.viewmodel.BaseViewModel;
import com.xql.common.LiveDataBus;
/**
 * @ClassName: MainVM
 * @Description: MainActivity的MainVM
 * @CreateDate: 2021/6/4 14:41
 * @UpdateUser: RyanLiu
 */

public class MainVM extends BaseViewModel {

//
//    public MutableLiveData<FunctionBean> getFunction() {
//        mFunctionBean = new ArrayList<>();
//
//        MutableLiveData<List<FunctionBean>> liveData = new MutableLiveData<>();
//
//        liveData.postValue(mFunctionBean);
//        return null;
//    }

    public MutableLiveData<String> getString() {
        final LiveDataBus.BusMutableLiveData<String> liveData = LiveDataBus.getInstance().with("iswuliu", String.class);
        liveData.postValue("111");
        return liveData;
    }
}
