package com.chenghe.parttime.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chenghe.base.base.BaseMvpActivity;
import com.chenghe.base.util.PermissionUtil;
import com.chenghe.base.util.SpUtil;
import com.chenghe.base.util.ToastUtils;
import com.chenghe.base.util.UserInfoUtil;
import com.chenghe.parttime.R;
import com.chenghe.parttime.contract.LoginContract;
import com.chenghe.parttime.presenter.LoginPresenter;
import com.chenghe.parttime.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.tv_getcode)
    TextView tv_getcode;
    private Handler handler;
    private int countDown;
    private boolean isCountDown;

    public static void start(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        countDown--;
                        if (countDown<=0){
                            countDown = 0;
                            tv_getcode.setText("发送验证码");
                            isCountDown = false;
                            setGetCode(true);
                        }else {
                            tv_getcode.setText(String.valueOf(countDown));
                            sendEmptyMessageDelayed(0,1000);
                        }

                    break;
                }
            }
        };
    }

    @Override
    protected void initData() {

       PermissionUtil.requestPermissionCombined(this, new PermissionUtil.CombinedPermissionListenerImp() {
           @Override
           public void onCombinedGranted() {
               super.onCombinedGranted();
           }
       }, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        boolean isFirst = SpUtil.getBoolean(getAContext(), "ISFIRST", false);
        if (!isFirst) {
            presenter.active();
        }
        String userId = UserInfoUtil.getInstance().getUserId();
//        if (userId != null&&!userId.trim().isEmpty()&&!"0".equals(userId)){
//            MainActivity.start(LoginActivity.this);
//            finish();
//
//        }
        setGetCode(false);
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s!=null&&!s.toString().trim().isEmpty()&&s.toString().length()>=11&&!isCountDown){
                    setGetCode(true);
                }else {
                    setGetCode(false);
                }

            }
        });
    }

    @OnClick({R.id.tv_login,R.id.tv_getcode,R.id.iv_close})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login:
                if (et_phone.getText() == null||et_phone.getText().toString().length()<11){
                    ToastUtils.showLongToast("请输入正确的手机号");
                    return;
                }
                if (et_code.getText() == null||et_code.getText().toString().trim().isEmpty()){
                    ToastUtils.showLongToast("请输入验证码");
                    return;
                }
                presenter.login(et_phone.getText().toString(),et_code.getText().toString());
                break;
            case R.id.tv_getcode:
                if (et_phone.getText() == null||et_phone.getText().toString().length()<11){
                    ToastUtils.showLongToast("请输入正确的手机号");
                    return;
                }
                isCountDown = true;
                handler.sendEmptyMessageDelayed(0,1000);
                setGetCode(false);
                countDown = 60;
                presenter.getToken(et_phone.getText().toString());
                break;
            case R.id.iv_close:
                finish();
                break;

        }
    }

    private void setGetCode(boolean enable){
        if (enable){
            tv_getcode.setTextColor(getResources().getColor(R.color.color_588FFE));
            tv_getcode.setEnabled(true);
        }else {
            tv_getcode.setTextColor(getResources().getColor(R.color.color_B2B2B2));
            tv_getcode.setEnabled(false);
        }
    }
    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void startSmsCodeCountDown(boolean sendSuccess) {

    }

    @Override
    public void onLoginSuccess() {
//       MainActivity.start(LoginActivity.this);
        ToastUtils.showShortToast("登录成功");
        finish();
       // ARouter.getInstance().build(RoutMap.ACTIVITY_MAIN).navigation();
    }

    @Override
    public void onLoginFaild(String message) {
        ToastUtils.showShortToast("登录失败:"+message);
    }

}
