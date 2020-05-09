package com.ch.parttime.view.dilaog;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ch.base.constants.Constants;
import com.ch.base.dialog.BaseDialog;
import com.ch.base.util.SpUtil;
import com.ch.base.util.ToastUtils;
import com.ch.parttime.R;
import com.ch.parttime.activity.PersonalResumeActivity;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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
    private long customerId;

    public CopyContactDialog(Context context) {
        super(context);
    }

    public CopyContactDialog(Context context, int contactType, String contact, long customerId,int flag) {
        super(context);
        this.contactType = contactType;
        this.contact = contact;
        this.flag = flag;
        this.customerId = customerId;
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
        if (isInOneDay()){
            String mapString = SpUtil.getString(getContext(),"contactMap",null);
            HashMap<String,String> contactMap = null;
            if (mapString!=null){
                contactMap = new Gson().fromJson(mapString,HashMap.class);
            }
            String copyCotact = contact;
            if (contactType == Constants.CONTACT_QQ){
                if (contactMap!=null&&contactMap.get(customerId+"QQ")!=null){
                    copyCotact = contactMap.get(customerId+"QQ");
                }
                joinQQ(getContext(),copyCotact);
            }else if (contactType == Constants.CONTACT_WECHAT){
                if (contactMap!=null&&contactMap.get(customerId+"WX")!=null){
                    copyCotact = contactMap.get(customerId+"WX");
                }
                goWeChatApi(getContext());
            }else if (contactType == Constants.CONTACT_PHONE){
                callPhone(getContext(),copyCotact);
            }
            copyText(copyCotact);
        }else {
            if (contactType == Constants.CONTACT_QQ){
                HashMap<String,String> contactMap = new HashMap<>();
                contactMap.put(customerId+"QQ",contact);
                SpUtil.putString(getContext(),"contactMap",new Gson().toJson(contactMap));
                joinQQ(getContext(),contact);
            }else if (contactType == Constants.CONTACT_WECHAT){
                HashMap<String,String> contactMap = new HashMap<>();
                contactMap.put(customerId+"WX",contact);
                SpUtil.putString(getContext(),"contactMap",new Gson().toJson(contactMap));
                goWeChatApi(getContext());
            }else if (contactType == Constants.CONTACT_PHONE){
                callPhone(getContext(),contact);
            }
            copyText(contact);
        }

    }

    public void copyRealContact(){
        if (isInOneDay()){
            String mapString = SpUtil.getString(getContext(),"contactMap",null);
            HashMap<String,String> contactMap = null;
            if (mapString!=null){
                contactMap = new Gson().fromJson(mapString,HashMap.class);
            }
            String copyContact = contact;
            if (contactType == Constants.CONTACT_QQ){
                if (contactMap!=null&&contactMap.get(customerId+"QQ")!=null){
                    copyContact = contactMap.get(customerId+"QQ");
                }
            }else if (contactType == Constants.CONTACT_WECHAT){
                if (contactMap!=null&&contactMap.get(customerId+"WX")!=null){
                    copyContact = contactMap.get(customerId+"WX");
                }
            }
            copyText(copyContact);
        }else {
            if (contactType == Constants.CONTACT_QQ){
                HashMap<String,String> contactMap = new HashMap<>();
                contactMap.put(customerId+"QQ",contact);
                SpUtil.putString(getContext(),"contactMap",new Gson().toJson(contactMap));
            }else if (contactType == Constants.CONTACT_WECHAT){
                HashMap<String,String> contactMap = new HashMap<>();
                contactMap.put(customerId+"WX",contact);
                SpUtil.putString(getContext(),"contactMap",new Gson().toJson(contactMap));
            }
            copyText(contact);
        }
    }
    public void copyText(String contact){
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("contact", contact);
// 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }
    public boolean isInOneDay(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date(System.currentTimeMillis());
        String currentDate = simpleDateFormat.format(date);
        String lastDate = SpUtil.getString(getContext(),"lastCopyTime","");
        if (currentDate.equals(lastDate)){
            return true;
        }else {
            SpUtil.putString(getContext(),"lastCopyTime",currentDate);
            return false;
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
