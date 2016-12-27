package com.lan.googleplay.ui.fragment;

import android.view.View;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lan.googleplay.R;
import com.lan.googleplay.bean.AppInfo;
import com.lan.googleplay.http.HttpHelper;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.ui.Apdater.GameAdapter;
import com.lan.googleplay.uitls.CommonUtil;
import com.lan.googleplay.uitls.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X_S on 2016/12/18.
 */
public class GameFragment extends BaseFragment {
    private PullToRefreshListView pullToRefreshListView;
    private ListView lv_refresh;
    private ArrayList<AppInfo> appInfoList = new ArrayList<>();
    private GameAdapter gameAdapter;

    @Override
    public View getSuccessView() {
        initRefreshListView();
        gameAdapter = new GameAdapter(appInfoList);
        lv_refresh.setAdapter(gameAdapter);

        return pullToRefreshListView;
    }

    @Override
    public Object requestData() {
        String appResult = HttpHelper.get(URL.GAME + appInfoList.size());
//        homeBean = JsonUtil.parseJsonToBean(appResult, HomeBean.class);
        List<AppInfo> appList = (List<AppInfo>) JsonUtil.parseJsonToList(appResult, new TypeToken<List<AppInfo>>() {}.getType());
        appInfoList.addAll(appList);

        CommonUtil.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                gameAdapter.notifyDataSetChanged();
                pullToRefreshListView.onRefreshComplete();
            }
        });
        return appList;
    }

    private void initRefreshListView() {
        pullToRefreshListView = (PullToRefreshListView) View.inflate(getActivity(), R.layout.homefragment_lv, null);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                switch (refreshView.getCurrentMode()) {
                    case PULL_FROM_START:
                        CommonUtil.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                pullToRefreshListView.onRefreshComplete();
                            }
                        });
                        break;
                    case PULL_FROM_END:
                        contentPage.loadDataAndRefresh();
                        break;
                }

            }
        });


        lv_refresh = pullToRefreshListView.getRefreshableView();
        lv_refresh.setDividerHeight(0);
        lv_refresh.setSelector(android.R.color.transparent);//背景颜色
    }

}
