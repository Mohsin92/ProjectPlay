package com.varshaaweblabs.ecommerce.Payment.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Added;
import com.varshaaweblabs.ecommerce.Payment.Model.PaymentModule_Data;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dinesh on 28/10/17.
 */

public class Payment_Option_Adapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<PaymentModule_Data> payment_data_list;
    Cart_Coupon_Added coupon_list = new Cart_Coupon_Added();
    RadioButton selected = null;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;
    String pay_id;


    public Payment_Option_Adapter(Context mContext, List<PaymentModule_Data> payment_data_list, Cart_Coupon_Added coupon_list) {
        this.mContext = mContext;
        this.payment_data_list = payment_data_list;
        this.coupon_list = coupon_list;
        mInflater = LayoutInflater.from(this.mContext);
        pref_login = mContext.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();
    }

    @Override
    public int getCount() {
        return payment_data_list.size();
    }

    @Override
    public Object getItem(int i) {
        return payment_data_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public static class Holder {
        RadioButton rd_pay;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        final Holder holder;

        if (row == null) {
            row = mInflater.inflate(R.layout.row_radio_pay_options, null);
            holder = new Holder();
            holder.rd_pay = (RadioButton) row.findViewById(R.id.rd_pay);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }

        final PaymentModule_Data pay_data = payment_data_list.get(i);

        holder.rd_pay.setText(pay_data.getPayment().toString());

        pay_id = pay_data.getId().toString();

//        if (pay_id.equalsIgnoreCase("1")) {
//            holder.rd_pay.setChecked(true);
//            selected = holder.rd_pay;
//            Constants.PAY_OPTION_PAYMENT = pay_data.getPayment().toString();
//            Constants.PAY_OPTION_MODULE = pay_data.getModule().toString();
//        } else {
//            holder.rd_pay.setChecked(false);
//
//        }


        holder.rd_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selected != null) {
                    selected.setChecked(false);
                    holder.rd_pay.setChecked(false);
                }

                holder.rd_pay.setChecked(true);
                selected = holder.rd_pay;
                pay_id = pay_data.getId().toString();
                Constants.PAY_ID = pay_id;
                Toast.makeText(mContext, "Selected ID" + pay_id, Toast.LENGTH_SHORT).show();
                Constants.PAY_OPTION_PAYMENT = pay_data.getPayment().toString();
                Constants.PAY_OPTION_MODULE = pay_data.getModule().toString();
            }
        });
        return row;
    }


}
