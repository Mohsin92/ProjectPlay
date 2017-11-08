package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_Data;
import com.varshaaweblabs.ecommerce.R;

import java.util.ArrayList;

/**
 * Created by mohsin on 22/6/17.
 */

public class SubCategory_Adapter extends RecyclerView.Adapter<SubCategory_Adapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Category_Data> category_responses;

    public SubCategory_Adapter(Context mContext, ArrayList<Category_Data> category_responses) {
        this.mContext = mContext;
        this.category_responses = category_responses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_group, parent, false);

        return new SubCategory_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Category_Data subcategory_list = category_responses.get(position);
        holder.title.setText(subcategory_list.getName());
    }

    @Override
    public int getItemCount() {
        return category_responses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_groupname);

        }
    }

}
