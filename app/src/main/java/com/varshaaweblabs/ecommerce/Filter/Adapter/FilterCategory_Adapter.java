package com.varshaaweblabs.ecommerce.Filter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.varshaaweblabs.ecommerce.Filter.Model.Filters;
import com.varshaaweblabs.ecommerce.R;

import java.util.List;

/**
 * Created by dinesh on 11/9/17.
 */

public class FilterCategory_Adapter extends BaseAdapter {
    private Context mContext;
    List<Filters> cat_filter;
    private LayoutInflater mInflater;

    public FilterCategory_Adapter(Context mContext, List<Filters> cat_filter) {
        this.mContext = mContext;
        this.cat_filter = cat_filter;
        mInflater = LayoutInflater.from(this.mContext);
    }


    @Override
    public int getCount() {
        return cat_filter.size();
    }

    @Override
    public Object getItem(int i) {
        return cat_filter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public static class Holder {
        TextView tv_category;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        Holder holder;


        if (row == null) {

            row = mInflater.inflate(R.layout.filter_list_item, null);
            holder = new Holder();


            holder.tv_category = (TextView) row.findViewById(R.id.tv_category);

            row.setTag(holder);

        } else {
            holder = (Holder) row.getTag();
        }
        final Filters filters_data = cat_filter.get(i);
        holder.tv_category.setText(filters_data.getLabel() + "   (" + filters_data.getMagnitude() + ")");
        return row;
    }
}
