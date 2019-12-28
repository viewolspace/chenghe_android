package com.parttime.potato.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.parttime.base.base.BaseActivity;
import com.parttime.base.constants.RoutMap;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.util.PermissionUtil;
import com.parttime.base.util.UserInfoUtil;
import com.parttime.potato.R;
import com.parttime.potato.fragments.AllFragment;
import com.parttime.potato.fragments.HomeFragment;
import com.parttime.potato.fragments.JXFragment;
import com.parttime.potato.fragments.MineFragment;
import com.parttime.potato.fragments.RecommendFragment;
import com.parttime.potato.utils.StatusBarUtils;

import butterknife.BindView;

@Route(path = RoutMap.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.rg_menu)
    RadioGroup rg_menu;

    private Fragment currentFragment;
    private HomeFragment homeFragment;
    private int currentId = -1;
    private RecommendFragment recommendFragment;
    private AllFragment allFragment;
    private MineFragment mineFragment;
    private JXFragment jxFragment;
    //缓存当前Tab的选中下标
    public static final String BUNDLE_CACHE_INDEX_KEY = "bundle_cache_index_key";
    //fragment缓存标签
    public static final String FRAGMENT_TAG = "fragment_tag";
    public static void start(Context context){
        Intent intent  = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }


//    @Override
//    protected void initSavedInstance(Bundle savedInstanceState) {
//        if (savedInstanceState!=null){
//            currentId = savedInstanceState.getInt(BUNDLE_CACHE_INDEX_KEY,-1);
//            //如果已经添加过fragment, 需要隐藏操作
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG+R.id.rb_home);
//            recommendFragment = (RecommendFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG+R.id.rb_recommend);
//            allFragment = (AllFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG+R.id.rb_all);
//            mineFragment = (MineFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG+R.id.rb_mine);
//            if (homeFragment!=null){
//                transaction.hide(homeFragment);
//            }
//            if (recommendFragment!=null){
//                transaction.hide(recommendFragment);
//            }
//            if (allFragment!=null){
//                transaction.hide(allFragment);
//            }
//            if (mineFragment!=null){
//                transaction.hide(mineFragment);
//            }
//            transaction.commit();
//            if (currentId!=-1){
//                rg_menu.check(currentId);
//            }else {
//                rg_menu.check(R.id.rb_home);
//            }
//        }
//
//    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbarWihte(this);
        rg_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        if (currentId!=-1){
            rg_menu.check(currentId);
        }else {
            rg_menu.check(R.id.rb_home);
        }


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
            fm.hide(currentFragment);
        }

        switch (checkedId){
            case R.id.rb_home:
                if (homeFragment == null){
                    homeFragment = new HomeFragment();
                    fm.add(R.id.fl_container,homeFragment,FRAGMENT_TAG+checkedId);
                }
                fm.show(homeFragment);
                currentFragment = homeFragment;
                break;
            case R.id.rb_recommend:
                if (recommendFragment == null){
                    recommendFragment = new RecommendFragment();
                    fm.add(R.id.fl_container,recommendFragment,FRAGMENT_TAG+checkedId);
                }
                fm.show(recommendFragment);
                currentFragment = recommendFragment;
                break;
            case R.id.rb_jingxuan:
                if (jxFragment == null){
                    jxFragment = new JXFragment();
                    fm.add(R.id.fl_container,jxFragment,FRAGMENT_TAG+checkedId);
                }
                fm.show(jxFragment);
                currentFragment = jxFragment;
                break;
            case R.id.rb_all:
                if (allFragment == null){
                    allFragment = new AllFragment();
                    fm.add(R.id.fl_container, allFragment,FRAGMENT_TAG+checkedId);
                }
                fm.show(allFragment);
                currentFragment = allFragment;
                break;
            case R.id.rb_mine:
                if (mineFragment == null){
                    mineFragment = new MineFragment();
                    fm.add(R.id.fl_container, mineFragment,FRAGMENT_TAG+checkedId);
                }
                fm.show(mineFragment);
                currentFragment = mineFragment;
                break;
        }
        fm.commitAllowingStateLoss();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_CACHE_INDEX_KEY,currentId);
        super.onSaveInstanceState(outState);
    }
}
