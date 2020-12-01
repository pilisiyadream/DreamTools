package com.pilisiya.dreamtools.view.yearmonnthday;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DateArrayAdapter extends ArrayWheelAdapter<String> {
	int currentItem;
	int currentValue;


	public DateArrayAdapter(Context context, String[] items, int current) {
		super(context, items);
		this.currentValue = current;
		setTextSize(16);
	}

	@Override
	protected void configureTextView(TextView view) {
		super.configureTextView(view);
		view.setTypeface(Typeface.SANS_SERIF);
	}

	@Override
	public View getItem(int index, View cachedView, ViewGroup parent) {
		currentItem = index;
		return super.getItem(index, cachedView, parent);
	}
}
