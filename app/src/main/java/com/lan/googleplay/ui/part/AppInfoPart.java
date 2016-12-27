package com.lan.googleplay.ui.part;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lan.googleplay.R;
import com.lan.googleplay.bean.AppInfo;
import com.lan.googleplay.global.GooglePlayApplication;
import com.lan.googleplay.global.ImageLoaderOptions;
import com.lan.googleplay.http.URL;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by X_S on 2016/12/26.
 */

public class AppInfoPart extends AppBasePart<AppInfo> {

    private TextView tv_download_num;
    private ImageView iv_icon;
    private TextView tv_name;
    private RatingBar rb_stars;
    private TextView tv_version;
    private TextView tv_date;
    private TextView tv_size;
    private View view;

    public AppInfoPart() {
        initView();

    }

    private void initView() {
        view = View.inflate(GooglePlayApplication.getContext(), R.layout.layout_detail_app_info, null);
        iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        rb_stars = (RatingBar) view.findViewById(R.id.rb_stars);
        tv_download_num = (TextView) view.findViewById(R.id.tv_download_num);
        tv_version = (TextView) view.findViewById(R.id.tv_version);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_size = (TextView) view.findViewById(R.id.tv_size);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setData(AppInfo appInfo) {
        ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX+appInfo.getIconUrl(),iv_icon, ImageLoaderOptions.list_options);
        tv_name.setText(appInfo.getName());
        rb_stars.setRating(appInfo.getStars());
        tv_download_num.setText("下载：" + appInfo.getDownloadNum());
        tv_version.setText("版本：" + appInfo.getVersion());
        tv_date.setText("日期：" + appInfo.getDate());
        tv_size.setText("大小：" + Formatter.formatFileSize(GooglePlayApplication.getContext(), appInfo.getSize()));
    }
}
