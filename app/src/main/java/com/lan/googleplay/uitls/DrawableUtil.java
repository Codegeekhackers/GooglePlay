package com.lan.googleplay.uitls;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtil {
	public static GradientDrawable generateDrawable(int argb, int radius) {
		GradientDrawable gradientDrawable = new GradientDrawable();
		gradientDrawable.setShape(GradientDrawable.RECTANGLE);
		gradientDrawable.setCornerRadius(radius);
		gradientDrawable.setColor(argb);
		return gradientDrawable;
	}
	
	public static StateListDrawable generateSelector(Drawable normal, Drawable pressed) {
		StateListDrawable stateListDrawable = new StateListDrawable();
		stateListDrawable.addState(new int[] {android.R.attr.state_pressed}, pressed);
		stateListDrawable.addState(new int[] {}, normal);
		return stateListDrawable;
	}
}
