package com.parttime.orange;

import android.util.Log;

import com.parttime.base.base.App;
import com.reyun.tracking.sdk.Tracking;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.mezu.MeizuRegister;
import org.android.agoo.oppo.OppoRegister;
import org.android.agoo.vivo.VivoRegister;
import org.android.agoo.xiaomi.MiPushRegistar;

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
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(this);
//        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER); //服务端控制声音


        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i(TAG,"注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e(TAG,"注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });


        /**
         * 初始化厂商通道
         */
        //小米通道
        MiPushRegistar.register(this, "填写您在小米后台APP对应的xiaomi id", "填写您在小米后台APP对应的xiaomi key");
        //华为通道，注意华为通道的初始化参数在minifest中配置
        HuaWeiRegister.register(this);
        //魅族通道
        MeizuRegister.register(this, "填写您在魅族后台APP对应的app id", "填写您在魅族后台APP对应的app key");
        //OPPO通道
        OppoRegister.register(this, "填写您在OPPO后台APP对应的app key", "填写您在魅族后台APP对应的app secret");
        //VIVO 通道，注意VIVO通道的初始化参数在minifest中配置
        VivoRegister.register(this);
    }
}
