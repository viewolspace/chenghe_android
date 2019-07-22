package com.example.chenghejianzhi.presenter;

import com.example.base.base.BasePresenter;
import com.example.chenghejianzhi.contract.RecommendContract;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 16:23
 * @describe ：
 */
public class RecommendPresenter extends BasePresenter<RecommendContract.View> implements RecommendContract.Presenter {
    public RecommendPresenter(RecommendContract.View view) {
        super(view);
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void getData() {

    }
}
