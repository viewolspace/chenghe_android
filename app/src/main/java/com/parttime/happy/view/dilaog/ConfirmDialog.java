package com.parttime.happy.view.dilaog;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.parttime.base.dialog.BaseDialog;
import com.parttime.happy.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/2/28 15:39
 * @describe ：
 */
public class ConfirmDialog extends BaseDialog {
    @BindView(R.id.tv_dialog_title)
    TextView tvDialogTitle;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private OnDialogListener onDialogListener;
    public ConfirmDialog(Context context) {
        super(context);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.dialog_confirm;
    }

    @Override
    protected void initView(View root) {
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        layoutParams.width = (int) (getScreenWidth(getContext()) * 0.8);
        root.setLayoutParams(layoutParams);
        getWindow().setGravity(Gravity.CENTER);
    }
    @OnClick({R.id.tv_cancel})
    public void onCancelClicked(View view) {
        dismiss();
    }
    @OnClick({ R.id.tv_confirm})
    public void onConfirmClicked(View view) {
        dismiss();
        if (onDialogListener != null) {
            onDialogListener.onConfirmClickListener();
        }
    }

    public void setTitle(String title) {
        tvDialogTitle.setText(title);
    }

    public void setTitle(CharSequence title) {
        tvDialogTitle.setText(title);
    }

    public void setConfirmText(String text) {
        tvConfirm.setText(text);
    }

    public void setCancelText(String text) {
        tvCancel.setText(text);
    }

    public void setConfirmTextColor(@ColorInt int color) {
        tvConfirm.setTextColor(color);
    }

    public void setCancelTextColor(@ColorInt int color) {
        tvCancel.setTextColor(color);
    }

    public void setCancelTextVisibility(boolean visibility) {
        if (visibility){
            tvCancel.setVisibility(View.VISIBLE);
        }else {
            tvCancel.setVisibility(View.GONE);
        }
    }

    public void setTitleFontSize(int size) {
        tvDialogTitle.setTextSize(size);
    }

    public interface OnDialogListener {
        void onConfirmClickListener();
    }

    public void setOnDialogListener(OnDialogListener onDialogListener) {
        this.onDialogListener = onDialogListener;
    }
    /**
     * 获取屏幕的宽度（单位：px）
     *
     * @return 屏幕宽px
     */
    public  int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        return dm.widthPixels;
    }
}
