package com.parttime.sunshine;

import com.parttime.base.base.App;
import com.reyun.tracking.sdk.Tracking;
import com.umeng.analytics.AnalyticsConfig;
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
        // 支持在子进程中统计自定义事件
        UMConfigure.setProcessEvent(true);
        Tracking.initWithKeyAndChannelId(this,"73f04047428541b21d99d81732a422d1", AnalyticsConfig.getChannel(this));
        //UMConfigure.setLogEnabled(true);
    }
}
