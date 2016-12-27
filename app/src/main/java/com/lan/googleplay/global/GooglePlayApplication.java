package com.lan.googleplay.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by X_S on 2016/12/18.
 */

public class GooglePlayApplication extends Application {
    private static Context context;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        mHandler = new Handler();
        initImageLoader();
        //Log.e("TAGApplication",""+context+""+mHandler);
    }

    private void initImageLoader() {
        ImageLoaderConfiguration.Builder configBuilder = new  ImageLoaderConfiguration.Builder(this);
        configBuilder.threadPriority(Thread.NORM_PRIORITY-2);
        configBuilder.denyCacheImageMultipleSizesInMemory();
        configBuilder.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        configBuilder.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        configBuilder.tasksProcessingOrder(QueueProcessingType.LIFO);/*拿出可用缓存的八分之一*/
        configBuilder.writeDebugLogs();/*写入异常*/

        ImageLoader.getInstance().init(configBuilder.build());

    }

    public static Context getContext() {
        return context;
    }

    public static Handler getmHandler() {
        return mHandler;
    }
}
