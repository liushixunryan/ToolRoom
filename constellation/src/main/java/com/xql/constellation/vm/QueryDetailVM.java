package com.xql.constellation.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.GsonUtils;
import com.xql.basic.viewmodel.BaseViewModel;
import com.xql.common.GlobalConfig;
import com.xql.common.okHttpUtil;
import com.xql.constellation.bean.PairBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @ClassName: QueryDetailVM
 * @Description: java类作用描述
 * @CreateDate: 2021/6/9 14:23
 * @UpdateUser: RyanLiu
 */

public class QueryDetailVM extends BaseViewModel {
    /**
     * 星座配对网络请求
     */
    public MutableLiveData<PairBean> getPair(String men, String women) {
        final MutableLiveData<PairBean> liveData = new MutableLiveData<>();
        final PairBean[] pairBean = {null};
        okHttpUtil.getOkhttpRequest(GlobalConfig.getGlobalConfig().query + men + "&women=" + women, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("sansuiban", "onFailure: " + e );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String query = response.body().string();
                pairBean[0] = GsonUtils.fromJson(query,PairBean.class);
                liveData.postValue(pairBean[0]);
            }
        });

        return liveData;
    }
}
