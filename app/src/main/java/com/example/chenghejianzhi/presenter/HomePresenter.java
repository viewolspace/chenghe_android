package com.example.chenghejianzhi.presenter;

import com.example.base.base.BasePresenter;
import com.example.chenghejianzhi.contract.HomeContract;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 16:12
 * @describe ：
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter{
    public HomePresenter(HomeContract.View view) {
        super(view);
    }
    @Override
    public void getData() {

    }
    @Override
    protected void onDestroy() {

    }


}
