package com.lan.googleplay.ui.part;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lan.googleplay.R;
import com.lan.googleplay.bean.AppInfo;
import com.lan.googleplay.bean.SafeInfo;
import com.lan.googleplay.global.GooglePlayApplication;
import com.lan.googleplay.global.ImageLoaderOptions;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.uitls.LogUtil;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by X_S on 2016/12/26.
 */

public class AppSafePart extends AppBasePart<AppInfo> implements View.OnClickListener {

    private View view;
    private RelativeLayout rl_safe;
    private ImageView iv_safe_image1;
    private ImageView iv_safe_image2;
    private ImageView iv_safe_image3;
    private ImageView iv_safe_arrow;
    private LinearLayout ll_safe_des_container;
    private ImageView iv_safe_des1;
    private TextView tv_safe_des1;
    private LinearLayout ll_safe_des2;
    private ImageView iv_safe_des2;
    private TextView tv_safe_des2;
    private LinearLayout ll_safe_des3;
    private ImageView iv_safe_des3;
    private TextView tv_safe_des3;
    private boolean isOpenSafe = false;
    private int maxHeight;

    public AppSafePart() {
        initView();
    }

    private void initView() {
        view = View.inflate(GooglePlayApplication.getContext(), R.layout.layout_detail_app_safe, null);
        rl_safe = (RelativeLayout) view.findViewById(R.id.rl_safe);
        iv_safe_image1 = (ImageView) view.findViewById(R.id.iv_safe_image1);
        iv_safe_image2 = (ImageView) view.findViewById(R.id.iv_safe_image2);
        iv_safe_image3 = (ImageView) view.findViewById(R.id.iv_safe_image3);
        iv_safe_arrow = (ImageView) view.findViewById(R.id.iv_safe_arrow);
        ll_safe_des_container = (LinearLayout) view.findViewById(R.id.ll_safe_des_container);
        iv_safe_des1 = (ImageView) view.findViewById(R.id.iv_safe_des1);
        tv_safe_des1 = (TextView) view.findViewById(R.id.tv_safe_des1);
        ll_safe_des2 = (LinearLayout) view.findViewById(R.id.ll_safe_des2);
        iv_safe_des2 = (ImageView) view.findViewById(R.id.iv_safe_des2);
        tv_safe_des2 = (TextView) view.findViewById(R.id.tv_safe_des2);
        ll_safe_des3 = (LinearLayout) view.findViewById(R.id.ll_safe_des3);
        iv_safe_des3 = (ImageView) view.findViewById(R.id.iv_safe_des3);
        tv_safe_des3 = (TextView) view.findViewById(R.id.tv_safe_des3);
        rl_safe.setOnClickListener(this);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setData(AppInfo appInfo) {

        List<SafeInfo> safeList = appInfo.getSafe();
        SafeInfo safeInfo1 = safeList.get(0);
        ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX + safeInfo1.getSafeUrl(), iv_safe_image1, ImageLoaderOptions.list_options);
        ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX + safeInfo1.getSafeDesUrl(), iv_safe_des1, ImageLoaderOptions.list_options);

        tv_safe_des1.setText(safeInfo1.getSafeDes());

        if (safeList.size() > 1) {
            SafeInfo safeInfo2 = safeList.get(1);

            ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX + safeInfo2.getSafeUrl(), iv_safe_image2, ImageLoaderOptions.list_options);
            ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX + safeInfo2.getSafeDesUrl(), iv_safe_des2, ImageLoaderOptions.list_options);
            tv_safe_des2.setText(safeInfo2.getSafeDes());

        } else {
            ll_safe_des2.setVisibility(View.GONE);//必须是GONE 不占空间
        }
        if (safeList.size() > 2) {
            SafeInfo safeInfo3 = safeList.get(2);
            ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX + safeInfo3.getSafeUrl(), iv_safe_image3, ImageLoaderOptions.list_options);
            ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX + safeInfo3.getSafeDesUrl(), iv_safe_des3, ImageLoaderOptions.list_options);
            tv_safe_des3.setText(safeInfo3.getSafeDes());
        } else {
            ll_safe_des3.setVisibility(View.GONE);
        }

        ll_safe_des_container.measure(0,0);
        maxHeight = ll_safe_des_container.getMeasuredHeight();
        ll_safe_des_container.getLayoutParams().height = 0;
        ll_safe_des_container.requestLayout();


        ViewHelper.setTranslationX(rl_safe, -rl_safe.getMeasuredWidth());

        ViewPropertyAnimator.animate(rl_safe)
                .translationXBy(rl_safe.getMeasuredWidth())
                .setInterpolator(new OvershootInterpolator(4))/*动画的速度*/
                .setDuration(500)
                .setStartDelay(500)
                .start();

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.rl_safe){
            ValueAnimator valueAnimator;
            if(isOpenSafe){
                valueAnimator = ValueAnimator.ofInt(maxHeight, 0);
            }else {
                valueAnimator= ValueAnimator.ofInt(0, maxHeight);
            }
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int animatedValue = (int) animation.getAnimatedValue();

                    ll_safe_des_container.getLayoutParams().height = animatedValue;
                    ll_safe_des_container.requestLayout();

                }
            });
            valueAnimator.setDuration(4000);
            valueAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    //属性动画
                    ViewPropertyAnimator.animate(iv_safe_arrow)
                            .rotationBy(180)/*旋转角度*/
                            .setDuration(500)
                            .start();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    isOpenSafe=!isOpenSafe;

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            valueAnimator.start();
        }
        
    }
}
