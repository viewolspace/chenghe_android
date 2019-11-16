package com.parttime.potato.presenter;

import android.annotation.SuppressLint;

import com.parttime.base.base.BasePresenter;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.RecommendBean;
import com.parttime.base.constants.Constants;
import com.parttime.base.rx.RxThrowableConsumer;
import com.parttime.base.rx.RxUtils;
import com.parttime.potato.adapter.HomeAdapter;
import com.parttime.potato.contract.HomeContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

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
    protected void onDestroy() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void getData(boolean refresh) {
        if (refresh){
            pageIndex = 1;
            Observable.zip(api.getAd(Constants.AD_HOME_TOP)
                    , api.getAd(Constants.AD_HOME_HOT),
                    api.queryRecommend(1, pageIndex, pageSize),
                    (commonAdBean, commonAdBean2, recommendBean) -> {
                        List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                        if (commonAdBean!=null&&commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_TOP,commonAdBean));
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_TITLE,"热门"));
                        }
                        if (commonAdBean2!=null&&commonAdBean2.getResult()!=null&&commonAdBean2.getResult().size()>0){
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_HOT,commonAdBean2));
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_TITLE,"推荐"));
                        }
                        if (recommendBean!=null&&recommendBean.getResult()!=null&&recommendBean.getResult().size()>0){
                            for (RecommendBean.ResultBean resultBean:recommendBean.getResult()){
                                recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_RECOMMEND,resultBean));
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
                                recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_RECOMMEND,resultBean));
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
