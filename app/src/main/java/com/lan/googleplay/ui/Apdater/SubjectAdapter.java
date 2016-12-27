package com.lan.googleplay.ui.Apdater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lan.googleplay.R;
import com.lan.googleplay.bean.Subject;
import com.lan.googleplay.global.GooglePlayApplication;
import com.lan.googleplay.global.ImageLoaderOptions;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.ui.view.RationImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by X_S on 2016/12/25.
 */

public class SubjectAdapter extends BasicAdapter<Subject> {
    public SubjectAdapter(List list) {
        super(list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null) {
            view = View.inflate(GooglePlayApplication.getContext(), R.layout.adapter_subject, null);
            holder = new ViewHolder();
            holder.riv_subject_pic = (RationImageView) view.findViewById(R.id.riv_subject_pic);
            holder.tv_subject_des = (TextView) view.findViewById(R.id.tv_subject_des);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Subject subject = list.get(i);
        ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX + subject.getUrl(), holder.riv_subject_pic, ImageLoaderOptions.list_options);
        holder.tv_subject_des.setText(subject.getDes());
        return view;
    }
    private class ViewHolder {
        public RationImageView riv_subject_pic;
        public TextView tv_subject_des;
    }
}
