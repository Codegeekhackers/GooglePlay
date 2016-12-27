package com.lan.googleplay.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lan.googleplay.R;
import com.lan.googleplay.http.HttpHelper;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.ui.view.FlowLayout;
import com.lan.googleplay.uitls.ColorUtil;
import com.lan.googleplay.uitls.CommonUtil;
import com.lan.googleplay.uitls.DrawableUtil;
import com.lan.googleplay.uitls.JsonUtil;
import com.lan.googleplay.uitls.ToastUitl;

import java.util.List;

/**
 * Created by X_S on 2016/12/18.
 */
public class HotFragment extends BaseFragment {

    private int horizontal_text_padding;
    private int vertical_text_padding;
    private int radius;
    private FlowLayout flowLayout;

    @Override
    public View getSuccessView() {
        ScrollView scrollView = new ScrollView(getContext());
        flowLayout = new FlowLayout(getContext());
        horizontal_text_padding = (int) CommonUtil.getDimen(R.dimen.horizontal_text_padding);
        vertical_text_padding = (int) CommonUtil.getDimen(R.dimen.vertical_text_padding);
        int flow_layout_padding = (int) CommonUtil.getDimen(R.dimen.flow_layout_padding);
        radius = (int) CommonUtil.getDimen(R.dimen.hot_text_radius);
        flowLayout.setPadding(flow_layout_padding,flow_layout_padding,flow_layout_padding,flow_layout_padding);
        scrollView.addView(flowLayout);
        return scrollView;
    }

    @Override
    public Object requestData() {
        String HotResult = HttpHelper.get(URL.HOT);
        final List<String> hotList = (List<String>) JsonUtil.parseJsonToList(HotResult, new TypeToken<List<String>>() {}.getType());

        CommonUtil.runOnUIThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                for (String str:hotList){
                    Log.e("HotResult",""+str);
                    final TextView tv = new TextView(getActivity());
                    tv.setGravity(Gravity.CENTER);
                    tv.setText(str);
                    tv.setTextSize(16);
                    tv.setTextColor(Color.WHITE);
                    GradientDrawable normal = DrawableUtil.generateDrawable(ColorUtil.getRandomColor(), radius);
                    GradientDrawable pressed = DrawableUtil.generateDrawable(ColorUtil.getRandomColor(), radius);
                    StateListDrawable selector = DrawableUtil.generateSelector(normal, pressed);
                    tv.setBackground(selector);
                    tv.setPadding(horizontal_text_padding, vertical_text_padding, horizontal_text_padding, vertical_text_padding);
                    flowLayout.addView(tv);

                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            ToastUitl.showToast(tv.getText().toString());

                        }
                    });

                }
            }
        });

        return hotList;
    }
}
