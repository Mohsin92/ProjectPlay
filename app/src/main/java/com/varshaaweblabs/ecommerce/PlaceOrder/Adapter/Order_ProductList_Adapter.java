package com.varshaaweblabs.ecommerce.PlaceOrder.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Product;
import com.varshaaweblabs.ecommerce.R;

import java.util.List;

/**
 * Created by dinesh on 13/10/17.
 */

public class Order_ProductList_Adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Cart_Product> product;

    public Order_ProductList_Adapter(Context mContext, List<Cart_Product> product) {
        this.mContext = mContext;
        this.product = product;
        mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return product.size();
    }

    @Override
    public Object getItem(int i) {
        return product.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        Holder holder;

        if (row == null) {
            row = mInflater.inflate(R.layout.row_order_product_list_item, null);
            holder = new Order_ProductList_Adapter.Holder();
            holder.tv_cart_product_name = (TextView) row.findViewById(R.id.tv_cart_product_name);
            holder.tv_cart_brandname = (TextView) row.findViewById(R.id.tv_cart_brandname);
            holder.tv_cart_totalprice = (TextView) row.findViewById(R.id.tv_cart_totalprice);
            holder.tv_cart_discount_price = (TextView) row.findViewById(R.id.tv_cart_discount_price);
            holder.tv_cart_offer = (TextView) row.findViewById(R.id.tv_cart_offer);
            holder.product_photo = (ImageView) row.findViewById(R.id.property_agent_photo);
            holder.tv_qty = (TextView) row.findViewById(R.id.tv_qty);
            holder.tv_size_cell = (TextView) row.findViewById(R.id.tv_size_cell);
            holder.tv_color_cell = (TextView) row.findViewById(R.id.tv_color_cell);
            row.setTag(holder);

        } else {
            holder = (Order_ProductList_Adapter.Holder) row.getTag();
        }

        final Cart_Product products = product.get(i);

        holder.tv_cart_product_name.setText(products.getName());
        holder.tv_cart_brandname.setText(products.getManufacturerName());
        holder.tv_cart_totalprice.setText("$" + products.getTotal());
        holder.tv_qty.setText("Qty:" + products.getQuantityWanted());

        String string = products.getAttributesSmall();
        String[] parts = string.split("-");
        String size = parts[0];
        String color = parts[1];

        holder.tv_size_cell.setText("Size:" + size);
        holder.tv_color_cell.setText("color:" + color);

        if (!products.getReductionType().equalsIgnoreCase("")) {
            holder.tv_cart_discount_price.setPaintFlags(holder.tv_cart_discount_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            int qty = Integer.parseInt(products.getCartQuantity());
            Double price = new Double(products.getRegularPrice().toString());
            Double total_discount_price = qty * price;
            holder.tv_cart_discount_price.setText("$ " + total_discount_price);
            holder.tv_cart_offer.setText(products.getDiscountPercentageAbsolute());
        } else {
            holder.tv_cart_discount_price.setVisibility(View.GONE);
            holder.tv_cart_offer.setVisibility(View.GONE);
        }

        String img_url = products.getImages().get(0).getBySize().getCartDefault().getUrl();

        Glide.with(mContext).load(getUrlWithHeaders(img_url)).into(holder.product_photo);

        return row;

    }

    static GlideUrl getUrlWithHeaders(String url) {
        return new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .build());
    }

    public static class Holder {
        TextView tv_cart_product_name, tv_cart_brandname, tv_cart_totalprice, tv_cart_discount_price, tv_cart_offer, tv_size_cell, tv_color_cell, tv_qty;
        ImageView product_photo;
    }

}

