package useless.danmu;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/6/13 11:45
 * @describe ：
 */
public class FixTextView extends android.support.v7.widget.AppCompatTextView {
    public FixTextView(Context context) {
        super(context);
    }

    public FixTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FixTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
