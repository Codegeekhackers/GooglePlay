package com.lan.googleplay.ui.part;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lan.googleplay.R;
import com.lan.googleplay.bean.AppInfo;
import com.lan.googleplay.global.GooglePlayApplication;
import com.lan.googleplay.global.ImageLoaderOptions;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.ui.activity.AppDetailActivity;
import com.lan.googleplay.ui.activity.ImageScaleActivity;
import com.lan.googleplay.uitls.CommonUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X_S on 2016/12/26.
 */

public class AppScreenPart extends AppBasePart<AppInfo> {

    private final View view;
    private AppDetailActivity activity;
    private final LinearLayout ll_screen_container;
    private List<String> screens;

    public AppScreenPart( AppDetailActivity activity) {
        this.activity =activity;
        view = View.inflate(GooglePlayApplication.getContext(), R.layout.layout_detail_app_screen, null);
        ll_screen_container = (LinearLayout) view.findViewById(R.id.ll_screen_container);

    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setData(AppInfo appInfo) {
        int screen_image_width = (int) CommonUtil.getDimen(R.dimen.screen_image_width);
        int screen_image_height = (int) CommonUtil.getDimen(R.dimen.screen_image_height);
        int screen_image_margin = (int) CommonUtil.getDimen(R.dimen.screen_image_margin);
        screens = appInfo.getScreen();
        for (int i = 0; i < screens.size(); i++) {
            ImageView imageView = new ImageView(GooglePlayApplication.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screen_image_width, screen_image_height);
            if (i > 0) {
                params.leftMargin = screen_image_margin;
            }
            imageView.setLayoutParams(params);
            ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX+ screens.get(i),imageView, ImageLoaderOptions.pager_options);

            ll_screen_container.addView(imageView);
            final int index = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, ImageScaleActivity.class);
                    intent.putExtra("index", index);
                    intent.putStringArrayListExtra("urls", (ArrayList<String>) screens);
                    activity.startActivity(intent);

                }
            });
        }


    }
}
