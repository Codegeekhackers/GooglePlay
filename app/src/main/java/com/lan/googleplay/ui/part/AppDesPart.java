package com.lan.googleplay.ui.part;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lan.googleplay.R;
import com.lan.googleplay.bean.AppInfo;
import com.lan.googleplay.global.GooglePlayApplication;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewPropertyAnimator;

/**
 * Created by X_S on 2016/12/26.
 */

public class AppDesPart extends AppBasePart<AppInfo> implements View.OnClickListener {
    private int maxHeight;
    private int minHeight;
    private ScrollView scrollview;
    private View view;
    private TextView tv_des;
    private TextView tv_author;
    private ImageView iv_des_arrow;
    private boolean isExpended = false;

    public AppDesPart(ScrollView scrollview) {
        this.scrollview = scrollview;
        initView();

    }

    private void initView() {
        view = View.inflate(GooglePlayApplication.getContext(), R.layout.layout_detail_app_des, null);
        tv_des = (TextView) view.findViewById(R.id.tv_des);
        tv_author = (TextView) view.findViewById(R.id.tv_author);
        iv_des_arrow = (ImageView) view.findViewById(R.id.iv_des_arrow);
        view.setOnClickListener(this);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setData(AppInfo appInfo) {
        tv_des.setText(appInfo.getDes());
//        tv_des.setMaxLines(5);

        tv_author.setText("作者：" + appInfo.getAuthor());
        tv_des.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                tv_des.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                minHeight = tv_des.getHeight();
                tv_des.setMaxLines(Integer.MAX_VALUE);
                tv_des.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        tv_des.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        minHeight = tv_des.getHeight();

                        tv_des.getLayoutParams().height = minHeight;
                        tv_des.requestLayout();


                    }
                });
            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view == this.view) {
            ValueAnimator valueAnimator;
            if (isExpended) {
                valueAnimator = ValueAnimator.ofInt(maxHeight, minHeight);
            } else {
                valueAnimator = ValueAnimator.ofInt(minHeight, maxHeight);
            }
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    int animatorValue = (int) animator.getAnimatedValue();
                    tv_des.getLayoutParams().height = animatorValue;
                    tv_des.requestLayout();

                    if (!isExpended) {
                        //?
                        scrollview.smoothScrollBy(0, 1000);
                    }
                }
            });
            valueAnimator.setDuration(500);
            valueAnimator.addListener(new AppDesAnimatorListener());
            valueAnimator.start();
        }

    }

    private class AppDesAnimatorListener implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            isExpended = !isExpended;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            ViewPropertyAnimator.animate(iv_des_arrow).rotationBy(180).setDuration(500).start();
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
