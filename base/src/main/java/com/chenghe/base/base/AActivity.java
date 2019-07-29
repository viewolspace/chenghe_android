package com.chenghe.base.base;


import com.chenghe.base.dialog.LoginProgressDialog;
import com.trello.navi2.component.support.NaviAppCompatActivity;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/18 15:47
 * @describe ：
 */
public abstract class AActivity extends NaviAppCompatActivity {

    protected LoginProgressDialog progressDialog;

    protected final LifecycleProvider<ActivityEvent> mProvider
            = NaviLifecycle.createActivityLifecycleProvider(this);

    /**
     * 显示加载进度dialog
     */
    public void showProgressDialog(String text) {
        if (progressDialog == null){
            progressDialog = new LoginProgressDialog(AActivity.this);
        }
        if (progressDialog.isShowing()) return;
        progressDialog.show(text);
    }

    /**
     * 隐藏加载进度dialog
     */
    public void hideProgressDialog() {
        if (progressDialog == null){
            return;
        }
        if (!progressDialog.isShowing()) return;
        progressDialog.dismiss();
    }

}
