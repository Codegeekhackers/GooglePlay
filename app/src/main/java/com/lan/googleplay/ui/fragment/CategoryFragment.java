package com.lan.googleplay.ui.fragment;

import android.view.View;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.lan.googleplay.R;
import com.lan.googleplay.bean.Category;
import com.lan.googleplay.bean.CategoryInfo;
import com.lan.googleplay.http.HttpHelper;
import com.lan.googleplay.http.URL;
import com.lan.googleplay.ui.Apdater.CategoryAdapter;
import com.lan.googleplay.uitls.CommonUtil;
import com.lan.googleplay.uitls.JsonUtil;
import com.lan.googleplay.uitls.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by X_S on 2016/12/18.
 */
public class CategoryFragment extends BaseFragment {
    private List<Object> list = new ArrayList<>();
    private CategoryAdapter categoryAdapter;

    @Override
    public View getSuccessView() {
        ListView listview = (ListView) View.inflate(getActivity(), R.layout.listview, null);
        categoryAdapter = new CategoryAdapter(list);
        listview.setAdapter(categoryAdapter);
        return listview;
    }

    @Override
    public Object requestData() {
        String result = HttpHelper.get(URL.CATEGORY);
        List<Category> Categories = (List<Category>) JsonUtil.parseJsonToList(result, new TypeToken<List<Category>>() {}.getType());
        for (Category category: Categories) {
            String title = category.getTitle();
            list.add(title);
            List<CategoryInfo> infos = category.getInfos();
            list.addAll(infos);

        }
//        LogUtil.i("list","");
//        System.out.println(list);
        CommonUtil.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                categoryAdapter.notifyDataSetChanged();
            }
        });
        return Categories;
    }
}
