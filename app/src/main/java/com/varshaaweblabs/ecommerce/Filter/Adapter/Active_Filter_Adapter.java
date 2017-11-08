package com.varshaaweblabs.ecommerce.Filter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varshaaweblabs.ecommerce.Filter.Model.Active_Filter;
import com.varshaaweblabs.ecommerce.Filter.Model.Active_Filter_List;
import com.varshaaweblabs.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinesh on 6/10/17.
 */

public class Active_Filter_Adapter extends BaseAdapter {

    private Context mContext;
    List<Active_Filter> active_filter;
    ArrayList<Active_Filter_List> actve_filterlist = new ArrayList<>();
    private LayoutInflater mInflater;
    LinearLayout filter_view;
    ArrayList<Object> tag_id=new ArrayList<>();
    private Helper mListener;

    public Active_Filter_Adapter(Context mContext, List<Active_Filter> active_filter, Helper mListener) {
        this.mContext = mContext;
        this.active_filter = active_filter;
        this.mListener = mListener;
        mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return active_filter.size();
    }

    @Override
    public Object getItem(int i) {
        return active_filter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public static class Holder {
        TextView tv_filters;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        Holder holder;
        actve_filterlist.clear();

        if (row == null) {

            row = mInflater.inflate(R.layout.row_active_filters, null);
            holder = new Holder();


            holder.tv_filters = (TextView) row.findViewById(R.id.tv_filters);
            filter_view = (LinearLayout) row.findViewById(R.id.filter_view);
            row.setTag(holder);

        } else {
            holder = (Holder) row.getTag();
        }


        final Active_Filter active_filters_data = active_filter.get(i);
        holder.tv_filters.setText(active_filters_data.getLabel());
        for (int j = 0; j < active_filters_data.getList_active_filters().size(); j++) {
            actve_filterlist.add(active_filters_data.getList_active_filters().get(j));
        }

        setTags(actve_filterlist);

        return row;
    }

    public void setTags(final ArrayList<Active_Filter_List> taglist) {
        filter_view.removeAllViews();

        final ArrayList<Active_Filter_List> List = new ArrayList<>();
        List.addAll(taglist);

        for (int i = 0; i < List.size(); i++) {
            final View child = mInflater.inflate(R.layout.row_filter_tags, null);
            final TextView tv_filter_tag = (TextView) child.findViewById(R.id.tv_filter_tag);
            tv_filter_tag.setTag(taglist.get(i).getValue());
            tv_filter_tag.setText(taglist.get(i).getLabel() + "  x  ");
            filter_view.addView(child);


            tv_filter_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ViewGroup) child.getParent()).removeView(child);
                   tag_id.add(tv_filter_tag.getTag());
                    if (mListener != null) {
                        mListener.OnTagSelected(tag_id);
                    }


                }
            });

        }

    }


    public interface Helper {
        void OnTagSelected(ArrayList id);

    }
}
