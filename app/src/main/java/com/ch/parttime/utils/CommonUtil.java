package com.ch.parttime.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.ch.base.retrofit.ApiService;
import com.ch.base.retrofit.RetrofitServiceCreator;
import com.ch.base.rx.RxThrowableConsumer;
import com.ch.base.rx.RxUtils;
import com.ch.base.util.ToastUtils;

import java.util.HashMap;

/**
 * Created by zdy On 2019/7/27.
 */
public class CommonUtil {

    public static void copyString(Context context ,String content){
        ClipboardManager clipboardmanager = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text",content);
        if (clipboardmanager!=null){
            clipboardmanager.setPrimaryClip(clip);
            ToastUtils.showShortToast("已复制到剪贴板");
        }
    }

    public static void adState(String adId){
        ApiService apiService = RetrofitServiceCreator.createService(ApiService.class);
        HashMap<String, String> data = new HashMap<>();
        data.put("adId",adId);
        apiService.adStat(data)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(baseBean -> {

                },new RxThrowableConsumer());
    }
}
