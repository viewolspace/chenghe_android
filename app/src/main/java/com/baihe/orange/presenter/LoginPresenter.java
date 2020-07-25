package com.baihe.orange.presenter;

import com.baihe.base.base.BasePresenter;
import com.baihe.base.bean.LoginBean;
import com.baihe.base.bean.PhoneCodeBean;
import com.baihe.base.bean.TokenBean;
import com.baihe.base.constants.Constants;
import com.baihe.base.rx.RxThrowableConsumer;
import com.baihe.base.rx.RxUtils;
import com.baihe.base.util.SpUtil;
import com.baihe.base.util.ToastUtils;
import com.baihe.base.util.UserInfoUtil;
import com.baihe.orange.contract.LoginContract;
import com.baihe.orange.utils.PhoneUtils;

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
        api.getToken(phone)
                .compose(mProvider.bindToLifecycle())
                .flatMap((Function<TokenBean, ObservableSource<PhoneCodeBean>>) tokenBean -> api.getPhoneCode(phone,tokenBean.getToken(), Constants.APP))
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(phoneCodeBean -> ToastUtils.showShortToast("发送短信成功"),new RxThrowableConsumer());

    }
}
