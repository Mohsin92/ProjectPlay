package com.varshaaweblabs.ecommerce.OrderDetails.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.OrderItemRow;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.SplashScreen.Model.OrderStates_Resp;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dinesh on 30/10/17.
 */

public class OrderItemsListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<OrderItemRow> order_item_list;
    String del_date;
    String order_status;
    SharedPreferences pref_product;
    SharedPreferences.Editor editor_prodmeta;


    public OrderItemsListAdapter(Context mContext, List<OrderItemRow> order_item_list, String del_date, String order_status) {
        this.mContext = mContext;
        this.order_item_list = order_item_list;
        this.del_date = del_date;
        this.order_status = order_status;
        mInflater = LayoutInflater.from(this.mContext);
        pref_product = mContext.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_prodmeta = pref_product.edit();
    }


    @Override
    public int getCount() {
        return order_item_list.size();
    }

    @Override
    public Object getItem(int i) {
        return order_item_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class Holder {
        TextView tv_order_product_name, tv_pay_status, tv_delivery, tv_delvery_date;
        Button btn_place_order;
        ImageView iv_image_product;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        Holder holder;

        if (row == null) {
            row = mInflater.inflate(R.layout.row_order_item_list, null);
            holder = new Holder();
            holder.tv_order_product_name = (TextView) row.findViewById(R.id.tv_order_product_name);
            holder.tv_pay_status = (TextView) row.findViewById(R.id.tv_pay_status);
            holder.tv_delivery = (TextView) row.findViewById(R.id.tv_delivery);
            holder.tv_delvery_date = (TextView) row.findViewById(R.id.tv_delvery_date);
            holder.iv_image_product = (ImageView) row.findViewById(R.id.iv_image_product);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }
        final OrderItemRow order_prod_data = order_item_list.get(i);

        Gson gson = new Gson();
        OrderStates_Resp curr_state_data = gson.fromJson(pref_product.getString(Pref_Data.ORDER_STATES, ""), OrderStates_Resp.class);
        for (int j = 0; j < curr_state_data.getOrderStates().size(); j++) {
            if (curr_state_data.getOrderStates().get(j).getId().toString().equalsIgnoreCase(order_status)) {
                holder.tv_pay_status.setText(curr_state_data.getOrderStates().get(j).getName());
                holder.tv_pay_status.setTextColor(Color.parseColor(curr_state_data.getOrderStates().get(j).getColor()));

            }
        }


        String str = order_prod_data.getProductName();
        String[] parts = str.split("-");
        String prod_name = parts[0];
        holder.tv_order_product_name.setText(prod_name);
        String[] parts_date = del_date.split(" ");
        String date = parts_date[0];
        holder.tv_delvery_date.setText(date);

        String img_url = order_prod_data.getDefaultImage().toString();

        Glide.with(mContext).load(getUrlWithHeaders(img_url)).into(holder.iv_image_product);
        return row;
    }

    static GlideUrl getUrlWithHeaders(String url) {
        return new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .build());
    }
}
