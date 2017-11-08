package com.varshaaweblabs.ecommerce.Filter.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.squareup.picasso.Picasso;
import com.varshaaweblabs.ecommerce.Filter.Model.Product;
import com.varshaaweblabs.ecommerce.ProductFullDetails.View.ProductfullDetails;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;

import java.util.ArrayList;

/**
 * Created by mohsin on 16/6/17.
 */

public class FilterProductList_Adapter extends RecyclerView.Adapter<FilterProductList_Adapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Product> product;
    Picasso picasso;
    boolean flag_list;

    public FilterProductList_Adapter(Context mContext, ArrayList<Product> product, boolean flag_list) {
        this.mContext = mContext;
        this.product = product;
        this.flag_list = flag_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (flag_list) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_list_item, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_grid_item, parent, false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Product product_list = product.get(position);
        holder.title.setText(product_list.getName());
//        holder.price.setText(product_list.getPriceAmount().toString());

        String prod_image = product_list.getIdImage();
        String img_url = prod_image.replaceAll("-", "/");

        if (!product_list.getPriceAmount().equals(product_list.getRegularPriceAmount())) {
            holder.product_price_disc.setVisibility(View.VISIBLE);
            holder.tv_disc_per_row.setVisibility(View.VISIBLE);
            holder.price.setText("$ " + product_list.getPriceAmount().toString());
            holder.price.setTextColor(Color.parseColor("#696969"));
            holder.product_price_disc.setPaintFlags(holder.product_price_disc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.product_price_disc.setText("$ " + product_list.getRegularPrice().toString());
            holder.price.setTextColor(Color.parseColor("#000000"));
            holder.tv_disc_per_row.setText("SAVE " + product.get(position).getDiscountPercentage().toString());
        } else {
            holder.product_price_disc.setVisibility(View.GONE);
            holder.tv_disc_per_row.setVisibility(View.GONE);
            holder.price.setText("$ " + product_list.getPriceAmount().toString());
        }


        holder.rl_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prod_details = new Intent(mContext, ProductfullDetails.class);
                prod_details.putExtra("selected_data", product_list);
                mContext.startActivity(prod_details);
            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
                Intent prod_details = new Intent(mContext, ProductfullDetails.class);
                prod_details.putExtra("selected_data", product_list);
                mContext.startActivity(prod_details);
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext,"Product",Toast.LENGTH_SHORT).show();
//                Intent prod_details=new Intent(mContext, ProductfullDetails.class);
//                mContext.startActivity(prod_details);
//
//            }
//        });

//        String url=Constants.BASE_URL+"images/products/"+prod_id+"/"+prod_image+"";
        // loading album cover using Glide library


//        Log.e("Image URL",url);

        Glide.with(mContext).load(getUrlWithHeaders(Constants.BASE_URL + "images/products/" + img_url + "")).into(holder.thumbnail);

    }

    static GlideUrl getUrlWithHeaders(String url) {
        return new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .build());
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, product_price_disc, tv_disc_per_row;
        RelativeLayout rl_lay;
        public ImageView thumbnail, overflow;


        public MyViewHolder(View view) {
            super(view);
            rl_lay = (RelativeLayout) view.findViewById(R.id.grid_lay);
            title = (TextView) view.findViewById(R.id.product_name);
            price = (TextView) view.findViewById(R.id.product_price);
            thumbnail = (ImageView) view.findViewById(R.id.product_image);
            product_price_disc = (TextView) view.findViewById(R.id.product_price_disc);
            tv_disc_per_row = (TextView) view.findViewById(R.id.tv_disc_per_row);
        }
    }
}
