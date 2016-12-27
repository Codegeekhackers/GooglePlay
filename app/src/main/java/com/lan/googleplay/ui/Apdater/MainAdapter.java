package com.lan.googleplay.ui.Apdater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.lan.googleplay.R;
import com.lan.googleplay.ui.fragment.FragmentFactory;
import com.lan.googleplay.uitls.CommonUtil;

/**
 * Created by X_S on 2016/12/18.
 */

public class MainAdapter extends FragmentPagerAdapter {

    private static final String TAG = "MainAdapter";
    private final String[] tabs;

    public MainAdapter(FragmentManager fm) {
        super(fm);
        tabs = CommonUtil.getStringArray(R.array.tab_names);
//        for (String s:tabs) {
//            Log.e(",",s);
//        }
    }

    @Override
    public Fragment getItem(int position) {
        //Log.e(TAG,"MainAdapter.position="+position);
        return FragmentFactory.create(position);
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
