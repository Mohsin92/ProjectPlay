package com.varshaaweblabs.ecommerce.OrderDetails.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.varshaaweblabs.ecommerce.OrderDetails.Model.GetOrder_Association;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.GetOrder_Data;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.OrderItemRow;
import com.varshaaweblabs.ecommerce.OrderDetails.View.Order_Full_Details_Activity;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinesh on 30/10/17.
 */

public class OrderHeaderListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<GetOrder_Data> order_list;
    List<OrderItemRow> oredr_item_list = new ArrayList<>();
    OrderItemsListAdapter order_item_adapter;


    public OrderHeaderListAdapter(Context mContext, List<GetOrder_Data> order_list) {
        this.mContext = mContext;
        this.order_list = order_list;
        mInflater = LayoutInflater.from(this.mContext);

    }


    @Override
    public int getCount() {
        return order_list.size();
    }

    @Override
    public Object getItem(int i) {
        return order_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class Holder {
        TextView tv_order_ref_no, tv_details, tv_order_date;
        ListView lv_order_prod_list;
        LinearLayout ll_details;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        final Holder holder;

        if (row == null) {
            row = mInflater.inflate(R.layout.row_order_header_section, null);
            holder = new Holder();
            holder.tv_order_ref_no = (TextView) row.findViewById(R.id.tv_order_ref_no);
            holder.tv_details = (TextView) row.findViewById(R.id.tv_details);
            holder.tv_order_date = (TextView) row.findViewById(R.id.tv_order_date);
            holder.lv_order_prod_list = (ListView) row.findViewById(R.id.lv_order_prod_list);
            holder.ll_details = (LinearLayout) row.findViewById(R.id.ll_details);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }
        final GetOrder_Data order_resp_list = order_list.get(i);

        holder.tv_order_ref_no.setText("Order ID " + order_resp_list.getReference().toString());
        holder.tv_order_date.setText("Order Date " + order_resp_list.getDateUpd().toString());
        String del_date = order_resp_list.getDeliveryDate().toString();
        String status = order_resp_list.getCurrentState().toString();

        final GetOrder_Association order_ass_data = (GetOrder_Association) order_list.get(i).getAssociations();
        oredr_item_list.clear();


        for (int j = 0; j < order_ass_data.getOrderRows().size(); j++) {
            oredr_item_list.add(order_ass_data.getOrderRows().get(j));

        }

        order_item_adapter = new OrderItemsListAdapter(mContext, oredr_item_list, del_date, status);
        holder.lv_order_prod_list.setAdapter(order_item_adapter);


        holder.ll_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.ORDER_ID=order_resp_list.getId().toString();
                Toast.makeText(mContext,"ID " +Constants.ORDER_ID,Toast.LENGTH_SHORT).show();
                Intent order_full_details = new Intent(mContext, Order_Full_Details_Activity.class);
                mContext.startActivity(order_full_details);
            }
        });

        return row;
    }
}
