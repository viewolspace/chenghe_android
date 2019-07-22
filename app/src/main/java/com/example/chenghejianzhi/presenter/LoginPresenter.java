package com.example.chenghejianzhi.presenter;

import com.example.base.base.BasePresenter;
import com.example.chenghejianzhi.contract.LoginContract;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 15:03
 * @describe ：
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void postSmsCode(String phone) {

    }

    @Override
    public void login(String phone, String code) {

    }
}
