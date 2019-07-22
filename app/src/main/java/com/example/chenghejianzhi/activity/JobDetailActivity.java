package com.example.chenghejianzhi.activity;

import com.example.base.base.BaseMvpActivity;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.contract.JobDetailContract;
import com.example.chenghejianzhi.presenter.JobDetailPresenter;

import java.util.List;

public class JobDetailActivity extends BaseMvpActivity<JobDetailContract.Presenter> implements JobDetailContract.View {


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_job_detail;
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected JobDetailContract.Presenter initPresenter() {
        return new JobDetailPresenter(this);
    }

    @Override
    public void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList) {

    }
}
