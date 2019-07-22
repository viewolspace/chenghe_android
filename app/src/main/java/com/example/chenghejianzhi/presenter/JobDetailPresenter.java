package com.example.chenghejianzhi.presenter;

import com.example.base.base.BasePresenter;
import com.example.chenghejianzhi.contract.JobDetailContract;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 16:31
 * @describe ：
 */
public class JobDetailPresenter extends BasePresenter<JobDetailContract.View> implements JobDetailContract.Presenter{
    public JobDetailPresenter(JobDetailContract.View view) {
        super(view);
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void getData() {

    }
}
