package com.lan.googleplay.uitls;

import android.util.Log;

/**
 * Created by X_S on 2016/12/24.
 */

public class LogUtil {
    private static boolean isDebug = true;

    public static void i(String TAG,String msg){
        if(isDebug=true){
            Log.i(TAG,msg);
        }

    }

    public static void i(Object obj,String msg){
        if(isDebug=true){
            Log.i(obj.getClass().getSimpleName(),msg);
        }
    }

    public static void e(String TAG,String msg){
        if(isDebug=true){
            Log.e(TAG,msg);
        }

    }

    public static void e(Object obj,String msg){
        if(isDebug=true){
            Log.i(obj.getClass().getSimpleName(),msg);
        }
    }
}
