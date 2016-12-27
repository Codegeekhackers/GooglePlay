package com.lan.googleplay.ui.Apdater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lan.googleplay.R;
import com.lan.googleplay.bean.Category;
import com.lan.googleplay.bean.CategoryInfo;
import com.lan.googleplay.global.GooglePlayApplication;
import com.lan.googleplay.global.ImageLoaderOptions;
import com.lan.googleplay.http.URL;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by X_S on 2016/12/25.
 */

public class CategoryAdapter extends BasicAdapter<Object> {

    private static final int ITEM_TITLE = 0;
    private static final int ITEM_INFO = 1;

    public CategoryAdapter(List<Object> list) {
        super(list);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public int getItemViewType(int position) {
        Object object = list.get(position);
        if (object instanceof String) {
            return ITEM_TITLE;
        } else {
            return ITEM_INFO;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);

        switch (itemViewType) {
            case ITEM_TITLE:
                if (view == null) {
                    view = View.inflate(GooglePlayApplication.getContext(), R.layout.adapter_category_title, null);
                }
                TextView textView = (TextView) view.findViewById(R.id.tv_category_title);
                textView.setText((String) list.get(i));

                break;
            case ITEM_INFO:
                ViewHolder infoViewHolder = null;
                if (view == null) {
                    view = View.inflate(GooglePlayApplication.getContext(), R.layout.adapter_category_info, null);
                    infoViewHolder = new ViewHolder();
                    infoViewHolder.ll_category_1 = (LinearLayout) view.findViewById(R.id.ll_category_1);
                    infoViewHolder.iv_category_image1 = (ImageView) view.findViewById(R.id.iv_category_image1);
                    infoViewHolder.tv_category_name1 = (TextView) view.findViewById(R.id.tv_category_name1);
                    infoViewHolder.ll_category_2 = (LinearLayout) view.findViewById(R.id.ll_category_2);
                    infoViewHolder.iv_category_image2 = (ImageView) view.findViewById(R.id.iv_category_image2);
                    infoViewHolder.tv_category_name2 = (TextView) view.findViewById(R.id.tv_category_name2);
                    infoViewHolder.ll_category_3 = (LinearLayout) view.findViewById(R.id.ll_category_3);
                    infoViewHolder.iv_category_image3 = (ImageView) view.findViewById(R.id.iv_category_image3);
                    infoViewHolder.tv_category_name3 = (TextView) view.findViewById(R.id.tv_category_name3);
                    view.setTag(infoViewHolder);
                } else {
                    infoViewHolder = (ViewHolder) view.getTag();
                }
                CategoryInfo info = (CategoryInfo) list.get(i);
                if (!"".equals(info.getName1())) {
                    infoViewHolder.ll_category_1.setVisibility(View.VISIBLE);
                    infoViewHolder.tv_category_name1.setText(info.getName1());
                    ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX + info.getUrl1(), infoViewHolder.iv_category_image1, ImageLoaderOptions.list_options);
                } else {
                    infoViewHolder.ll_category_1.setVisibility(View.INVISIBLE);
                }
                if (!"".equals(info.getName2())) {
                    infoViewHolder.ll_category_2.setVisibility(View.VISIBLE);
                    infoViewHolder.tv_category_name2.setText(info.getName2());
                    ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX + info.getUrl2(), infoViewHolder.iv_category_image2, ImageLoaderOptions.list_options);
                } else {
                    infoViewHolder.ll_category_2.setVisibility(View.INVISIBLE);
                }
                if (!"".equals(info.getName3())) {
                    infoViewHolder.ll_category_3.setVisibility(View.VISIBLE);
                    infoViewHolder.tv_category_name3.setText(info.getName3());
                    ImageLoader.getInstance().displayImage(URL.IMAGE_PREFIX + info.getUrl3(), infoViewHolder.iv_category_image3, ImageLoaderOptions.list_options);
                } else {
                    infoViewHolder.ll_category_3.setVisibility(View.INVISIBLE);
                }
                break;
        }

        return view;
    }

    private class ViewHolder {
        private LinearLayout ll_category_1;
        private ImageView iv_category_image1;
        private TextView tv_category_name1;
        private LinearLayout ll_category_2;
        private ImageView iv_category_image2;
        private TextView tv_category_name2;
        private LinearLayout ll_category_3;
        private ImageView iv_category_image3;
        private TextView tv_category_name3;
    }
}
