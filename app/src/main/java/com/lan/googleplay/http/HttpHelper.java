package com.lan.googleplay.http;

import com.lan.googleplay.uitls.LogUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by X_S on 2016/12/24.
 */

public class HttpHelper {

    private static String result;

    public static String get(String url){
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            switch (httpResponse.getStatusLine().getStatusCode()) {
                case 200:
                    HttpEntity httpEntity = httpResponse.getEntity();
                    InputStream is = httpEntity.getContent();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                        baos.flush();
                    }
                    result = new String(baos.toByteArray(), "utf-8");
                    is.close();
                    baos.close();
                    httpClient.getConnectionManager().closeExpiredConnections();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//       LogUtil.e("HttpHelperljw+App",result);
        return result;

//        HttpUtils httpUtils = new HttpUtils();
//        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//                result = responseInfo.result;
//                LogUtil.e("HttpHelper",responseInfo.result);
//            }
//            @Override
//            public void onFailure(HttpException error, String msg) {
//                LogUtil.e("HttpHelper",msg.toString());
//            }
//        });
//        if(result==null){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        LogUtil.e("ljw",result);
//        return result;
    }
}
