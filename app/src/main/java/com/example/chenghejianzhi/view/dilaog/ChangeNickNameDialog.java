package com.example.chenghejianzhi.view.dilaog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.base.dialog.BaseDialog;
import com.example.base.util.ToastUtils;
import com.example.base.util.UserInfoUtil;
import com.example.chenghejianzhi.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/24 16:55
 * @describe ：
 */
public class ChangeNickNameDialog extends BaseDialog {
    @BindView(R.id.tv_dialog_confirm)
    TextView tv_dialog_confirm;
    @BindView(R.id.tv_dialog_skip)
    TextView tv_dialog_skip;
    @BindView(R.id.et_dialog_contact)
    TextView et_dialog_contact;
    public ChangeNickNameDialog(Context context) {
        super(context);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.dialog_change_nickname;
    }

    @Override
    protected void initView(View root) {
    }
    @OnClick({R.id.tv_dialog_skip,R.id.tv_dialog_confirm})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_dialog_confirm:
                if (et_dialog_contact.getText()!=null&&!
                        et_dialog_contact.getText().toString().trim().isEmpty()){
                    UserInfoUtil.getInstance().upDateNiceName(et_dialog_contact.getText().toString());
                    dismiss();
                }else {
                    ToastUtils.showShortToast("请输入昵称");
                }

                break;
            case R.id.tv_dialog_skip:
                dismiss();
                break;
        }
    }

}
