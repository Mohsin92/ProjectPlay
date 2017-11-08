package com.varshaaweblabs.ecommerce.Filter.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.varshaaweblabs.ecommerce.Filter.Model.Filters;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dinesh on 9/9/17.
 */

public class Sub_FilterAdapter extends BaseAdapter {
    List<Filters> filters;
    private HashMap<Integer, Boolean> myChecked = new HashMap<Integer, Boolean>();
    private LayoutInflater mInflater;
    Context context;

    public Sub_FilterAdapter(Context context, List<Filters> filters) {
        this.context = context;
        this.filters = filters;
        mInflater = LayoutInflater.from(this.context);
        for (int i = 0; i < filters.size(); i++) {
            myChecked.put(i, false);
        }
    }


    public void toggleChecked(int position) {
        if (myChecked.get(position)) {
            myChecked.put(position, false);
        } else {
            myChecked.put(position, true);
        }

        notifyDataSetChanged();
    }

    public List<Integer> getCheckedItemPositions() {
        List<Integer> checkedItemPositions = new ArrayList<Integer>();

        for (int i = 0; i < myChecked.size(); i++) {
            if (myChecked.get(i)) {
                (checkedItemPositions).add(i);
            }
        }

        return checkedItemPositions;
    }

    public List<String> getCheckedItemsID() {
        List<String> checkedItems = new ArrayList<String>();

        for (int i = 0; i < myChecked.size(); i++) {
            if (myChecked.get(i)) {
                (checkedItems).add(filters.get(i).getValue());
            }
        }

        return checkedItems;
    }


    public void removeChecked(int posi) {
        for (int i = 0; i < myChecked.size(); i++) {
            if (myChecked.get(i)) {
                myChecked.remove(i);
            }
        }
    }

    public ArrayList<String> getCheckedItemsNAMES() {
        ArrayList<String> checkedItems = new ArrayList<String>();

        for (int i = 0; i < myChecked.size(); i++) {
            if (myChecked.get(i)) {
                (checkedItems).add(filters.get(i).getLabel());
            }
        }

        return checkedItems;
    }

    public String checkedPosition() {
        String checkedPosition = null;
        for (int i = 0; i < myChecked.size(); i++) {
            if (myChecked.get(i)) {
                checkedPosition = filters.get(i).toString();
            }
        }
        return checkedPosition;
    }

    public String getCheckedValues() {
        String checkedValues = null;

        for (int i = 0; i < myChecked.size(); i++) {
            if (myChecked.get(i)) {
                checkedValues = filters.get(i).getValue();
            }
        }

        return checkedValues;
    }

    public String getCheckedNAMES() {
        String checkedItems = null;
        for (int i = 0; i < myChecked.size(); i++) {
            if (myChecked.get(i)) {
                checkedItems = filters.get(i).getLabel();
            }

        }

        return checkedItems;
    }

    public void clearData() {
        // clear the data
        filters.clear();
    }


    @Override
    public int getCount() {
        return filters.size();
    }

    @Override
    public Object getItem(int i) {
        return filters.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class Holder {
        CheckedTextView ctv_category;
        TextView cat_color;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        Holder holder;

        if (row == null) {

            row = mInflater.inflate(R.layout.filter_categorycheckbox, null);
            holder = new Holder();

            holder.ctv_category = (CheckedTextView) row.findViewById(R.id.ctv_checkbox);
            holder.cat_color = (TextView) row.findViewById(R.id.cat_color);

            row.setTag(holder);

        } else {
            holder = (Holder) row.getTag();
        }
        final Filters filters_data = filters.get(i);
        holder.ctv_category.setText(filters_data.getLabel() + "  (" + filters_data.getMagnitude() + ")");
        Constants.LABEL=filters_data.getFacetLabel();

        if (filters_data.getFacetLabel().equalsIgnoreCase("Color")) {
            holder.cat_color.setVisibility(View.VISIBLE);
            GradientDrawable bgShape = (GradientDrawable) holder.cat_color.getBackground();
            bgShape.setColor(Color.parseColor(filters_data.getProperties().getColor()));
        }

        Boolean checked = myChecked.get(i);
        if (checked != null) {
            holder.ctv_category.setChecked(checked);
        }




        return row;
    }
}
