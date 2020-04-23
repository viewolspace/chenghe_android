package com.parttime.happy.utils;

import android.content.Context;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zdy On 2019/12/4.
 */
public class MobEventHelper {
    /**
     * 友盟统计
     */
    public static void statistics(Context context, String key,String event) {
        Map<String, Object> eventMap = new HashMap<>();
        eventMap.put("event",event);
        MobclickAgent.onEventObject(context, key, eventMap);
        Log.d("友盟统计","key:"+key+"map");
    }
}
