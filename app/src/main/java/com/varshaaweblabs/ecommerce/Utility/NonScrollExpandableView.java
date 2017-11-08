package com.varshaaweblabs.ecommerce.Utility;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

/**
 * Created by mohsin on 22/2/17.
 */

public class NonScrollExpandableView extends ExpandableListView {

    public NonScrollExpandableView(Context context) {
        super(context);
    }

    public NonScrollExpandableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollExpandableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = View.MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}
