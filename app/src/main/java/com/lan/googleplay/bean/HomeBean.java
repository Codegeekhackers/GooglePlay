package com.lan.googleplay.bean;

import java.util.List;

/**
 * Created by X_S on 2016/12/24.
 */

public class HomeBean {
    private List<String> picture;
    private List<AppInfo> list;

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public List<AppInfo> getList() {
        return list;
    }

    public void setList(List<AppInfo> list) {
        this.list = list;
    }
}
