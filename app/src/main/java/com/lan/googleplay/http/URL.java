package com.lan.googleplay.http;

/**
 * Created by X_S on 2016/12/24.
 */

public interface URL {
    String SERVER_HOST="http://127.0.0.1:8090/";
    String IMAGE_PREFIX = SERVER_HOST + "image?name=";
    String HOME = SERVER_HOST + "home?index=";
    String APP = SERVER_HOST + "app?index=";
    String GAME = SERVER_HOST + "game?index=";
    String SUBJECT = SERVER_HOST + "subject?index=";
    String RECOMMEND = SERVER_HOST + "recommend?index=0";
    String CATEGORY = SERVER_HOST + "category?index=0";
    String HOT = SERVER_HOST + "hot?index=0";
    String APP_DETAIL = SERVER_HOST + "detail?packageName=";
}
