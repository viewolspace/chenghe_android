package com.example.chenghejianzhi.fragments;

import android.view.View;

import com.example.base.base.BaseFragment;
import com.example.base.base.BaseMvpFragment;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.contract.RecommendContract;
import com.example.chenghejianzhi.presenter.RecommendPresenter;

import java.util.List;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:02
 * @describe ：
 */
public class RecommendFragment extends BaseMvpFragment<RecommendContract.Presenter> implements RecommendContract.View {
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected RecommendContract.Presenter initPresenter() {
        return new RecommendPresenter(this);
    }

    @Override
    public void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList) {

    }
}
