package com.example.chenghejianzhi.presenter;

import com.example.base.base.BasePresenter;
import com.example.chenghejianzhi.contract.AllContract;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 16:25
 * @describe ：
 */
public class AllPresenter extends BasePresenter<AllContract.View> implements AllContract.Presenter{

    public AllPresenter(AllContract.View view) {
        super(view);
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void getData() {

    }
}
