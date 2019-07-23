package com.example.chenghejianzhi.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseActivity;
import com.example.base.rx.RxEvent;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.constants.RoutMap;
import com.example.chenghejianzhi.fragments.AllFragment;
import com.example.chenghejianzhi.fragments.HomeFragment;
import com.example.chenghejianzhi.fragments.MineFragment;
import com.example.chenghejianzhi.fragments.RecommendFragment;
import com.example.chenghejianzhi.utils.StatusBarUtils;

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

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        rg_menu.check(R.id.menu_home);
        rg_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        switchFragment(R.id.rb_home);
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
