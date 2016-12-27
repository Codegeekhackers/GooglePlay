package com.lan.googleplay.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lan.googleplay.uitls.CommonUtil;

public abstract class BaseFragment extends Fragment {
	public ContentPage contentPage;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(contentPage == null){
			contentPage=new ContentPage(getActivity()) {

				@Override
				public Object loadData() {
					return requestData();
				}
				@Override
				public View createSuccessView() {
					return getSuccessView();
				}
			};
		}else{
			CommonUtil.removeSelfFromParent(contentPage);
		}

		return contentPage;
	}

	public abstract View getSuccessView();

	public abstract Object requestData();
	
}
