package com.lan.googleplay.ui.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.lan.googleplay.R;
import com.lan.googleplay.uitls.CommonUtil;

import java.util.List;

/**
 * Created by X_S on 2016/12/18.
 */
public abstract class ContentPage extends FrameLayout{

    private View loadingView;
    private View errorView;
    private View emptyView;
    private Button btn_reload;
    private PageState state=PageState.STATE_LOADING;
    private View successView;

    public ContentPage(Context context) {
        super(context);
        initContentpage();
    }
    
    public ContentPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContentpage();
    }

    public ContentPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initContentpage();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ContentPage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initContentpage();
    }

    enum PageState{
        STATE_LOADING(0),
        STATE_SUCCESS(1),
        STATE_ERROR(2),
        STATE_EMPTY(3);

        private int value;
        PageState(int value){
            this.value= value;
        }

        public int getValue(){
            return value;
        }


    }

    private void initContentpage() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(loadingView==null){
            loadingView = View.inflate(getContext(), R.layout.page_loading, null);
        }
        addView(loadingView,params);


        if(errorView==null){
            errorView = View.inflate(getContext(), R.layout.page_error, null);
            if(btn_reload ==null){
                btn_reload = (Button) errorView.findViewById(R.id.btn_reload);
            }
            btn_reload.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    state = PageState.STATE_LOADING;
                    showPage();
                    loadDataAndRefresh();
                }
            });
        }
        addView(errorView,params);

        if(emptyView==null){
            emptyView = View.inflate(getContext(), R.layout.page_empty, null);
        }
        addView(emptyView,params);

        successView=createSuccessView();
        if(successView!=null){
            addView(successView,params);
        }else{
            throw  new IllegalStateException("The method createSuccessView() can not return null");
        }

        //有四个视图选择一个视图进行展示
        showPage();
        loadDataAndRefresh();
    }

    protected void loadDataAndRefresh() {
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(1500);
                Object data = loadData();
                state=checkData(data);
                //?
                CommonUtil.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        //主子线程的数据更新
                        showPage();
                    }
                });
                
            }
        }.start();
    }

    private PageState checkData(Object data) {
        if (data==null){
            return PageState.STATE_ERROR;
        }else{
            if(data instanceof List){
                List list= (List) data;
                if(list.size()==0){
                    return PageState.STATE_EMPTY;
                }else {
                    return PageState.STATE_SUCCESS;
                }
            }else{
                //有待完善
                return PageState.STATE_SUCCESS;
            }
        }
    }

    private void showPage() {
        switch (state.getValue()){
            case 0:
                loadingView.setVisibility(View.VISIBLE);
                successView.setVisibility(View.INVISIBLE);
                errorView.setVisibility(View.INVISIBLE);
                emptyView.setVisibility(View.INVISIBLE);
                break;
            case 1:
                loadingView.setVisibility(View.INVISIBLE);
                successView.setVisibility(View.VISIBLE);
                errorView.setVisibility(View.INVISIBLE);
                emptyView.setVisibility(View.INVISIBLE);
                break;
            case 2:
                loadingView.setVisibility(View.INVISIBLE);
                successView.setVisibility(View.INVISIBLE);
                errorView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.INVISIBLE);
                break;
            case 3:
                loadingView.setVisibility(View.INVISIBLE);
                successView.setVisibility(View.INVISIBLE);
                errorView.setVisibility(View.INVISIBLE);
                emptyView.setVisibility(View.VISIBLE);
                break;
        }
    }


    public abstract Object loadData();

    public abstract View createSuccessView();
}
