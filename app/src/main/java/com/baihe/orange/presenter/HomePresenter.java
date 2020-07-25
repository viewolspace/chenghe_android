package com.baihe.orange.presenter;

import android.annotation.SuppressLint;

import com.baihe.base.base.BasePresenter;
import com.baihe.base.base.BaseRecyclerAdapter;
import com.baihe.base.bean.RecommendBean;
import com.baihe.base.constants.Constants;
import com.baihe.base.rx.RxThrowableConsumer;
import com.baihe.base.rx.RxUtils;
import com.baihe.orange.adapter.CommonAdapter;
import com.baihe.orange.contract.HomeContract;

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
            Observable.zip(api.getAd(Constants.AD_HOME_BANNER),
                    api.getAd(Constants.AD_JX_TOP2),
                    api.getAd(Constants.AD_JX_TOP1),
                    api.queryRecommend(Constants.SY_RECOMMENTD, pageIndex, pageSize),
                    (commonAdBean, commonAdBean2, commonAdBean3,recommendBean) -> {
                        List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();

                        if (commonAdBean!=null&&commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(CommonAdapter.BANNER,commonAdBean));
                            //recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_TITLE,"热门推荐"));
                        }


                        if (commonAdBean2!=null&&commonAdBean2.getResult()!=null&&commonAdBean2.getResult().size()>0){
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(CommonAdapter.JX_TOP2,commonAdBean2));
                        }
                        if (commonAdBean3!=null&&commonAdBean3.getResult()!=null&&commonAdBean3.getResult().size()>0){
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(CommonAdapter.JX_TOP,commonAdBean3));
                        }
                        if (recommendBean!=null&&recommendBean.getResult()!=null&&recommendBean.getResult().size()>0){
                            //recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(CommonAdapter.TITLE,"推荐"));
                            for (RecommendBean.ResultBean resultBean:recommendBean.getResult()){
                                recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(CommonAdapter.SY_RECOMMEND,resultBean));
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
            api.queryRecommend(Constants.SY_RECOMMENTD,pageIndex,pageSize)
                    .compose(RxUtils.rxSchedulerHelper())
                    .compose(mProvider.bindToLifecycle())
                    .subscribe(recommendBean -> {
                        view.loadFinish(false);
                        List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                        if (recommendBean!=null&&recommendBean.getResult()!=null&&recommendBean.getResult().size()>0){
                            for (RecommendBean.ResultBean resultBean:recommendBean.getResult()){
                                recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(CommonAdapter.SY_RECOMMEND,resultBean));
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
