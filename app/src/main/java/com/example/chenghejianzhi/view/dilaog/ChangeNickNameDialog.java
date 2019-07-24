package com.example.chenghejianzhi.view.dilaog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.base.dialog.BaseDialog;
import com.example.chenghejianzhi.R;

import butterknife.BindView;

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
}
