package com.example.chenghejianzhi.view.dilaog;

import android.content.Context;
import android.view.View;

import com.example.base.dialog.BaseDialog;
import com.example.chenghejianzhi.R;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/24 16:55
 * @describe ：
 */
public class ChangeNickNameDialog extends BaseDialog {
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
