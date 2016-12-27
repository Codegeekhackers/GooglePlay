package com.lan.googleplay.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.lan.googleplay.R;
import com.lan.googleplay.bean.AppInfo;
import com.lan.googleplay.http.HttpHelper;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.ui.fragment.ContentPage;
import com.lan.googleplay.ui.part.AppDesPart;
import com.lan.googleplay.ui.part.AppInfoPart;
import com.lan.googleplay.ui.part.AppSafePart;
import com.lan.googleplay.ui.part.AppScreenPart;
import com.lan.googleplay.uitls.CommonUtil;
import com.lan.googleplay.uitls.JsonUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by X_S on 2016/12/26.
 */

public class AppDetailActivity extends AppCompatActivity {

    private AppInfoPart appInfoPart;
    private AppSafePart appSafePart;
    private AppScreenPart appScreenPart;
    private AppDesPart appDesPart;
    private String packageName;
    private AppInfo appInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        packageName = getIntent().getStringExtra("packageName");

        ContentPage contentPage = new ContentPage(this) {
            @Override
            public Object loadData() {
                return requestData();
            }

            @Override
            public View createSuccessView() {
                return getSuccessView();
            }
        };
        setContentView(contentPage);

    }


    private View getSuccessView() {
        View view = View.inflate(this, R.layout.activity_app_detail, null);
        Toolbar tb_app = (Toolbar) view.findViewById(R.id.tb_app);
        ScrollView scrollview = (ScrollView) view.findViewById(R.id.scrollview);
        LinearLayout ll_part_container = (LinearLayout) view.findViewById(R.id.ll_part_container);
        initActionBar(tb_app);

        appInfoPart = new AppInfoPart();
        ll_part_container.addView(appInfoPart.getView());
        appSafePart = new AppSafePart();
        ll_part_container.addView(appSafePart.getView());
        appScreenPart = new AppScreenPart(this);
        ll_part_container.addView(appScreenPart.getView());
        appDesPart = new AppDesPart(scrollview);
        ll_part_container.addView(appDesPart.getView());
        return view;
    }

    private void initActionBar(Toolbar tb_app) {
        tb_app.setLogo(R.mipmap.ic_launcher);
        tb_app.setTitle(getString(R.string.app_name));
        setSupportActionBar(tb_app);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);//设置Icon可见按钮可以被点击
    }

    private Object requestData() {
        String result = HttpHelper.get(URL.APP_DETAIL+packageName);
        appInfo = JsonUtil.parseJsonToBean(result, AppInfo.class);
        CommonUtil.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                refreshUI();
            }
        });
        return appInfo;
//        return list;
    }

    private void refreshUI() {
        appInfoPart.setData(appInfo);
        appSafePart.setData(appInfo);
        appScreenPart.setData(appInfo);
        appDesPart.setData(appInfo);
    }

}
