package com.example.chenghejianzhi.fragments;

import android.view.View;

import com.example.base.base.BaseFragment;
import com.example.base.base.BaseMvpFragment;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.contract.AllContract;
import com.example.chenghejianzhi.presenter.AllPresenter;

import java.util.List;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:02
 * @describe ：
 */
public class AllFragment extends BaseMvpFragment<AllContract.Presenter> implements AllContract.View {
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initWidget(View root) {

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
