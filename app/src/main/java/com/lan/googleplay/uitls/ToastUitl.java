package com.lan.googleplay.uitls;

import android.widget.Toast;

import com.lan.googleplay.global.GooglePlayApplication;

/**
 * Created by X_S on 2016/12/24.
 */

public class ToastUitl {
    private static Toast toast;

    public static void showToast(String text){
        if(toast==null){
            toast = Toast.makeText(GooglePlayApplication.getContext(),text,Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }
}
