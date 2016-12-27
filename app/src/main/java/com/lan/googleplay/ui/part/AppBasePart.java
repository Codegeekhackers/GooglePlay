package com.lan.googleplay.ui.part;

import android.view.View;

public abstract class AppBasePart<T> {
	public abstract View getView();
	public abstract void setData(T t);

}
