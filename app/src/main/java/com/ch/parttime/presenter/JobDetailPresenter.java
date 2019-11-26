package com.ch.parttime.presenter;

import android.annotation.SuppressLint;

import com.ch.base.base.BasePresenter;
import com.ch.base.rx.RxThrowableConsumer;
import com.ch.base.rx.RxUtils;
import com.ch.parttime.contract.JobDetailContract;

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


    @SuppressLint("CheckResult")
    @Override
    public void getData(int id) {
        api.getJobDetail(id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle())
                .subscribe(jobDetailBean -> view.refreshList(jobDetailBean)
                        ,new RxThrowableConsumer());
    }

    @SuppressLint("CheckResult")
    @Override
    public void copyRecord(int id) {
        api.copyPartTime(id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle())
                .subscribe(baseBean -> {

                }, new RxThrowableConsumer());
    }

    @SuppressLint("CheckResult")
    @Override
    public void apply(int id) {
        api.joinPartTime(id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle())
                .subscribe(baseBean -> {

                    view.refreshApply(baseBean);
                }, new RxThrowableConsumer());
    }

}
