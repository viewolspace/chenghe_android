package com.example.chenghejianzhi.activity;

import com.example.base.base.BaseMvpActivity;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.contract.LoginContract;
import com.example.chenghejianzhi.presenter.LoginPresenter;
import com.example.chenghejianzhi.utils.StatusBarUtils;

public class LoginActivity extends BaseMvpActivity<LoginContract.Presenter> implements LoginContract.View {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void startSmsCodeCountDown(boolean sendSuccess) {

    }

}
