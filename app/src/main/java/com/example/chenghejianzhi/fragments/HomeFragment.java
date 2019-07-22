package com.example.chenghejianzhi.fragments;

import android.view.View;

import com.example.base.base.BaseFragment;
import com.example.base.base.BaseMvpFragment;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.contract.HomeContract;
import com.example.chenghejianzhi.presenter.HomePresenter;

import java.util.List;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:02
 * @describe ：
 */
public class HomeFragment extends BaseMvpFragment<HomeContract.Presenter> implements HomeContract.View {
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget(View root) {

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
