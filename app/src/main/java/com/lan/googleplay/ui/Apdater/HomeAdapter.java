package com.lan.googleplay.ui.Apdater;

import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lan.googleplay.R;
import com.lan.googleplay.bean.AppInfo;
import com.lan.googleplay.global.ImageLoaderOptions;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.global.GooglePlayApplication;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by X_S on 2016/12/24.
 */

public class HomeAdapter extends BasicAdapter<AppInfo>{
    public HomeAdapter(List list) {
        super(list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = View.inflate(GooglePlayApplication.getContext(), R.layout.item_lv_home,null);
            holder.iv_app_icon = (ImageView) view.findViewById(R.id.iv_app_icon);
            holder.tv_app_name = (TextView) view.findViewById(R.id.tv_app_name);
            holder.rb_app_stars = (RatingBar) view.findViewById(R.id.rb_app_stars);
            holder.tv_app_size = (TextView) view.findViewById(R.id.tv_app_size);
            holder.tv_app_des = (TextView) view.findViewById(R.id.tv_app_des);
            view.setTag(holder);
        }else {
             holder = (ViewHolder) view.getTag();
        }
        AppInfo appInfo = list.get(i);
        holder.tv_app_name.setText(appInfo.getName());
        //设置星星
        holder.rb_app_stars.setRating(appInfo.getStars());
        //格式化 Formatter.formatFileSize(GooglePlayApplication.getContext(), appInfo.getSize())
        holder.tv_app_size.setText(Formatter.formatFileSize(GooglePlayApplication.getContext(), appInfo.getSize()));
        holder.tv_app_des.setText(appInfo.getDes());
        //加载图片 三级缓存 全局
        ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX+appInfo.getIconUrl(),holder.iv_app_icon, ImageLoaderOptions.list_options);


        return view;

    }

    private class ViewHolder {
        public ImageView iv_app_icon;
        public TextView tv_app_name;
        public RatingBar rb_app_stars;
        public TextView tv_app_size;
        public TextView tv_app_des;
    }
}
