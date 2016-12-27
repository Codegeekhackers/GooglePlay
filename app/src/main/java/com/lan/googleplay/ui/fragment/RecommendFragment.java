package com.lan.googleplay.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lan.googleplay.R;
import com.lan.googleplay.http.HttpHelper;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.lib.randomLayout.StellarMap;
import com.lan.googleplay.ui.Apdater.StellarMapAdapter;
import com.lan.googleplay.uitls.CommonUtil;
import com.lan.googleplay.uitls.JsonUtil;

import java.util.List;

/**
 * Created by X_S on 2016/12/18.
 */
public class RecommendFragment extends BaseFragment {

    private StellarMap stellarMap;

    @Override
    public View getSuccessView() {
        stellarMap = new StellarMap(getContext());
        int innerPadding = (int) CommonUtil.getDimen(R.dimen.stellar_map_padding);
        stellarMap.setInnerPadding(innerPadding, innerPadding, innerPadding, innerPadding);
        return stellarMap;
    }

    @Override
    public Object requestData() {

        String result = HttpHelper.get(URL.RECOMMEND);
        if(result!=null){
            final List<String> recommends = (List<String>) JsonUtil.parseJsonToList(result, new TypeToken<List<String>>() {}.getType());
            CommonUtil.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    stellarMap.setAdapter(new StellarMapAdapter(recommends));

                    //设置初始的组
                    stellarMap.setGroup(0, true);
                    stellarMap.setRegularity(15, 15);//设置排布规则 x,y
                }
            });
            return recommends;

        }

        return null;
    }
}
