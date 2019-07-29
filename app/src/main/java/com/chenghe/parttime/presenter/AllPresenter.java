package com.chenghe.parttime.presenter;

import android.annotation.SuppressLint;

import com.chenghe.base.base.BasePresenter;
import com.chenghe.base.base.BaseRecyclerAdapter;
import com.chenghe.base.bean.RecommendBean;
import com.chenghe.base.constants.Constants;
import com.chenghe.base.rx.RxThrowableConsumer;
import com.chenghe.base.rx.RxUtils;
import com.chenghe.parttime.adapter.AllAdapter;
import com.chenghe.parttime.contract.AllContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

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

    @SuppressLint("CheckResult")
    @Override
    public void getData(boolean refresh) {
        if (refresh){
            pageIndex=1;
            Observable.zip(api.getAd(Constants.AD_ALL_TOP)
                    , api.getAd(Constants.AD_ALL_HOT),
                    api.queryAll("", pageIndex, pageSize),
                    (commonAdBean, commonAdBean2, recommendBean) -> {
                        List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                        if (commonAdBean!=null&&commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(AllAdapter.TOP,commonAdBean));
                        }
                        if (commonAdBean2!=null&&commonAdBean2.getResult()!=null&&commonAdBean2.getResult().size()>0){
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(AllAdapter.BANNER,commonAdBean2));
                        }
                        if (recommendBean!=null&&recommendBean.getResult()!=null&&recommendBean.getResult().size()>0){
                            for (RecommendBean.ResultBean resultBean:recommendBean.getResult()){
                                recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(AllAdapter.RECOMMEND,resultBean));
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
            api.queryAll("",pageIndex,pageSize)
                    .compose(RxUtils.rxSchedulerHelper())
                    .compose(mProvider.bindToLifecycle())
                    .subscribe(recommendBean -> {
                        view.loadFinish(false);
                        List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                        if (recommendBean!=null&&recommendBean.getResult()!=null&&recommendBean.getResult().size()>0){
                            for (RecommendBean.ResultBean resultBean:recommendBean.getResult()){
                                recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(AllAdapter.RECOMMEND,resultBean));
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
