package com.lan.googleplay.ui.Apdater;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.lan.googleplay.global.GooglePlayApplication;
import com.lan.googleplay.lib.randomLayout.StellarMap;
import com.lan.googleplay.uitls.ColorUtil;

import java.util.List;
import java.util.Random;

/**
 * Created by X_S on 2016/12/25.
 */

public class StellarMapAdapter implements StellarMap.Adapter {
    private List<String> list = null;

    public StellarMapAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getCount(int group) {
        return list.size() / getGroupCount();
    }

    //每个条目的view对象
    @Override
    public View getView(int group, int position, View convertView) {
        TextView textView = new TextView(GooglePlayApplication.getContext());
        //group 组的标号
        int listPosition = group * getCount(group) + position;
        textView.setText(list.get(listPosition));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(new Random().nextInt(8)+14);
        textView.setTextColor(ColorUtil.getRandomColor());
        return textView;
    }

    @Override
    public int getNextGroupOnPan(int group, float degree) {
        return 0;
    }

    @Override
    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
        return (group+1)%getGroupCount();
    }
}
