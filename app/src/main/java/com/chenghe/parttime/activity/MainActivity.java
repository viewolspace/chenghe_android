package com.chenghe.parttime.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chenghe.base.base.BaseActivity;
import com.chenghe.base.constants.RoutMap;
import com.chenghe.base.rx.RxEvent;
import com.chenghe.base.util.PermissionUtil;
import com.chenghe.base.util.UserInfoUtil;
import com.chenghe.parttime.R;
import com.chenghe.parttime.fragments.AllFragment;
import com.chenghe.parttime.fragments.HomeFragment;
import com.chenghe.parttime.fragments.MineFragment;
import com.chenghe.parttime.fragments.RecommendFragment;
import com.chenghe.parttime.utils.StatusBarUtils;

import butterknife.BindView;

@Route(path = RoutMap.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.rg_menu)
    RadioGroup rg_menu;

    private Fragment currentFragment;
    private HomeFragment homeFragment;
    private int currentId;
    private RecommendFragment recommendFragment;
    private AllFragment allFragment;
    private MineFragment mineFragment;

    public static void start(Context context){
        Intent intent  = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        rg_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        rg_menu.check(R.id.rb_home);

        PermissionUtil.requestPermissionCombined(this, new PermissionUtil.CombinedPermissionListenerImp() {
            @Override
            public void onCombinedGranted() {
                super.onCombinedGranted();
            }
        }, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (UserInfoUtil.getInstance().isLogin()){
            UserInfoUtil.getInstance().upDateLocalUser();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }

    private void switchFragment(int checkedId){
        if (currentId == checkedId){
            return;
        }
        currentId = checkedId;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fm = fragmentManager.beginTransaction();
        if (currentFragment!=null){
            fm.detach(currentFragment);
        }

        switch (checkedId){
            case R.id.rb_home:
                if (homeFragment == null){
                    homeFragment = new HomeFragment();
                    fm.add(R.id.fl_container,homeFragment);
                }
                fm.attach(homeFragment);
                currentFragment = homeFragment;
                break;
            case R.id.rb_recommend:
                if (recommendFragment == null){
                    recommendFragment = new RecommendFragment();
                    fm.add(R.id.fl_container,recommendFragment);
                }
                fm.attach(recommendFragment);
                currentFragment = recommendFragment;
                break;
            case R.id.rb_all:
                if (allFragment == null){
                    allFragment = new AllFragment();
                    fm.add(R.id.fl_container, allFragment);
                }
                fm.attach(allFragment);
                currentFragment = allFragment;
                break;
            case R.id.rb_mine:
                if (mineFragment == null){
                    mineFragment = new MineFragment();
                    fm.add(R.id.fl_container, mineFragment);
                }
                fm.attach(mineFragment);
                currentFragment = mineFragment;
                break;
        }
        fm.commitAllowingStateLoss();
    }

}
