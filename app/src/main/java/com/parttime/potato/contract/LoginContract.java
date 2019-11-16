package com.parttime.potato.contract;

import com.parttime.base.base.BaseContract;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:53
 * @describe ：
 */
public interface LoginContract {
    interface Presenter extends BaseContract.Presenter<View> {
        void postSmsCode(String phone);

        void login(String phone,String code);

        void active();

        void getToken(String phone);
    }

    interface View extends BaseContract.View<Presenter>{

        void startSmsCodeCountDown(boolean sendSuccess);

        void onLoginSuccess();

        void onLoginFaild(String message);
    }
}
