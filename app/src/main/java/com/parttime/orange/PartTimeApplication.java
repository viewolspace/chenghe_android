package com.parttime.orange;

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
        Tracking.initWithKeyAndChannelId(this,"6b0296af8f3e61c0f8fe93dc29cd299c", AnalyticsConfig.getChannel(this));
        //UMConfigure.setLogEnabled(true);
    }
}
