package com.parttime.diandi.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.parttime.base.base.App;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtils {

    private static final String TAG = "PhoneUtils";

    /**
     * 得到sd卡的路径
     */
    public static String getSDPath() {
        File path = null;
        if (PhoneUtils.SDCardExist()) {
            path = Environment.getExternalStorageDirectory();// 获取跟目录
        } else {
            StringBuilder sbPhone = new StringBuilder();
            sbPhone.append(Environment.getDataDirectory());
            sbPhone.append("/data/com.jhss.youguu");
            return sbPhone.toString();
        }
        return path.toString();
    }

    public static boolean SDCardExist() {
        if (!"".equals(Environment.getExternalStorageState())
                && Environment.getExternalStorageState() != null) {
            boolean sdCardExist =
                    Environment.getExternalStorageState()
                            .equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
            return sdCardExist;
        } else {
            return false;
        }
    }

    /**
     * SD卡是否加载中
     *
     * @return booolean
     */
    public static boolean isSDMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static boolean isSDFreeSpaceLarger(final long freeSpace) {
        boolean flag = false;
        if (PhoneUtils.isSDMounted()) {
            StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long blockCount = statfs.getBlockCount();
            long availableBlocks = statfs.getAvailableBlocks();
            if (blockCount > 0L && blockCount - availableBlocks >= 0L
                    && (long) statfs.getBlockSize() * (long) statfs.getFreeBlocks() >= freeSpace) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取imei地址 需要有电话功能设备 获取不是很可靠
     */
    public static String getPhoneImei(Activity context) {
        TelephonyManager mTm =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.READ_PHONE_STATE},0);
            return "NAN";
        }
        String imei = mTm.getDeviceId();
        return imei == null ? "NAN" : imei;
    }
    /**
     * 获取imei地址 需要有电话功能设备 获取不是很可靠
     */
    public static String getPhoneImei() {
        TelephonyManager mTm =
                (TelephonyManager) App.getInstant().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(App.getInstant(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "NAN";
        }
        String imei = mTm.getDeviceId();
        return imei == null ? "NAN" : imei;
    }

    /**
     * 获取android_id(注:当用户刷机或恢复出厂 之后android_id会改变)
     */
    public static String getAndroidID() {
        String android_id =
                Secure.getString(App.getInstant().getContentResolver(), Secure.ANDROID_ID);
        return android_id;
    }

    public static String getUUID(Activity context) {
        final TelephonyManager tm = (TelephonyManager) App.getInstant().getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.READ_PHONE_STATE},0);
            tmDevice = "NAN";
        }else {
            tmDevice = "" + tm.getDeviceId();
        }

        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + Secure.getString(App.getInstant().getContentResolver(), Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return  deviceUuid.toString();
    }
    public static String getUUID() {
        final TelephonyManager tm = (TelephonyManager) App.getInstant().getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        if (ActivityCompat.checkSelfPermission( App.getInstant(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            tmDevice = "";
        }else {
            tmDevice = "" + tm.getDeviceId();
        }

        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + Secure.getString(App.getInstant().getContentResolver(), Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return  deviceUuid.toString();
    }
    public static String getMacAddress() {

        String macAddress = null;

        WifiManager wifiManager =
                (WifiManager) App.getInstant().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiManager ? null : wifiManager.getConnectionInfo());

        macAddress = info.getMacAddress();
        return macAddress;
    }

    /**
     * 通过linux命令获取mac地址 若wifi正在使用 则无法获取
     */
    public static String getMacByLinuxCode() throws IOException {

        String macSerial = null;
        String str = "";
        LineNumberReader input = null;
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            input = new LineNumberReader(new InputStreamReader(pp.getInputStream()));
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            Log.e(PhoneUtils.TAG, "", ex);
            macSerial = "";
        } finally {
            input.close();
        }
        return macSerial;
    }

    /**
     * 获取系统版本号
     */
    public static String getSdkCode() {

        return Build.VERSION.RELEASE;
    }

    /**
     * 获取屏幕分辨率
     */
    public static String getScreenSize() {
        Display display =
                ((WindowManager) App.getInstant().getSystemService(Context.WINDOW_SERVICE))
                        .getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm.heightPixels + "*" + dm.widthPixels;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth() {
        Display display =
                ((WindowManager) App.getInstant().getSystemService(Context.WINDOW_SERVICE))
                        .getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenHeight() {
        Display display =
                ((WindowManager) App.getInstant().getSystemService(Context.WINDOW_SERVICE))
                        .getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取屏幕分辨率
     *
     * @return [0]height [1]width
     */
    public static int[] getScreenSizeArray(final Context ctx) {
        Display display =
                ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return new int[]{dm.heightPixels, dm.widthPixels};
    }

    /**
     * 获取手机型号
     */
    public static String getPhoneUA() {

        return Build.MODEL == null ? "NAN" : Build.MODEL;
    }

    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     * @author zuolongsnail
     */
    public static boolean isAppInstalledByQuery(final String packageName) {

        PackageInfo packageInfo;
        try {
            packageInfo = App.getInstant().getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
            Log.e(PhoneUtils.TAG, e.getMessage());
        }
        return packageInfo != null;
    }

    public static boolean isAppInstalledByCompare(final String packageName) {

        PackageManager manager = App.getInstant().getPackageManager();
        List<PackageInfo> pkgList = manager.getInstalledPackages(0);
        for (int i = 0; i < pkgList.size(); i++) {
            PackageInfo pI = pkgList.get(i);
            if (pI.packageName.equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否升级 判断版本号
     */
    public static boolean isUpdateApp(final String packName, final int versionCode) {

        PackageInfo packageInfo;
        try {
            packageInfo = App.getInstant().getPackageManager().getPackageInfo(packName, 0);
        } catch (Exception e) {
            packageInfo = null;
            Log.e(PhoneUtils.TAG, e.getMessage());
        }
        if (packageInfo != null) {
            return packageInfo.versionCode < versionCode;
        } else {
            return false;
        }
    }

    /**
     * 获取上网方式
     */
    public static String getNetType() {

        String netType = "";
        NetworkInfo info = PhoneUtils.getActiveNetworkInfo();
        if (null != info && info.isAvailable()) {
            netType = info.getTypeName();
        }
        return netType;
    }

    /**
     * 是否升级 判断版本号
     */
    public static String getVerionName() {

        PackageInfo packageInfo;
        try {
            packageInfo = App.getInstant().getPackageManager()
                    .getPackageInfo("com.jhss.youguu", 0);
        } catch (Exception e) {
            packageInfo = null;
            Log.e(PhoneUtils.TAG, e.getMessage());
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        } else {
            return "";
        }
    }

    /**
     * 获取版本号
     */
    public static int getVerionCode() {

        PackageInfo packageInfo;
        try {
            packageInfo = App.getInstant().getPackageManager()
                    .getPackageInfo("com.jhss.youguu", 0);
        } catch (Exception e) {
            packageInfo = null;
            Log.e(PhoneUtils.TAG, e.getMessage());
        }
        if (packageInfo != null) {
            return packageInfo.versionCode;
        } else {
            return -1;
        }
    }

    public static boolean isWifi() {
        final NetworkInfo networkInfo = PhoneUtils.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            return false;
        }
        // NetworkInfo不为null开始判断是网络类型
        int netType = networkInfo.getType();
        if (netType == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 判断网络连接是否可用
     */

    public static boolean isNetAvailable() {
        boolean isAvailable = false;

        NetworkInfo mNetworkInfo = PhoneUtils.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return isAvailable;
    }

    public static NetworkInfo getActiveNetworkInfo() {
        try {
            ConnectivityManager mConnectivityManager =
                    (ConnectivityManager) App.getInstant()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return mNetworkInfo;
        } catch (NullPointerException e) {
            // 注意在部分手机rom中会出现如下异常：
            // java.lang.NullPointerException
            // at android.os.Parcel.readException(Parcel.java:1431)
            // at android.os.Parcel.readException(Parcel.java:1379)
            // at
            // android.net.IConnectivityManager$Stub$Proxy.getActiveNetworkInfo(IConnectivityManager.java:688)
            // at
            // android.net.ConnectivityManager.getActiveNetworkInfo(ConnectivityManager.java:460)
            return null;
        }
    }

    /**
     * 获取运营商信息
     */
    public static String getNetOperatorInfo() {
        String netExtraInfo = "";
        TelephonyManager mTm =
                (TelephonyManager) App.getInstant().getSystemService(Context.TELEPHONY_SERVICE);
        if (mTm.getSimState() == TelephonyManager.SIM_STATE_READY) {
            netExtraInfo = mTm.getSimOperator();
            if (null != netExtraInfo) {
                if (netExtraInfo.equals("46000") || netExtraInfo.equals("46002")
                        || netExtraInfo.equals("46007")) {
                    // 中国移动
                    netExtraInfo = "中国移动";
                } else if (netExtraInfo.equals("46001")) {

                    // 中国联通
                    netExtraInfo = "中国联通";
                } else if (netExtraInfo.equals("46003")) {

                    // 中国电信
                    netExtraInfo = "中国电信";
                } else {
                    netExtraInfo = "其他";
                }
            }
        }
        return netExtraInfo;
    }

    // dip转像素
    public static int dipToPixels(final float dip) {
        final float SCALE = App.getInstant().getResources().getDisplayMetrics().density;
        float valueDips = dip;
        int valuePixels = (int) (valueDips * SCALE + 0.5f);
        return valuePixels;
    }

    // 像素转dip
    public static float pixelsToDip(final int pixels) {
        final float SCALE = App.getInstant().getResources().getDisplayMetrics().density;
        float dips = pixels / SCALE;
        return dips;
    }

    public static float getDensity() {
        return App.getInstant().getResources().getDisplayMetrics().density;
    }

    /**
     * 获取屏幕分辨率
     */
    public static String getScreenSizeD_H() {
        WindowManager wm =
                (WindowManager) App.getInstant().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels + "*" + dm.heightPixels;
    }

    private static List<RunningAppProcessInfo> getAllProcess() {
        ActivityManager actvityManager =
                (ActivityManager) App.getInstant().getSystemService(Context.ACTIVITY_SERVICE);
        return actvityManager.getRunningAppProcesses();
    }

    public static String getCurrentRunningProcessName(final int pid) {
        List<RunningAppProcessInfo> aProcess = PhoneUtils.getAllProcess();
        String curPName = null;
        for (RunningAppProcessInfo procInfo : aProcess) {
            if (pid == procInfo.pid) {
                curPName = procInfo.processName;
            }
        }
        return curPName;
    }


    /**
     * 判断手机号的正则
     */
    public static boolean isMobileNO(final String mobiles) {
        Pattern p =
                Pattern.compile(
                        "\\d{11}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 弹出和隐藏键盘
     */
    public static void showKeyBoard(final boolean showKeyBoard, final EditText mEdintText,
                                    final Context mContext) {
        InputMethodManager inp =
                (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (showKeyBoard) {
            // 显示键盘
            inp.showSoftInput(mEdintText, InputMethodManager.RESULT_SHOWN);
            inp.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY);
        } else {
            // 隐藏键盘
            inp.hideSoftInputFromWindow(mEdintText.getWindowToken(), 0);
        }
    }

    /**
     * EditText是否显示删除按钮
     */
    public static void showDellButton(final EditText mEditText, final ImageView dell) {
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(final CharSequence arg0, final int arg1, final int arg2,
                                      final int arg3) {

            }

            @Override
            public void beforeTextChanged(final CharSequence arg0, final int arg1, final int arg2,
                                          final int arg3) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (s.length() == 0) {
                    dell.setVisibility(View.GONE);
                } else {
                    dell.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 更改手机号中间四位
     */
    public static String hidePhoneNum(final String phoneNum) {
        if (!TextUtils.isEmpty(phoneNum)) {
            StringBuffer sb = new StringBuffer(phoneNum);
            sb.replace(3, 7, "****");
            String num = sb.toString();
            return num;
        } else {
            return "";
        }
    }




    /**
     * Bitmap转成string(base64)
     *
     * @param bitmap bitmap
     * @return base64 string
     */
    public static String convertBitmapToString(Bitmap bitmap) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] imgByte = baos.toByteArray();
            return Base64.encodeToString(imgByte, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * string(base64)转成Bitmap
     */
    public static Bitmap convertStringToBitmap(String str) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(str, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }





    /**
     * 判断button连续点击
     */
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static Activity getActivityFromView(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

}
