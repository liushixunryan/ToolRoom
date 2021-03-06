package com.xql.toolroom;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.xql.annotation.BindPath;
import com.xql.arouter.ARouter;
import com.xql.basic.activity.BaseActivity;
import com.xql.toolroom.adapter.FunctionAdapter;
import com.xql.toolroom.bean.FunctionBean;
import com.xql.toolroom.databinding.ActivityMainBinding;
import com.xql.toolroom.interfaces.IOnFunctionItemListener;
import com.xql.toolroom.vm.MainVM;

import java.util.ArrayList;
import java.util.List;


@BindPath(key = "main/main")
public class MainActivity extends BaseActivity<ActivityMainBinding, MainVM> {
    //判断是否点击二次返回键
    private Boolean isExit = false;

    private List<FunctionBean> mFunctionBean = new ArrayList<>();
    private FunctionAdapter mFunctionAdapter;
    private final String[] function_name = {"星座运势", "天气查询", "笔记记录", "新闻资讯", "成语接龙"};
    private final int[] function_img = {R.mipmap.constellation_icon, R.mipmap.weather, R.mipmap.note, R.mipmap.news, R.mipmap.news};
    private final int[] function_ID = {0, 1, 2, 3, 4};

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mBinding.titleTv.setText("欢迎");
        for (int i = 0; i < function_name.length; i++) {
            FunctionBean functionBean = new FunctionBean();
            functionBean.setFunction_name(function_name[i]);
            functionBean.setFunction_icon(function_img[i]);
            functionBean.setFunction_ID(function_ID[i]);
            mFunctionBean.add(functionBean);
        }
    }

    @Override
    protected void initData() {
        mFunctionAdapter = new FunctionAdapter(this, mFunctionBean, R.layout.item_function_layout);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        //去掉上拉刷新和下拉加载的阴影
        mBinding.functionRv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mBinding.functionRv.setLayoutManager(layoutManager);
        mBinding.functionRv.setAdapter(mFunctionAdapter);
        onClick();
    }

    private void onClick() {
        mFunctionAdapter.setOnItemClickListener(new IOnFunctionItemListener() {
            @Override
            public void OnFunctionItemClick(int position, int ID) {
                Log.i(TAG, "OnFunctionItemClick: " + position + "/" + ID);
                switch (ID) {
                    case 0:
                        ARouter.getInstance().jumpActivity("constellation/constellation", null);
                        break;
                    case 1:
                        ARouter.getInstance().jumpActivity("weather/weather", null);
                        break;
                    case 2:
                        ARouter.getInstance().jumpActivity("note/note", null);
                        break;
                    case 3:
                        ARouter.getInstance().jumpActivity("news/news", null);
                        break;
                    case 4:
                        ARouter.getInstance().jumpActivity("idiom/idiom", null);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void exit() {
        if (!isExit) {
            isExit = true;
            ToastUtils.showLong("再按一次退出程序");
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }

    };
}