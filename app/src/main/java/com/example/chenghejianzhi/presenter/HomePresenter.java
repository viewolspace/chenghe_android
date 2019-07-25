package com.example.chenghejianzhi.presenter;

import com.example.base.base.BasePresenter;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.base.bean.JobCommonBean;
import com.example.base.rx.RxOptional;
import com.example.base.rx.RxThrowableConsumer;
import com.example.base.rx.RxUtils;
import com.example.chenghejianzhi.adapter.HomeAdapter;
import com.example.chenghejianzhi.contract.HomeContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

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
    public void getData() {
        api.queryRecommend("000000020001",1,10)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle())
                .compose(RxUtils.handleResultOptional()).subscribe(jobCommonBeanRxOptional -> {
            List<BaseRecyclerAdapter.RecyclerItem> itemList = new ArrayList<>();
            itemList.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_TOP,null));
            itemList.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_TITLE,"热门活动"));
            itemList.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_HOT,null));
            itemList.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_TITLE,"热门推荐"));
            for (int i = 0;i<10;i++){
                itemList.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_RECOMMEND,null));
            }
                    view.refreshList(itemList);
                },new RxThrowableConsumer(){

        });
    }
    @Override
    protected void onDestroy() {

    }


}
