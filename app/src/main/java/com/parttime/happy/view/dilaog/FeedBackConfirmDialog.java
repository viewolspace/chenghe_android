package com.parttime.happy.view.dilaog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.parttime.base.dialog.BaseDialog;
import com.parttime.happy.R;

import butterknife.BindView;

/**
 * Created by zdy On 2020/1/6.
 */
public class FeedBackConfirmDialog extends BaseDialog {
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;

    private ClickListener clickListener;

    public ClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void  onClick();
    }
    public FeedBackConfirmDialog(Context context) {
        super(context);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.dialog_feedback_confirm;
    }

    @Override
    protected void initView(View root) {
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (clickListener!=null){
                   clickListener.onClick();
               }
            }
        });
    }
}
