package com.varshaaweblabs.ecommerce.Cart.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Product;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;

import java.util.List;

/**
 * Created by mohsin on 26/9/17.
 */

public class AddToCart_Adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Cart_Product> product;
    private onSpinnerItemHelper mSpinnerItemHelper;
    private onDeleteItemHelper onDeleteItemHelper;
    String qty;


    public AddToCart_Adapter(Context mContext, List<Cart_Product> product, onSpinnerItemHelper spinnerItemHelper, onDeleteItemHelper onDeleteItemHelper) {
        this.mContext = mContext;
        this.product = product;
        mInflater = LayoutInflater.from(this.mContext);
        mSpinnerItemHelper = spinnerItemHelper;
        this.onDeleteItemHelper = onDeleteItemHelper;
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
        final Holder holder;

        if (row == null) {

            row = mInflater.inflate(R.layout.row_cart_item, null);
            holder = new AddToCart_Adapter.Holder();

            holder.tv_cart_product_name = (TextView) row.findViewById(R.id.tv_cart_product_name);
            holder.tv_cart_brandname = (TextView) row.findViewById(R.id.tv_cart_brandname);
            holder.tv_cart_totalprice = (TextView) row.findViewById(R.id.tv_cart_totalprice);
            holder.tv_cart_discount_price = (TextView) row.findViewById(R.id.tv_cart_discount_price);
            holder.tv_cart_offer = (TextView) row.findViewById(R.id.tv_cart_offer);
            holder.product_photo = (ImageView) row.findViewById(R.id.property_agent_photo);
            holder.iv_cart_delete = (ImageView) row.findViewById(R.id.iv_cart_delete);
            holder.tv_size_cell = (TextView) row.findViewById(R.id.tv_size_cell);
            holder.tv_color_cell = (TextView) row.findViewById(R.id.tv_color_cell);
            holder.tv_cart_qty = (TextView) row.findViewById(R.id.tv_cart_qty);
            holder.iv_cust_icon = (ImageView) row.findViewById(R.id.iv_cust_icon);
            holder.iv_cart_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cart_Product cart_product = (Cart_Product) view.getTag();

                    if (onDeleteItemHelper != null) {
                        onDeleteItemHelper.onDeleteItemClick(cart_product);
                    }


                }
            });

            row.setTag(holder);

        } else {
            holder = (AddToCart_Adapter.Holder) row.getTag();
        }

        final Cart_Product products = product.get(i);


        holder.iv_cart_delete.setTag(products);

        holder.tv_cart_product_name.setText(products.getName());
        holder.tv_cart_brandname.setText(products.getManufacturerName());
        holder.tv_cart_totalprice.setText("$" + products.getTotal());

        String string = products.getAttributesSmall();
        String[] parts = string.split("-");
        String size = parts[0];
        String color = parts[1];

        holder.tv_size_cell.setText("Size :" + size);
        holder.tv_color_cell.setText("color :" + color);

        if (products.getIdCustomization() == null) {
            holder.iv_cust_icon.setVisibility(View.GONE);
        } else {
            holder.iv_cust_icon.setVisibility(View.VISIBLE);
        }

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
        final String qty = products.getCartQuantity();
        holder.tv_cart_qty.setText("Qty : " + qty);


        //TODO Login of quantity Dialog Bog
        holder.tv_cart_qty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                LayoutInflater inflater = LayoutInflater.from(mContext);

                final View dialoglayout = inflater.inflate(R.layout.row_cart_qty,
                        null);
                final EditText etText = (EditText) dialoglayout
                        .findViewById(R.id.et_text);
                final RadioGroup radioGroup = (RadioGroup) dialoglayout.findViewById(R.id.rg_qty);

                int count = radioGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    RadioButton button = (RadioButton) radioGroup.getChildAt(i);
                    RadioButton button_more = (RadioButton) radioGroup.getChildAt(5);
                    int qty_int = Integer.parseInt(qty);
                    if (qty_int < 6) {
                        button_more.setChecked(false);// the ID you're looking for
                        etText.setVisibility(View.GONE);
                        if (button.getText().toString().equalsIgnoreCase(qty)) {
                            button.setChecked(true);

                        } else {
                            button.setChecked(false);
                        }

                    } else {

                        button_more.setChecked(true);
                        etText.setVisibility(View.VISIBLE);
                        etText.setText(qty);
                    }
                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // checkedId is the RadioButton selected
                        RadioButton rb = (RadioButton) dialoglayout.findViewById(checkedId);
                        String qty = rb.getText().toString();
                        Constants.SELECTED_QTY = qty;

                        if (qty.equalsIgnoreCase("more")) {
                            etText.setVisibility(View.VISIBLE);

                        } else {
                            etText.setVisibility(View.GONE);
                        }


                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Select Qty ");
                builder.setView(dialoglayout);
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (etText.getVisibility() == View.VISIBLE) {
                            if (etText.getText().length() == 0) {
                                Toast.makeText(mContext, "Please enter text", Toast.LENGTH_SHORT).show();
                            } else {
                                Constants.SELECTED_QTY = etText.getText().toString();
                                Toast.makeText(mContext, Constants.SELECTED_QTY, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        } else {
                            Toast.makeText(mContext, Constants.SELECTED_QTY, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                        if (mSpinnerItemHelper != null) {
                            mSpinnerItemHelper.onSpinnerItemSelected(Constants.SELECTED_QTY, products.getId(), products.getIdProductAttribute());
                        }
                    }

                });

                AlertDialog alert = builder.create();
                alert.show();

            }

        });


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
        TextView tv_cart_product_name, tv_cart_brandname, tv_cart_totalprice, tv_cart_discount_price, tv_cart_offer, tv_size_cell, tv_color_cell, tv_cart_qty;
        ImageView product_photo, iv_cart_delete, iv_cust_icon;

    }


    public class testAdapter {

        public testAdapter(int id, String attrName) {
            this.id = id;
            this.attrName = attrName;
        }

        public int getId() {
            return id;
        }

        int id;
        String attrName;

        @Override
        public String toString() {
            return attrName;
        }

    }

    public interface onSpinnerItemHelper {
        void onSpinnerItemSelected(String qty_selected, String product_id, String id_product_attribute);
    }

    public interface onDeleteItemHelper {
        void onDeleteItemClick(Cart_Product cart_product);
    }
}
