package com.forte.login;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;

public class CarBrandCheckableLayout extends FrameLayout implements Checkable {

    private boolean checked;

    public CarBrandCheckableLayout(Context context) {
        super(context);
    }

    public CarBrandCheckableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        setBackgroundDrawable(checked ? new ColorDrawable(0xff0000a0) : null);
    }

    public boolean isChecked() {
        return checked;
    }

    public void toggle() {
        setChecked(!checked);
    }


}
