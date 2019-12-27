package com.ch.parttime.presenter;

import android.content.Context;

import com.ch.base.base.BasePresenter;
import com.ch.base.bean.LoginBean;
import com.ch.base.bean.PhoneCodeBean;
import com.ch.base.bean.TokenBean;
import com.ch.base.constants.Constants;
import com.ch.base.rx.RxThrowableConsumer;
import com.ch.base.rx.RxUtils;
import com.ch.base.util.SpUtil;
import com.ch.base.util.ToastUtils;
import com.ch.base.util.UserInfoUtil;
import com.ch.parttime.contract.LoginContract;
import com.ch.parttime.utils.PhoneUtils;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
        api.login(PhoneUtils.getPhoneImei(), code, phone)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle())
               .subscribe(new Consumer<LoginBean>() {
                   @Override
                   public void accept(LoginBean loginBean) throws Exception {
                       if ("0000".equals(loginBean.getStatus())){
                           view.onLoginSuccess();
                           UserInfoUtil.getInstance().setUserInfo(loginBean.getResult());
                       }else {
                           view.onLoginFaild(loginBean.getMessage());
                       }

                   }
               },new RxThrowableConsumer());

    }

    @Override
    public void active() {
        api.active(PhoneUtils.getPhoneImei(), "2")
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle())
                .subscribe(jobCommonBeanBaseResponse ->
                        SpUtil.putBoolean(view.getAContext(), "ISFIRST", true),new RxThrowableConsumer());

    }

    @Override
    public void getToken(final String phone) {
        api.getToken(phone, Constants.APP)
                .compose(mProvider.bindToLifecycle())
                .flatMap((Function<TokenBean, ObservableSource<PhoneCodeBean>>) tokenBean -> api.getPhoneCode(phone,tokenBean.getToken()))
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(phoneCodeBean -> ToastUtils.showShortToast("发送短信成功"),new RxThrowableConsumer());

    }
}
