package com.example.chenghejianzhi.presenter;

import android.annotation.SuppressLint;

import com.example.base.base.BasePresenter;
import com.example.base.bean.BaseBean;
import com.example.base.bean.CopyPartTimeBean;
import com.example.base.bean.JobDetailBean;
import com.example.base.rx.RxThrowableConsumer;
import com.example.base.rx.RxUtils;
import com.example.chenghejianzhi.contract.JobDetailContract;

import io.reactivex.functions.Consumer;

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
                    String apply = "0";
                    if ("0000".equals(baseBean.getStatus())){
                        apply = "1";
                    }
                    view.refreshApply(apply);
                }, new RxThrowableConsumer());
    }

}
