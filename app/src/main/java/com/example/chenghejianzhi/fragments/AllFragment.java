package com.example.chenghejianzhi.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.base.base.BaseFragment;
import com.example.base.base.BaseMvpFragment;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.adapter.RecommendAdapter;
import com.example.chenghejianzhi.contract.AllContract;
import com.example.chenghejianzhi.presenter.AllPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:02
 * @describe ：
 */
public class AllFragment extends BaseMvpFragment<AllContract.Presenter> implements AllContract.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initWidget(View root) {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        RecommendAdapter recommendAdapter = new RecommendAdapter();
        recycler.setAdapter(recommendAdapter);
        List<BaseRecyclerAdapter.RecyclerItem> itemList = new ArrayList<>();
        itemList.add(new BaseRecyclerAdapter.RecyclerItem(RecommendAdapter.TOP_BANNER,null));
        itemList.add(new BaseRecyclerAdapter.RecyclerItem(RecommendAdapter.TITLE,"今日精选"));
        for (int i = 0;i<10;i++){
            itemList.add(new BaseRecyclerAdapter.RecyclerItem(RecommendAdapter.RECOMMEND,null));
        }
        recommendAdapter.replace(itemList);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected AllContract.Presenter initPresenter() {
        return new AllPresenter(this);
    }

    @Override
    public void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList) {

    }
}
