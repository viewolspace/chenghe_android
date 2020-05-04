package com.parttime.diandi.view.dilaog;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;


import com.parttime.base.dialog.BaseDialog;
import com.parttime.diandi.R;

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
//        tvContent.setText(Html.fromHtml("&emsp;&emsp;在您使用优顾炒股APP之前，为了更好地了解我们是如何保护用户的个人信息的，请您仔细阅读" +
//                "<a href='http://www.youguu.com/opms/html/article/32/2016/0628/2734.html'>《服务协议》</a> " + "和" +
//                "<a href='http://www.youguu.com/opms/html/article/32/2019/1108/2896.html'>《隐私政策》</a> "
//                + "的全部条款。您同意并接受全部条款后，优顾炒股将为您提供浏览、搜索等基础服务，可能会收集和使用您的敏感信息。"));
//        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
//
//        CharSequence text = tvContent.getText();
//        if (text instanceof Spannable) {
//            int end = text.length();
//            Spannable sp = (Spannable) tvContent.getText();
//            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
//            SpannableStringBuilder style = new SpannableStringBuilder(text);
//            style.clearSpans();// should clear old spans
//
//            // 循环把链接发过去
//            for (URLSpan url : urls) {
//                AgreementURLSpan myURLSpan = new AgreementURLSpan(getContext(), url.getURL());
//                style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//            }
//            tvContent.setText(style);
//        }
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
//            if (mUrl.endsWith("2734.html")) {
//                WebViewUI.start(widget.getContext(), mUrl, "优顾服务协议");
//            } else {
//                WebViewUI.start(widget.getContext(), mUrl, "优顾隐私协议");
//            }

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
