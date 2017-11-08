package com.varshaaweblabs.ecommerce.OrderDetails.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.OrderItemRow;
import com.varshaaweblabs.ecommerce.R;

import java.util.List;


/**
 * Created by dinesh on 31/10/17.
 */

public class OrderFullDetailsAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<OrderItemRow> order_list;

    public OrderFullDetailsAdapter(Context mContext, List<OrderItemRow> order_list) {
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
        ImageView property_agent_photo;
        TextView tv_cart_product_name, tv_size_cell, tv_color_cell, tv_qty, tv_cart_totalprice;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        final Holder holder;

        if (row == null) {
            row = mInflater.inflate(R.layout.row_order_full_prod_item, null);
            holder = new Holder();
            holder.tv_cart_product_name = (TextView) row.findViewById(R.id.tv_order_item_product_name);
            holder.tv_size_cell = (TextView) row.findViewById(R.id.tv_order_item_size_cell);
            holder.tv_color_cell = (TextView) row.findViewById(R.id.tv_order_item_color_cell);
            holder.tv_qty = (TextView) row.findViewById(R.id.tv_order_item_qty);
            holder.tv_cart_totalprice = (TextView) row.findViewById(R.id.tv_order_item_totalprice);
            holder.property_agent_photo = (ImageView) row.findViewById(R.id.iv_prod_image);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }
        OrderItemRow order_product_data = order_list.get(i);
        String str = order_product_data.getProductName();
        String[] parts = str.split("-");
        String prod_name = parts[0];
        holder.tv_cart_product_name.setText(prod_name);
        String prod_size = parts[1];
        holder.tv_size_cell.setText(prod_size);
        String prod_color = parts[2];
        holder.tv_color_cell.setText(prod_color);
        String img_url = order_product_data.getDefaultImage().toString();
        holder.tv_qty.setText(" Qty : " + order_product_data.getProductQuantity());
        holder.tv_cart_totalprice.setText("$" + Double.valueOf(order_product_data.getProductPrice()).toString());

        Glide.with(mContext).load(getUrlWithHeaders(img_url)).into(holder.property_agent_photo);
        return row;

    }

    static GlideUrl getUrlWithHeaders(String url) {
        return new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .build());
    }
}
