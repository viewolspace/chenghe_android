package com.example.base.base;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.base.util.PermissionUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/18 15:39
 * @describe ：
 */
public abstract class BaseActivity extends AActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在界面未初始化之前调用的初始化窗口
        initWindows();
        if (initArgs(getIntent().getExtras())) {
            // 得到界面Id并设置到Activity界面中
            int layId = getContentLayoutId();
            setContentView(layId);
            ButterKnife.bind(this);
            initWidget();
            initData();
        } else {
            finish();
        }
    }

    /**
     * 初始化相关参数
     * @param bundle 参数Bundle
     * @return 如果参数正确返回True，错误返回False
     */
    public boolean initArgs(Bundle bundle){
        return true;
    }

    /**
     * 初始化窗口
     */
    protected void initWindows(){

    }
    /**
     * 得到当前界面的资源文件id
     * @return 资源文件id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected  abstract void initWidget();

    /**
     * 初始化数据
     */
    protected  abstract void initData();

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

//    @Override
//    public void onBackPressed() {
//        // 得到当前Activity下的所有Fragment
//        List<Fragment> fragments = getSupportFragmentManager().getFragments();
//        for (android.support.v4.app.Fragment fragment:fragments){
//            // 判断是否为我们能够处理的Fragment类型
//            if (fragment instanceof Fragment){
//                // 判断是否拦截了返回按钮
//                if (((Fragment) fragment).onBackPressed()){
//                    // 如果有直接Return
//                    return;
//                }
//            }
//        }
//        super.onBackPressed();
//        finish();
//    }
}
