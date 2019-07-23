package com.example.chenghejianzhi.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.base.base.BaseFragment;
import com.example.base.base.BaseMvpFragment;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.adapter.HomeAdapter;
import com.example.chenghejianzhi.contract.HomeContract;
import com.example.chenghejianzhi.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:02
 * @describe ：
 */
public class HomeFragment extends BaseMvpFragment<HomeContract.Presenter> implements HomeContract.View {
    @BindView(R.id.recycler_home)
    RecyclerView recycler_home;
    private HomeAdapter homeAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget(View root) {
        recycler_home.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter();
        recycler_home.setAdapter(homeAdapter);
        List<BaseRecyclerAdapter.RecyclerItem> itemList = new ArrayList<>();
        itemList.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_TOP,null));
        itemList.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_TITLE,"热门活动"));
        itemList.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_HOT,null));
        itemList.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_TITLE,"热门推荐"));
        for (int i = 0;i<10;i++){
            itemList.add(new BaseRecyclerAdapter.RecyclerItem(HomeAdapter.HOME_RECOMMEND,null));
        }
        homeAdapter.replace(itemList);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected HomeContract.Presenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList) {

    }
}
