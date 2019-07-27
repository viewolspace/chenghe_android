package com.example.chenghejianzhi.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.base.base.BaseMvpActivity;
import com.example.base.util.PermissionUtil;
import com.example.base.util.SpUtil;
import com.example.base.util.ToastUtils;
import com.example.base.util.UserInfoUtil;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.contract.LoginContract;
import com.example.chenghejianzhi.presenter.LoginPresenter;
import com.example.chenghejianzhi.utils.StatusBarUtils;

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


    public static void start(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

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
        if (userId != null&&!userId.trim().isEmpty()&&!"0".equals(userId)){
            MainActivity.start(LoginActivity.this);
            finish();

        }
    }

    @OnClick({R.id.tv_login,R.id.tv_getcode})
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
                presenter.getToken(et_phone.getText().toString());
                break;

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
    public void onLoginSucess() {
       MainActivity.start(LoginActivity.this);
        finish();
       // ARouter.getInstance().build(RoutMap.ACTIVITY_MAIN).navigation();
    }

}
