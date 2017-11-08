package com.varshaaweblabs.ecommerce.PlaceOrder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Added;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_List;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Presenter.GetAllAddressPresenter;
import com.varshaaweblabs.ecommerce.PlaceOrder.Presenter.IGetAllAddressInterface;
import com.varshaaweblabs.ecommerce.PlaceOrder.View.Add_Edit_Address_Activity;
import com.varshaaweblabs.ecommerce.PlaceOrder.View.OrderSummeryView;
import com.varshaaweblabs.ecommerce.PlaceOrder.View.Show_Address_Activity;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dinesh on 14/10/17.
 */

public class GetAddressAdapter extends BaseAdapter implements IGetAllAddressInterface {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<User_Address_List> address_list;
    Cart_Coupon_Added coupon_list = new Cart_Coupon_Added();
    RadioButton selected = null;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;
    String add_id;
    GetAllAddressPresenter address_presenter;
    int selected_id;

    public GetAddressAdapter(Context mContext, List<User_Address_List> address_list, Cart_Coupon_Added coupon_list) {
        this.mContext = mContext;
        this.address_list = address_list;
        this.coupon_list = coupon_list;
        mInflater = LayoutInflater.from(this.mContext);
        pref_login = mContext.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();
        address_presenter = new GetAllAddressPresenter(mContext, this);
        address_presenter.attachView((OrderSummeryView.View) mContext);
    }

    @Override
    public int getCount() {
        return address_list.size();
    }

    @Override
    public Object getItem(int i) {
        return address_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public void getAllAddress(User_Address_model address_list) {
        if (mContext instanceof Show_Address_Activity) {
            ((Show_Address_Activity) mContext).getAllAddress(address_list);
        }
    }

    @Override
    public void deleteAddress(User_Address_model del_add_list) {
        if (mContext instanceof Show_Address_Activity) {
            ((Show_Address_Activity) mContext).deleteAddress(del_add_list);
        }
    }


    public static class Holder {
        TextView tv_name, tv_address, tv_phone, tv_edit_add, tv_delete_add;
        LinearLayout ll_tittle, ll_main;
        RadioButton check_text;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        final Holder holder;

        if (row == null) {
            row = mInflater.inflate(R.layout.row_address, null);
            holder = new GetAddressAdapter.Holder();
            holder.ll_tittle = (LinearLayout) row.findViewById(R.id.ll_tittle);
            holder.tv_name = (TextView) row.findViewById(R.id.tv_name);
            holder.tv_address = (TextView) row.findViewById(R.id.tv_address);
            holder.tv_phone = (TextView) row.findViewById(R.id.tv_phone);
            holder.check_text = (RadioButton) row.findViewById(R.id.check_text);
            holder.ll_main = (LinearLayout) row.findViewById(R.id.ll_main);
            holder.tv_edit_add = (TextView) row.findViewById(R.id.tv_edit_add);
            holder.tv_delete_add = (TextView) row.findViewById(R.id.tv_delete_add);
            row.setTag(holder);

        } else {
            holder = (Holder) row.getTag();
        }


        final User_Address_List address_data = address_list.get(i);
        holder.ll_tittle.setVisibility(View.GONE);
        holder.check_text.setVisibility(View.VISIBLE);
        final String add_id_1 = pref_login.getString(Constants.ADDRESS_ID, "");


        if (!add_id_1.equalsIgnoreCase("")) {
            if (add_id_1.equalsIgnoreCase(address_data.getId().toString())) {
                holder.check_text.setChecked(true);
                selected = holder.check_text;
            } else {
                holder.check_text.setChecked(false);

            }
        }
        holder.tv_edit_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Add_Id" + address_data.getId(), Toast.LENGTH_SHORT).show();
                Intent add_address = new Intent(mContext, Add_Edit_Address_Activity.class);
                add_address.putExtra("address_flag", false);
                add_address.putExtra("coupon_data", coupon_list);
                add_address.putExtra("selected_add", address_data);
                mContext.startActivity(add_address);
            }
        });
        holder.tv_delete_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id = pref_login.getString(Constants.USER_ID, "");
                String first_name = address_data.getFirstname();
                String last_name = address_data.getLastname();
                String add1 = address_data.getAddress1();
                String add2 = address_data.getAddress2();
                String postcode = address_data.getPostcode();
                String phone_mobile = address_data.getPhoneMobile();
                String city = address_data.getCity();
                String id_country = address_data.getIdCountry();
                String id_state = address_data.getIdState();
                String delete_add_id = address_data.getId().toString();
                String url = "<prestashop><address> <id>" + delete_add_id + "</id><id_customer>" + user_id + "</id_customer><id_country>" + id_country + "</id_country><id_state>" + id_state + "</id_state><alias>My Address</alias><firstname>" + first_name + "</firstname><lastname>" + last_name + "</lastname><address1>" + add1 + "</address1><address2>" + add2 + "</address2><postcode>" + postcode + "</postcode><city>" + city + "</city><company>test</company><phone>1235465</phone><phone_mobile>" + phone_mobile + "</phone_mobile><deleted>1</deleted></address></prestashop>";
                address_presenter.deleteAddress(url);
            }
        });
        holder.check_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selected != null) {
                    selected.setChecked(false);
                    notifyDataSetChanged();
                }

                holder.check_text.setChecked(true);
                selected = holder.check_text;
                add_id = address_data.getId().toString();
                editor_login.putString(Constants.ADDRESS_ID, add_id);
                editor_login.commit();

            }
        });

        holder.tv_name.setText(address_data.getFirstname() + " " + address_data.getLastname());
        holder.tv_address.setText(address_data.getAddress1());
        holder.tv_phone.setText(address_data.getPhoneMobile());
        return row;
    }

}
