package com.lan.googleplay.ui.fragment;


import android.view.View;

import android.widget.ListView;


import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lan.googleplay.R;
import com.lan.googleplay.bean.Subject;
import com.lan.googleplay.http.HttpHelper;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.ui.Apdater.SubjectAdapter;
import com.lan.googleplay.uitls.CommonUtil;
import com.lan.googleplay.uitls.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X_S on 2016/12/18.
 */
public class SubjectFragment extends BaseFragment {
    private PullToRefreshListView pullToRefreshListView;
    private ListView lv_refresh;
    private List<Subject> subjectList = new ArrayList<>();
    private SubjectAdapter subjectAdapter;

    @Override
    public View getSuccessView() {
        initRefreshListView();
        subjectAdapter = new SubjectAdapter(subjectList);
        lv_refresh.setAdapter(subjectAdapter);
        return pullToRefreshListView;

    }

    private void initRefreshListView() {
        pullToRefreshListView = (PullToRefreshListView) View.inflate(getActivity(), R.layout.homefragment_lv, null);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                switch (refreshView.getCurrentMode()){
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


    @Override
    public Object requestData() {
        String result = HttpHelper.get(URL.SUBJECT + subjectList.size());
        List<Subject> subjects = (List<Subject>) JsonUtil.parseJsonToList(result, new TypeToken<List<Subject>>() {}.getType());
        subjectList.addAll(subjects);
        CommonUtil.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                subjectAdapter.notifyDataSetChanged();
                pullToRefreshListView.onRefreshComplete();
            }
        });
        return subjectList;
    }
}
