package com.lan.googleplay.bean;

import java.util.List;

public class Category {
	private String title;
	private List<CategoryInfo> infos;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<CategoryInfo> getInfos() {
		return infos;
	}
	public void setInfos(List<CategoryInfo> infos) {
		this.infos = infos;
	}
}
