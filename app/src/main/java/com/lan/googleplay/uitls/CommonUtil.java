package com.lan.googleplay.uitls;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.lan.googleplay.global.GooglePlayApplication;

/**
 * Created by X_S on 2016/12/18.
 */

public class CommonUtil {

    public static void runOnUIThread(Runnable r){
        GooglePlayApplication.getmHandler().post(r);
    }

    public static Resources getResources(){
        return GooglePlayApplication.getContext().getResources();
    }

    public static String[] getStringArray(int resId){
        return getResources().getStringArray(resId);
    }

    public static String getString(int resId){
        return getResources().getString(resId);
    }

    public static Drawable getDrawable(int resId){
        return getResources().getDrawable(resId);
    }

    public static float getDimen(int resId){
        return getResources().getDimension(resId);
    }

    public static int getColor(int resId){
        return getResources().getColor(resId);
    }

    public static void removeSelfFromParent(View child){
        if (child!=null){
            ViewParent parent = child.getParent();
            if (parent instanceof ViewGroup){
                ViewGroup group= (ViewGroup) parent;
                group.removeView(child);
            }
        }


    }
}
