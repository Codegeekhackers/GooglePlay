package com.lan.googleplay.ui.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {
	private int horizontalSpacing = 15;
	private int verticalSpacing = 15;

	private List<Line> lines = new ArrayList<>();

	public FlowLayout(Context context) {
		super(context);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int paddingLeft = getPaddingLeft();
		int paddingTop = getPaddingTop();
		for (int i = 0; i < lines.size(); i++) {
			Line line = lines.get(i);
			if (i > 0) {
				paddingTop += lines.get(i - 1).getHeight() + verticalSpacing;
			}
			List<View> views = line.getViews();
			int remainSpacing = getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - line.getWidth();
			int perSpacing = remainSpacing / views.size();

			for (int j = 0; j < views.size(); j++) {
				View child = views.get(j);
				int widthMeasureSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredWidth() + perSpacing, MeasureSpec.EXACTLY);
				child.measure(widthMeasureSpec, 0);
				if (j == 0) {
					child.layout(paddingLeft, paddingTop, paddingLeft + child.getMeasuredWidth(), paddingTop + child.getMeasuredHeight());
				} else {
					View preView = views.get(j - 1);
					int left = preView.getRight() + horizontalSpacing;
					child.layout(left, preView.getTop(), left + child.getMeasuredWidth(), preView.getBottom());
				}
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		lines.clear();

		int width = MeasureSpec.getSize(widthMeasureSpec);
		int noPaddingWidth = width - getPaddingLeft() - getPaddingRight();

		Line line = null;
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.measure(0, 0);
			if (line == null) {
				line = new Line();
			}
			if (line.getViews().size() == 0) {
				line.addChild(child);
			} else if (line.getWidth() + horizontalSpacing + child.getMeasuredWidth() > noPaddingWidth) {
				lines.add(line);
				line = new Line();
				line.addChild(child);
			} else {
				line.addChild(child);
			}

			if (i == getChildCount() - 1) {
				lines.add(line);
			}
		}
		int height = getPaddingTop() + getPaddingBottom();
		for (int i = 0; i < lines.size(); i++) {
			height += lines.get(i).getHeight();
		}

		height += (lines.size() - 1) * verticalSpacing;

		setMeasuredDimension(width, height);
	}

	public void setHorizontalSpacing(int horizontalSpacing) {
		this.horizontalSpacing = horizontalSpacing;
	}

	public void setVerticalSpacing(int verticalSpacing) {
		this.verticalSpacing = verticalSpacing;
	}

	private class Line {
		private int width = 0;
		private int height = 0;
		private List<View> views;

		public Line() {
			views = new ArrayList<>();
		}

		public void addChild(View view) {
			if (!views.contains(view)) {
				if (views.size() == 0) {
					width = view.getMeasuredWidth();
				} else {
					width += horizontalSpacing + view.getMeasuredWidth();
				}
				height = Math.max(view.getMeasuredHeight(), height);
				views.add(view);
			}
		}

		public List<View> getViews() {
			return views;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}
	}
}
