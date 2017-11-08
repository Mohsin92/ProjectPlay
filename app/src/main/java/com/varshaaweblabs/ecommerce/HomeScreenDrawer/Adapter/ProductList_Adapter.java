package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Product_Data;
import com.varshaaweblabs.ecommerce.ProductFullDetails.View.ProductfullDetails;
import com.varshaaweblabs.ecommerce.R;

import java.util.ArrayList;

/**
 * Created by mohsin on 16/6/17.
 */

public class ProductList_Adapter extends RecyclerView.Adapter<ProductList_Adapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Product_Data> product;
    Boolean flag_prod = false;

    public ProductList_Adapter(Context mContext, ArrayList<Product_Data> product) {
        this.mContext = mContext;
        this.product = product;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_prod_horizontal, parent, false);
        return new ProductList_Adapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ProductList_Adapter.ViewHolder holder, int position) {
        final Product_Data product_list = product.get(position);
        holder.title.setText(product_list.getName());
        holder.price.setText("$ " +product_list.getPrice());
//        String prod_image = product_list.getIdDefaultImage();
//        String img_url = prod_image.replaceAll("-", "/");
        holder.card_recomm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prod_details = new Intent(mContext, ProductfullDetails.class);
                prod_details.putExtra("selected_prod_data", product_list);
                prod_details.putExtra("flag_prod", flag_prod);
                mContext.startActivity(prod_details);
            }
        });
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prod_details = new Intent(mContext, ProductfullDetails.class);
                prod_details.putExtra("selected_prod_data", product_list);
                prod_details.putExtra("flag_prod", flag_prod);
                mContext.startActivity(prod_details);
            }
        });


//        Log.e("Image URL", img_url);
//
//
//        Glide.with(mContext).load(getUrlWithHeaders(Constants.BASE_URL + "images/products/" + img_url + "")).into(holder.thumbnail);
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


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title, price;
        public ImageView thumbnail;
        public CardView card_recomm;

        ViewHolder(final View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.recom_prod_tittle);
            this.card_recomm = (CardView) itemView.findViewById(R.id.card_recomm);
            this.price = (TextView) itemView.findViewById(R.id.product_price);
            this.thumbnail = (ImageView) itemView.findViewById(R.id.recom_prod_image);

        }
    }
}
