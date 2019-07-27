package com.example.chenghejianzhi.view.dilaog;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.constants.Constants;
import com.example.base.dialog.BaseDialog;
import com.example.base.util.ToastUtils;
import com.example.chenghejianzhi.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zdy On 2019/7/27.
 */
public class CopyContactDialog extends BaseDialog {
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_copy)
    TextView tvCopy;

    private int contactType;
    private String contact;

    public CopyContactDialog(Context context) {
        super(context);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.dialog_copy_contact;
    }

    @Override
    protected void initView(View root) {
        if (contactType == Constants.CONTACT_QQ){
            tvCopy.setText("前往QQ添加>");
            tvTitle.setText("已复制QQ号");
        }else if (contactType == Constants.CONTACT_WECHAT){
            tvCopy.setText("前往微信添加>");
            tvTitle.setText("已复制微信号");
        }else if (contactType == Constants.CONTACT_PHONE){
            tvTitle.setText("已复制电话");
            tvCopy.setText("拨打电话>");
        }

    }

    public void show(int contactType,String contact){
        this.contactType = contactType;
        this.contact = contact;
        show();
    }

    @OnClick({R.id.iv_close, R.id.tv_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_copy:
                if (contactType == Constants.CONTACT_QQ){
                    goQQApi(getContext());
                }else if (contactType == Constants.CONTACT_WECHAT){
                    goWeChatApi(getContext());
                }else if (contactType == Constants.CONTACT_PHONE){
                    callPhone(getContext(),contact);
                }

                dismiss();
                break;
        }
    }

    /**
     * 跳转到微信
     */
    private void goWeChatApi(Context context){
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mobileqq","com.tencent.mobileqq.activity.HomeActivity");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtils.showShortToast("检查到您手机没有安装微信，请安装后使用该功能");
        }
    }

    /**
     * 跳转到QQ
     */
    private void goQQApi(Context context){
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtils.showShortToast("检查到您手机没有安装QQ，请安装后使用该功能");
        }
    }
    /**
     * 拨打电话界面
     */
    public void callPhone(Context context,String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

}
