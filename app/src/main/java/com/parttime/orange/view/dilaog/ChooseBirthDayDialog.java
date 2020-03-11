package com.parttime.orange.view.dilaog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.parttime.base.dialog.BaseDialog;
import com.parttime.orange.R;
import com.parttime.orange.view.CalendarSelectedView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zdy On 2019/7/26.
 */
public class ChooseBirthDayDialog extends BaseDialog {
    @BindView(R.id.tv_dialog_ok)
    TextView tv_dialog_ok;
    @BindView(R.id.calendar_selected)
    CalendarSelectedView calendar_selected;
    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void onConfirmClick(String year,String month,String day);
        void onCancelClick();
    }

    public ChooseBirthDayDialog(Context context) {
        super(context);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.dialog_choose_date;
    }

    @Override
    protected void initView(View root) {

    }

    @OnClick({R.id.tv_dialog_ok,R.id.tv_dialog_cancel})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_dialog_ok:
                if (clickListener!=null){
                    clickListener.onConfirmClick(calendar_selected.getYear()
                            ,calendar_selected.getMonth(),calendar_selected.getDay());
                }
                dismiss();
                break;
            case R.id.tv_dialog_cancel:
                dismiss();
                break;
        }
    }
}
