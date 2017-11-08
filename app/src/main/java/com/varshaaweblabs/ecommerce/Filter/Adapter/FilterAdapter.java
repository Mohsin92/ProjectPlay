package com.varshaaweblabs.ecommerce.Filter.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.varshaaweblabs.ecommerce.Filter.Model.Sub_Filter;
import com.varshaaweblabs.ecommerce.R;

import java.util.ArrayList;

/**
 * Created by dinesh on 8/9/17.
 */

public class FilterAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<Sub_Filter> sub_filters;
    private LayoutInflater mInflater;
    TextView tv_category;

    public FilterAdapter(Context mContext, ArrayList<Sub_Filter> sub_filters) {
        this.mContext = mContext;
        this.sub_filters = sub_filters;
        mInflater = LayoutInflater.from(this.mContext);
    }


    @Override
    public int getCount() {
        return sub_filters.size();
    }

    @Override
    public Object getItem(int i) {
        return sub_filters.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class Holder {
//        TextView tv_category;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;


        if (row == null) {

            row = mInflater.inflate(R.layout.filter_list_item, null);


            tv_category = (TextView) row.findViewById(R.id.tv_category);

//            row.setTag(holder);
//
//        } else {
//            holder = (Holder) row.getTag();
//        }
        }
        final Sub_Filter sub_filter = sub_filters.get(i);
        tv_category.setText(sub_filter.getLabel());



        return row;
    }
}

