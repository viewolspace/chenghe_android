package com.example.chenghejianzhi.presenter;

import com.example.base.base.BasePresenter;
import com.example.base.bean.JobCommonBean;
import com.example.base.bean.PhoneCodeBean;
import com.example.base.bean.TokenBean;
import com.example.base.rx.RxOptional;
import com.example.base.rx.RxThrowableConsumer;
import com.example.base.rx.RxUtils;
import com.example.base.util.LogUtils;
import com.example.base.util.SpUtil;
import com.example.base.util.ToastUtils;
import com.example.chenghejianzhi.contract.LoginContract;
import com.example.chenghejianzhi.utils.PhoneUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                .compose(RxUtils.handleResultOptional()).subscribe(jobCommonBeanRxOptional -> {
            view.onLoginSucess();
        }, new RxThrowableConsumer());

    }

    @Override
    public void active() {
        api.active(PhoneUtils.getPhoneImei(), "2")
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle())
                .compose(RxUtils.handleResultOptional())
                .subscribe(jobCommonBeanRxOptional -> SpUtil.putBoolean(view.getAContext(), "ISFIRST", true));

    }

    @Override
    public void getToken(final String phone) {
        api.getToken(phone).compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle())
                .subscribe(new DisposableSubscriber<TokenBean>() {
                    @Override
                    public void onNext(TokenBean tokenBean) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
