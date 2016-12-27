package com.lan.googleplay.ui.fragment;

import android.support.v4.app.Fragment;

public class FragmentFactory {
	public static Fragment create(int position){
		//预加载一个fragment
		Fragment fragment=null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new AppFragment();
			break;
		case 2:
			fragment = new GameFragment();
			break;
		case 3:
			fragment = new SubjectFragment();
			break;
		case 4:
			fragment = new RecommendFragment();
			break;
		case 5:
			fragment = new CategoryFragment();
			break;
		case 6:
			fragment = new HotFragment();
			break;
		}
		return fragment;
	}
}
