package com.parttime.happy.view.dilaog;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parttime.base.constants.Constants;
import com.parttime.base.dialog.BaseDialog;
import com.parttime.base.util.ToastUtils;
import com.parttime.happy.R;
import com.parttime.happy.activity.PersonalResumeActivity;

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
    private int  flag;

    public CopyContactDialog(Context context) {
        super(context);
    }

    public CopyContactDialog(Context context, int contactType, String contact, int flag) {
        super(context);
        this.contactType = contactType;
        this.contact = contact;
        this.flag = flag;
        refreshData();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.dialog_copy_contact;
    }

    @Override
    protected void initView(View root) {



    }


    private void refreshData(){
        if (contact==null||contact.trim().isEmpty()){
            if (flag==1){
                tvCopy.setText("前往完善简历>");
                tvTitle.setText("您的简历还未完善");
            }
        }else {
            if (contactType == Constants.CONTACT_QQ){
                tvCopy.setText("前往QQ联系雇主");
                tvTitle.setText("已为您复制QQ号");
            }else if (contactType == Constants.CONTACT_WECHAT){
                tvCopy.setText("前往微信联系雇主");
                tvTitle.setText("已为您复制微信号");
            }else if (contactType == Constants.CONTACT_PHONE){
                tvTitle.setText("已复制电话");
                tvCopy.setText("拨打电话>");
            }
        }
    }
    @OnClick({R.id.iv_close, R.id.tv_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_copy:
                if (contact==null||contact.trim().isEmpty()){
                    if (flag==1){
                        PersonalResumeActivity.start(getContext());
                    }
                }else {
                    copyClick();
                }


                dismiss();
                break;
        }
    }

    public void copyClick(){
        if (contactType == Constants.CONTACT_QQ){
            joinQQ(getContext(),contact);
        }else if (contactType == Constants.CONTACT_WECHAT){
            goWeChatApi(getContext());
        }else if (contactType == Constants.CONTACT_PHONE){
            callPhone(getContext(),contact);
        }
    }
    /**
     * 跳转到微信
     */
    private void goWeChatApi(Context context){
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtils.showShortToast("检查到您手机没有安装微信，请安装后使用该功能");
        }
    }
    /**
     * 跳转QQ聊天界面
     */
    public void joinQQ(Context context,String qqNum) {
        try {
            //第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNum;//uin是发送过去的qq号码
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShortToast("检查到您手机没有安装QQ，请安装后使用该功能");
        }
    }
    /**
     * 跳转到QQ
     */
    private void goQQApi(Context context){
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mobileqq","com.tencent.mobileqq.activity.HomeActivity");
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
