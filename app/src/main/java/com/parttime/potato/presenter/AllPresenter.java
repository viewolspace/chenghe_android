package com.parttime.potato.presenter;

import android.annotation.SuppressLint;

import com.parttime.base.base.BasePresenter;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.CommonAdBean;
import com.parttime.base.bean.RecommendBean;
import com.parttime.base.constants.Constants;
import com.parttime.base.rx.RxThrowableConsumer;
import com.parttime.base.rx.RxUtils;
import com.parttime.potato.adapter.AllAdapter;
import com.parttime.potato.contract.AllContract;

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
                    , api.queryAll("", pageIndex, pageSize,2),
                    (commonAdBean, recommendBean) -> {
                        List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                        //recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(AllAdapter.TOP,new CommonAdBean()));

                        if (commonAdBean!=null&&commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(AllAdapter.TOP,commonAdBean));
                        }
//                        if (commonAdBean2!=null&&commonAdBean2.getResult()!=null&&commonAdBean2.getResult().size()>0){
//                            recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(AllAdapter.BANNER,commonAdBean2));
//                        }
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
            api.queryAll("",pageIndex,pageSize,2)
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
