package com.parttime.happy.presenter;

import android.annotation.SuppressLint;

import com.parttime.base.base.BasePresenter;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.RecommendBean;
import com.parttime.base.constants.Constants;
import com.parttime.base.rx.RxThrowableConsumer;
import com.parttime.base.rx.RxUtils;
import com.parttime.happy.adapter.CommonAdapter;
import com.parttime.happy.contract.HotContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 16:25
 * @describe ：
 */
public class HotPresenter extends BasePresenter<HotContract.View> implements HotContract.Presenter{

    public HotPresenter(HotContract.View view) {
        super(view);
    }

    @Override
    protected void onDestroy() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void getData(boolean refresh) {
        if (refresh){
            pageIndex=1;
            api.queryRecommend2(Constants.RM_RECOMMENTD,pageIndex,pageSize,1)
                    .compose(RxUtils.rxSchedulerHelper())
                    .compose(mProvider.bindToLifecycle())
                    .subscribe(recommendBean -> {
                        view.loadFinish(true);
                        List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                        if (recommendBean!=null&&recommendBean.getResult()!=null&&recommendBean.getResult().size()>0){
                            for (RecommendBean.ResultBean resultBean:recommendBean.getResult()){
                                recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(CommonAdapter.RM_RECOMMEND,resultBean));
                            }
                        }
                        if (recommendBean ==null||recommendBean.getResult()==null||recommendBean.getResult().size()<pageSize){
                            view.loadMoreEnable(false);
                        }else {
                            view.loadMoreEnable(true);
                        }
                        view.refreshList(recyclerItems,true);
                        pageIndex++;

                    },new RxThrowableConsumer(){
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            super.accept(throwable);
                            view.loadFinish(true);
                        }
                    });
        }else {
            api.queryRecommend2(Constants.SY_RECOMMENTD,pageIndex,pageSize,1)
                    .compose(RxUtils.rxSchedulerHelper())
                    .compose(mProvider.bindToLifecycle())
                    .subscribe(recommendBean -> {
                        view.loadFinish(false);
                        List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                        if (recommendBean!=null&&recommendBean.getResult()!=null&&recommendBean.getResult().size()>0){
                            for (RecommendBean.ResultBean resultBean:recommendBean.getResult()){
                                recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(CommonAdapter.JX_RECOMMEND,resultBean));
                            }
                        }
                        if (recommendBean ==null||recommendBean.getResult()==null||recommendBean.getResult().size()<pageSize){
                            view.loadMoreEnable(false);
                        }
                        view.refreshList(recyclerItems,false);
                        pageIndex++;

                    },new RxThrowableConsumer(){
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            super.accept(throwable);
                            view.loadFinish(false);
                        }
                    });
        }

    }
}
