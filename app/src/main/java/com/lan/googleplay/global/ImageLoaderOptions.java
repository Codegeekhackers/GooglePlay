package com.lan.googleplay.global;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lan.googleplay.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by X_S on 2016/12/24.
 */

public class ImageLoaderOptions {
    public static DisplayImageOptions list_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_cancel)
            .showImageOnFail(R.mipmap.ic_cancel)
            .showImageForEmptyUri(R.mipmap.ic_cancel)
            .cacheInMemory(true)/*内存缓存*/
            .cacheOnDisk(true)/*文件缓存*/
            .considerExifParams(true)/*是否识别图像的方向信息*/
            .displayer(new FadeInBitmapDisplayer(300)).build();/*渐入渐出的动画效果*/

    public static DisplayImageOptions pager_options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_cancel)
            .showImageOnFail(R.mipmap.ic_cancel)
            .showImageForEmptyUri(R.mipmap.ic_cancel)
            .resetViewBeforeLoading(true)/*清除图片*/
            .imageScaleType(ImageScaleType.EXACTLY)/*缩放模式 丢失精度较少*/
            .bitmapConfig(Bitmap.Config.RGB_565)/*设置按什么向所比例进行缩放，这种模式相对好用和省内存*/
            .cacheInMemory(true)/*内存缓存*/
            .cacheOnDisk(true)/*文件缓存*/
            .considerExifParams(true)/*是否识别图像的方向信息*/
            .displayer(new FadeInBitmapDisplayer(300)).build();/*渐入渐出的动画效果*/
}
