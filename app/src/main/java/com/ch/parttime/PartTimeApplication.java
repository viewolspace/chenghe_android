package com.ch.parttime;

import com.ch.base.base.App;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/8/14 12:54
 * @describe ：
 */
public class PartTimeApplication extends App {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE, null);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        UMConfigure.setProcessEvent(true);
    }
}