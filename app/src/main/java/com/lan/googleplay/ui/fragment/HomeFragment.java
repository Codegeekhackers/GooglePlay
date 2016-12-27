package com.lan.googleplay.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lan.googleplay.R;
import com.lan.googleplay.bean.AppInfo;
import com.lan.googleplay.bean.HomeBean;
import com.lan.googleplay.http.HttpHelper;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.ui.Apdater.HomeAdapter;
import com.lan.googleplay.ui.Apdater.HomeHeaderAdapter;
import com.lan.googleplay.ui.activity.AppDetailActivity;
import com.lan.googleplay.uitls.CommonUtil;
import com.lan.googleplay.uitls.JsonUtil;

import java.util.ArrayList;

/**
 * Created by X_S on 2016/12/18.
 */
public class HomeFragment extends BaseFragment {

    private PullToRefreshListView pullToRefreshListView;
    private ListView lv_refresh;
    private ArrayList<AppInfo> appInfoList = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private HomeBean homeBean;
    private ViewPager vp_home;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            vp_home.setCurrentItem(vp_home.getCurrentItem()+1);
            //多久切换一次
            handler.sendEmptyMessageDelayed(0,2000);
        }
    };

    @Override
    public View getSuccessView() {

        initRefreshListView();
        initHomeHeader();
        homeAdapter = new HomeAdapter(appInfoList);
        lv_refresh.setAdapter(homeAdapter);

        lv_refresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("packageName", appInfoList.get(i - 2).getPackageName());
                startActivity(intent);
            }
        });
        return pullToRefreshListView;
    }

    private void initHomeHeader() {
        View headerView = View.inflate(getActivity(), R.layout.home_header, null);
        vp_home = (ViewPager) headerView.findViewById(R.id.vp_home);

        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        float height =width/2.65f;
        ViewGroup.LayoutParams layoutParams = vp_home.getLayoutParams();
        layoutParams.height= (int) height;
        vp_home.setLayoutParams(layoutParams);
        lv_refresh.addHeaderView(headerView);
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

        String homeResult = HttpHelper.get(URL.HOME + appInfoList.size());
        homeBean = JsonUtil.parseJsonToBean(homeResult, HomeBean.class);
        appInfoList.addAll(homeBean.getList());

        CommonUtil.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (homeBean.getPicture() != null && homeBean.getPicture().size() != 0) {
                    HomeHeaderAdapter homeHeaderAdapter = new HomeHeaderAdapter(homeBean.getPicture());
                   vp_home.setAdapter(homeHeaderAdapter);
                }
                homeAdapter.notifyDataSetChanged();
                pullToRefreshListView.onRefreshComplete();
            }
        });
        return homeBean;
    }

    @Override
    public void onResume() {
        super.onResume();
        //发送消息
        handler.sendEmptyMessageDelayed(0,2000);
    }

    @Override
    public void onDestroy() {
        //取消销毁
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();

    }
}
