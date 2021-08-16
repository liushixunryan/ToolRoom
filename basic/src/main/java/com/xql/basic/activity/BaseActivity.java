package com.xql.basic.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xql.basic.viewmodel.BaseViewModel;
import com.xql.loading.LoadingDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.litepal.tablemanager.Connector;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.xql.basic
 * @ClassName: BaseActivity
 * @CreateDate: 2021/6/4 13:26
 * @UpdateUser: RyanLiu
 */

public abstract class BaseActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
    protected final String TAG = "sansuiban";
    //等待窗对象
    private LoadingDialog loadingDialog;
    public B mBinding;
    public VM mViewModel;
    //是否显示标题栏
    private boolean isShowTitle = false;
    //是否显示状态栏
    private boolean isShowStatusBar = false;
    //是否允许旋转屏幕
    private boolean isAllowScreenRoate = true;
    //定位需要的声明
    protected AMapLocationClient mLocationClient = null;
    protected AMapLocationClientOption mLocationOption = null;
    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //activityi管理
        ActivityCollector.addActivity(this);
        if (!isShowTitle) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (!isShowStatusBar) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //如果>=4.4才支持沉浸式
        //显示上面的导航栏
        //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //显示下面的导航键
        //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //隐藏状态栏
        //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //显示状态栏
        //            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //        }

        //设置屏幕是否可旋转
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        //初始化布局
        mBinding = DataBindingUtil.setContentView(this, layoutId());
        mBinding.setLifecycleOwner(this);

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());//定位发起端
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();//定位参数
        initDialog();
        //创建我们的ViewModel。
        createViewModel();
        //初始化控件
        initView();
        //设置数据
        initData();

    }

    /**
     * 初始化各种Dialog
     */
    private void initDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
    }

    /**
     * 显示等待Dialog
     */
    public void showLoading() {
        if (loadingDialog != null && !loadingDialog.isShowing())
            loadingDialog.show();
    }

    /**
     * 显示等待Dialog，可自定义显示内容
     */
    public LoadingDialog showLoading(String msg) {
        if (loadingDialog != null && !loadingDialog.isShowing())
            loadingDialog.setMessage(msg).show();
        return loadingDialog;
    }

    /**
     * 隐藏等待Dialog
     */
    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 绑定viewmodel
     */
    public void createViewModel() {
        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
                Log.i(TAG, "createViewModel: " + modelClass.toString());
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(modelClass);
        }
    }

    /**
     * 初始化布局
     *
     * @return 布局id
     */
    protected abstract int layoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置数据
     */
    protected abstract void initData();

    /**
     * 设置是否显示标题栏
     *
     * @param showTitle true or false
     */
    public void setShowTitle(boolean showTitle) {
        isShowTitle = showTitle;
    }

    /**
     * 设置是否显示状态栏
     *
     * @param showStatusBar true or false
     */
    public void setShowStatusBar(boolean showStatusBar) {
        isShowStatusBar = showStatusBar;
    }

    /**
     * 是否允许屏幕旋转
     *
     * @param allowScreenRoate true or false
     */
    public void setAllowScreenRoate(boolean allowScreenRoate) {
        isAllowScreenRoate = allowScreenRoate;
    }

    /**
     * 保证同一按钮在1秒内只会响应一次点击事件
     */
    public abstract class OnSingleClickListener implements View.OnClickListener {
        //两次点击按钮之间的间隔，目前为1000ms
        private static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime;

        public abstract void onSingleClick(View view);

        @Override
        public void onClick(View view) {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                lastClickTime = curClickTime;
                onSingleClick(view);
            }
        }
    }

    /**
     * 同一按钮在短时间内可重复响应点击事件
     */
    public abstract class OnMultiClickListener implements View.OnClickListener {
        public abstract void onMultiClick(View view);

        @Override
        public void onClick(View v) {
            onMultiClick(v);
        }
    }

    //活动管理器
    public static class ActivityCollector {

        public static List<Activity> activitys = new ArrayList<Activity>();

        /**
         * 向List中添加一个活动
         *
         * @param activity 活动
         */
        public static void addActivity(Activity activity) {

            activitys.add(activity);
        }

        /**
         * 从List中移除活动
         *
         * @param activity 活动
         */
        public static void removeActivity(Activity activity) {

            activitys.remove(activity);
        }

        /**
         * 将List中存储的活动全部销毁掉
         */
        public static void finishAll() {

            for (Activity activity : activitys) {

                if (!activity.isFinishing()) {

                    activity.finish();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity管理
        ActivityCollector.removeActivity(this);
    }

    /**
     * 高德地图初始化配置
     */
    protected void Positioningservice() {
        boolean granted = PermissionUtils.isGranted(Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION);
        if (granted) {
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            //设置是否只定位一次,默认为false
            mLocationOption.setOnceLocation(false);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            mLocationOption.setMockEnable(false);
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(60000);
            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            //启动定位
            mLocationClient.startLocation();
        } else {
            ToastUtils.showLong("您没有授权将无法使用天气功能");
            hideLoading();
        }

    }

    /**
     * 权限申请
     */
    private void Requestspermissions() {
        AndPermission.with(context).runtime().permission(Permission.WRITE_EXTERNAL_STORAGE, Permission.ACCESS_COARSE_LOCATION, Permission.READ_EXTERNAL_STORAGE, Permission.ACCESS_FINE_LOCATION).onDenied(permissions -> {
            ToastUtils.showLong("权限获取失败,将退出应用");
            ActivityCollector.finishAll();
        }).onGranted(permissions -> {
            Positioningservice();
        }).start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 在onStart方法中调用权限申请,可以防止调用两次
         */
        Requestspermissions();
    }
}
