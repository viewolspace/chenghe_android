package com.example.chenghejianzhi.presenter;

import android.annotation.SuppressLint;

import com.example.base.base.BasePresenter;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.base.bean.CommonAdBean;
import com.example.base.bean.RecommendBean;
import com.example.base.constants.Constants;
import com.example.base.rx.RxThrowableConsumer;
import com.example.base.rx.RxUtils;
import com.example.chenghejianzhi.adapter.HomeAdapter;
import com.example.chenghejianzhi.adapter.RecommendAdapter;
import com.example.chenghejianzhi.contract.RecommendContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

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

    @SuppressLint("CheckResult")
    @Override
    public void getData(boolean refresh) {
        if (refresh){
            pageIndex =1;
            Observable.zip(api.getAd(Constants.AD_RECOMMEND_HOT),
                    api.queryRecommend(1, pageIndex, pageSize), (commonAdBean, recommendBean) -> {
                        List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                        if (commonAdBean!=null&&commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(RecommendAdapter.TOP_BANNER,commonAdBean));
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(RecommendAdapter.TITLE,"今日精选"));
                        }
                        if (recommendBean!=null&&recommendBean.getResult()!=null&&recommendBean.getResult().size()>0){
                            for (RecommendBean.ResultBean resultBean:recommendBean.getResult()){
                                recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(RecommendAdapter.RECOMMEND,resultBean));
                            }
                        }
                        if (recommendBean ==null||recommendBean.getResult()==null||recommendBean.getResult().size()<pageSize){
                            view.loadMoreEnable(false);
                        }else {
                            view.loadMoreEnable(true);
                        }
                        return recyclerItems;
                    }).compose(RxUtils.rxSchedulerHelper()).compose(mProvider.bindToLifecycle())
                    .subscribe(recyclerItems -> {
                        view.refreshList(recyclerItems,true);
                        view.loadFinish(true);
                    },new RxThrowableConsumer(){
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            super.accept(throwable);
                            view.loadFinish(true);
                        }
                    });
        }else {
            api.queryRecommend(2,pageIndex,pageSize)
                    .compose(RxUtils.rxSchedulerHelper())
                    .compose(mProvider.bindToLifecycle())
                    .subscribe(recommendBean -> {
                        view.loadFinish(false);
                        List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                        if (recommendBean!=null&&recommendBean.getResult()!=null&&recommendBean.getResult().size()>0){
                            for (RecommendBean.ResultBean resultBean:recommendBean.getResult()){
                                recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(RecommendAdapter.RECOMMEND,resultBean));
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
