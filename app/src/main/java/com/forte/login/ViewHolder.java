package com.forte.login;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
	TextView text = null;
	ImageView image = null;
	
	ViewHolder(View base) {
		this.text = (TextView)base.findViewById(R.id.textViewGroup);
		this.image = (ImageView)base.findViewById(R.id.imageViewGroup);
	}
}
