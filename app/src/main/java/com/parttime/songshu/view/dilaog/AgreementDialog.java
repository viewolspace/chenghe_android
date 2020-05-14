package com.parttime.songshu.view.dilaog;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;


import com.parttime.base.dialog.BaseDialog;
import com.parttime.songshu.R;
import com.parttime.songshu.activity.LoginActivity;
import com.parttime.songshu.activity.WebActivity;
import com.parttime.songshu.utils.WebLinkToNativePageUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/1/3 14:50
 * @describe ：
 */
public class AgreementDialog extends BaseDialog {
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public interface ClickListener {
        void onConfirmClick();
        void onCancelClick();
    }

    public AgreementDialog(Context context) {
        super(context);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.dialog_agreement;
    }

    @Override
    protected void initView(View root) {
        setCanceledOnTouchOutside(false);
        tvContent.setText(Html.fromHtml("&emsp;&emsp;感谢您下载松鼠兼职！" +
                "在您开始使用本软件前，请您务必仔细审慎阅读、充分理解软件许可及服务协议和隐私政策各条款，包括但不限于用户注意事项、用户行为以及为了向你提供服务而收集、使用、存储你个人信息的情况等。" +
                "您可以阅读<a href='http://www.chengheed.com/xy/songshujz_yhxy.htm'>《松鼠兼职用户协议》</a> 与<a href='http://www.chengheed.com/xy/songshujz_yszc.htm'>《松鼠兼职隐私政策》</a> 了解详细信息。" +
                "点击同意即表示您已阅读并同意<a href='http://www.chengheed.com/xy/songshujz_yhxy.htm'>《松鼠兼职用户协议》</a> 与<a href='http://www.chengheed.com/xy/songshujz_yszc.htm'>《松鼠兼职隐私政策》</a> 。" ));
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());

        CharSequence text = tvContent.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) tvContent.getText();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.clearSpans();// should clear old spans

            // 循环把链接发过去
            for (URLSpan url : urls) {
                AgreementURLSpan myURLSpan = new AgreementURLSpan(getContext(), url.getURL());
                style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            tvContent.setText(style);
        }
    }

    public static class AgreementURLSpan extends ClickableSpan {

        private String mUrl;
        private Context context;

        public AgreementURLSpan(Context context, String string) {
            mUrl = string;
            this.context = context;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.parseColor("#0873d2"));
//            ds.setUnderlineText(false);
        }

        /**
         * 对各种链接事件进行自己的处理
         */
        @Override
        public void onClick(View widget) {
            WebActivity.start(widget.getContext(),mUrl);
        }
    }
    @OnClick({R.id.tv_cancel, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                if (clickListener!=null){
                    clickListener.onCancelClick();
                }
                dismiss();
                break;
            case R.id.tv_confirm:
                if (clickListener!=null){
                    clickListener.onConfirmClick();
                }
                dismiss();
                break;
        }
    }

}
