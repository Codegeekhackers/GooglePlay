package com.lan.googleplay.ui.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.icu.text.Normalizer2;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by X_S on 2016/12/25.
 */

public class RationImageView extends ImageView {
    private String xmlns = "http://schemas.android.com/apk/res-auto";
    private float ratio;

    public RationImageView(Context context) {
        super(context);
    }

    public RationImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }



    public RationImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RationImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs);
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    private void initAttrs(AttributeSet attrs) {
        ratio = attrs.getAttributeFloatValue(xmlns, "ratio", 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if(ratio!=0){
            int height = (int) (width / ratio);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
