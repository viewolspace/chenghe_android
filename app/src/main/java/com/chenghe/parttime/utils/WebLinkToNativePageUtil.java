package com.chenghe.parttime.utils;

import android.content.Context;
import android.net.Uri;

import com.chenghe.parttime.activity.CategoryPartTimeActivity;
import com.chenghe.parttime.activity.JobDetailActivity;
import com.chenghe.parttime.activity.WebActivity;

/**
 * Created by zdy On 2019/7/27.
 */
public class WebLinkToNativePageUtil {

    public static void dealWithUrl(Context context , String url){
        if (StringUtil.isEmpty(url)) {
            return ;
        }
        Uri uri = Uri.parse(url);
        String path = uri.getPath();
        if (path == null) {
            return ;
        }
        if (url.startsWith("http://")|| url.startsWith("https://")){
            WebActivity.start(context,url);
        }else if (url.startsWith("jzq://")){
            if (path.startsWith("/detail")){
                String id = uri.getQueryParameter("id");
                if (id!=null){
                    try {
                        JobDetailActivity.start(context,Integer.valueOf(id));
                        CommonUtil.adState(id);
                    }catch (Exception e){

                    }
                }
            }else if (path.startsWith("/list")){
                String categoryId = uri.getQueryParameter("categoryId");
                CategoryPartTimeActivity.start(context,categoryId,"");
            }
        }
    }
}
