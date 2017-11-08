package com.varshaaweblabs.ecommerce.Utility;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.varshaaweblabs.ecommerce.R;


/**
 * Created by dinesh on 18/3/16.
 */
public class SegmentedGroup extends RadioGroup {
    private int oneDP;
    private Resources resources;
    private int mTintColor;
    private int mCheckedTextColor = Color.WHITE;

    public SegmentedGroup(Context context) {
        super(context);
        resources = getResources();
        mTintColor = resources.getColor(R.color.colorPrimary);
        oneDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, resources.getDisplayMetrics());
    }

    public SegmentedGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        resources = getResources();
        mTintColor = resources.getColor(R.color.colorPrimary);
        oneDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, resources.getDisplayMetrics());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //Use holo light for default
        updateBackground();
    }

    public void setTintColor(int tintColor) {
        mTintColor = tintColor;
        updateBackground();
    }

    public void setTintColor(int tintColor, int checkedTextColor) {
        mTintColor = tintColor;
        mCheckedTextColor = checkedTextColor;
        updateBackground();
    }

    public void updateBackground() {
        int count = super.getChildCount();
        if (count > 1) {
            View child = getChildAt(0);
            LayoutParams initParams = (LayoutParams) child.getLayoutParams();
            LayoutParams params = new LayoutParams(initParams.width, initParams.height, initParams.weight);
            // Check orientation for proper margins
            if (getOrientation() == LinearLayout.HORIZONTAL) {
                params.setMargins(0, 0, -oneDP, 0);
            } else {
                params.setMargins(0, 0, 0, -oneDP);
            }
            child.setLayoutParams(params);
            // Check orientation for proper layout
            if (getOrientation() == LinearLayout.HORIZONTAL) {
                updateBackground(getChildAt(0), R.drawable.radio_checked_left, R.drawable.radio_unchecked_left);
            } else {
                updateBackground(getChildAt(0), R.drawable.radio_checked_top, R.drawable.radio_unchecked_top);
            }
            for (int i = 1; i < count - 1; i++) {
                // Check orientation for proper layout
                if (getOrientation() == LinearLayout.HORIZONTAL) {
                    updateBackground(getChildAt(i), R.drawable.radio_checked_middle, R.drawable.radio_unchecked_middle);
                } else {
                    // Middle radiobutton when checked is the same as horizontal.
                    updateBackground(getChildAt(i), R.drawable.radio_checked_middle, R.drawable.radio_unchecked_middle_vertical);
                }
                View child2 = getChildAt(i);
                initParams = (LayoutParams) child2.getLayoutParams();
                params = new LayoutParams(initParams.width, initParams.height, initParams.weight);
                // Check orientation for proper margins
                if (getOrientation() == LinearLayout.HORIZONTAL) {
                    params.setMargins(0, 0, -oneDP, 0);
                } else {
                    params.setMargins(0, 0, 0, -oneDP);
                }
                child2.setLayoutParams(params);
            }
            // Check orientation for proper layout
            if (getOrientation() == LinearLayout.HORIZONTAL) {
                updateBackground(getChildAt(count - 1), R.drawable.radio_checked_right, R.drawable.radio_unchecked_right);
            } else {
                updateBackground(getChildAt(count - 1), R.drawable.radio_checked_bottom, R.drawable.radio_unchecked_bottom);
            }
        } else if (count == 1) {
            updateBackground(getChildAt(0), R.drawable.radio_checked_default, R.drawable.radio_unchecked_default);
        }
    }

    private void updateBackground(View view, int checked, int unchecked) {
        //Set text color
        ColorStateList colorStateList = new ColorStateList(new int[][]{
                {android.R.attr.state_pressed},
                {-android.R.attr.state_pressed, -android.R.attr.state_checked},
                {-android.R.attr.state_pressed, android.R.attr.state_checked}},
                new int[]{Color.GRAY, mTintColor, mCheckedTextColor});
        ((Button) view).setTextColor(colorStateList);

        //Redraw with tint color
        Drawable checkedDrawable = resources.getDrawable(checked).mutate();
        Drawable uncheckedDrawable = resources.getDrawable(unchecked).mutate();
        ((GradientDrawable) checkedDrawable).setColor(mTintColor);
        ((GradientDrawable) uncheckedDrawable).setStroke(oneDP, mTintColor);

        //Create drawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-android.R.attr.state_checked}, uncheckedDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, checkedDrawable);

        //Set button background
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
    }
}
