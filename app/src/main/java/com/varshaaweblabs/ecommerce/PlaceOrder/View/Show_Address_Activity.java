package com.varshaaweblabs.ecommerce.PlaceOrder.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Added;
import com.varshaaweblabs.ecommerce.PlaceOrder.Adapter.GetAddressAdapter;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_List;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Presenter.GetAllAddressPresenter;
import com.varshaaweblabs.ecommerce.PlaceOrder.Presenter.IGetAllAddressInterface;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dinesh on 14/10/17.
 */

public class Show_Address_Activity extends BaseActivity implements IGetAllAddressInterface, OrderSummeryView.View, View.OnClickListener {

    GetAllAddressPresenter address_presenter;
    ListView lv_address;
    GetAddressAdapter addressAdapter;
    ArrayList<User_Address_List> user_address_lists = new ArrayList<User_Address_List>();
    Button btn_cont_order;
    String add_id;
    LinearLayout ll_add_address, ll_order_track, ll_add_new_add;
    Cart_Coupon_Added coupon_list = new Cart_Coupon_Added();
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;
    Gson gson = new Gson();
    Boolean show_address_flag = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        address_presenter = new GetAllAddressPresenter(this, this);
        address_presenter.attachView(this);
        setContentView(R.layout.activity_get_address);


        pref_login = Show_Address_Activity.this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);

        Intent address = getIntent();
        coupon_list = (Cart_Coupon_Added) address.getSerializableExtra("coupon_data");
        show_address_flag = address.getBooleanExtra("show_address_flag", true);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Address");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lv_address = (ListView) findViewById(R.id.lv_address);
        btn_cont_order = (Button) findViewById(R.id.btn_cont_order);
        ll_add_address = (LinearLayout) findViewById(R.id.ll_add_address);
        ll_order_track = (LinearLayout) findViewById(R.id.ll_order_track);
        ll_add_new_add = (LinearLayout) findViewById(R.id.ll_add_new_add);
        ll_add_address.setOnClickListener(this);


        btn_cont_order.setOnClickListener(this);
        String user_id = pref_login.getString(Constants.USER_ID, "");
        User_Address_model add_data = gson.fromJson(pref_login.getString(Pref_Data.ADDRESS_DATA, ""), User_Address_model.class);


        //TODO flag for keep tracks of Activity
        if (show_address_flag) {
            ll_order_track.setVisibility(View.GONE);
            CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(
                    CoordinatorLayout.LayoutParams.MATCH_PARENT,
                    CoordinatorLayout.LayoutParams.MATCH_PARENT
            );
            Resources r = Show_Address_Activity.this.getResources();
            int px1 = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    56,
                    r.getDisplayMetrics()
            );
            int px2 = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    100,
                    r.getDisplayMetrics()
            );

            params.setMargins(0, px1, 0, px2);
            ll_add_new_add.setLayoutParams(params);
        } else {
            ll_order_track.setVisibility(View.VISIBLE);
            CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(
                    CoordinatorLayout.LayoutParams.MATCH_PARENT,
                    CoordinatorLayout.LayoutParams.MATCH_PARENT
            );
            Resources r = Show_Address_Activity.this.getResources();
            int px1 = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    112,
                    r.getDisplayMetrics()
            );
            int px2 = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    50,
                    r.getDisplayMetrics()
            );

            params.setMargins(0, px1, 0, px2);
            ll_add_new_add.setLayoutParams(params);

        }

        if (add_data == null) {
            try {
                address_presenter.getAllUserAddress(user_id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < add_data.getAddresses().size(); i++) {
                user_address_lists.add(add_data.getAddresses().get(i));
            }

            addressAdapter = new GetAddressAdapter(Show_Address_Activity.this, user_address_lists, coupon_list);
            lv_address.setAdapter(addressAdapter);
        }


        add_id = pref_login.getString(Constants.ADDRESS_ID, "");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cont_order:
                if (Constants.ADDRESS_CLASSNAME.toString().equalsIgnoreCase("class com.varshaaweblabs.ecommerce.Account.View.My_Account_Activity")) {
                    Intent address = new Intent(Show_Address_Activity.this, Constants.ADDRESS_CLASSNAME);
                    address.putExtra("coupon_data", coupon_list);
                    startActivity(address);
                } else if (Constants.ADDRESS_CLASSNAME.toString().equalsIgnoreCase("class com.varshaaweblabs.ecommerce.PlaceOrder.View.Activity_Order_Summery")) {
                    Intent order = new Intent(Show_Address_Activity.this, Constants.ADDRESS_CLASSNAME);
                    order.putExtra("order_flag", true);
                    order.putExtra("coupon_data", coupon_list);
                    startActivity(order);
                } else if (Constants.ADDRESS_CLASSNAME.toString().equalsIgnoreCase("")) {
                    Intent payment = new Intent(Show_Address_Activity.this, Activity_Order_Summery.class);
                    payment.putExtra("coupon_data", coupon_list);
                    startActivity(payment);
                }
                break;

            case R.id.ll_add_address:
                Intent add_address = new Intent(Show_Address_Activity.this, Add_Edit_Address_Activity.class);
                add_address.putExtra("coupon_data", coupon_list);
                add_address.putExtra("address_flag", true);
                startActivity(add_address);
                break;
        }
    }

    @Override
    public void getAllAddress(final User_Address_model address_list) {
        user_address_lists.clear();
        Show_Address_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < address_list.getAddresses().size(); i++) {
                    if (address_list.getAddresses().get(i).getDeleted().equalsIgnoreCase("0")) {
                        user_address_lists.add(address_list.getAddresses().get(i));
                    }
                }
                addressAdapter = new GetAddressAdapter(Show_Address_Activity.this, user_address_lists, coupon_list);
                lv_address.setAdapter(addressAdapter);
            }
        });
    }

    @Override
    public void deleteAddress(final User_Address_model del_add_list) {
        Show_Address_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String del_add_id = del_add_list.getAddresses().get(0).getId().toString();
                for (int i = 0; i < user_address_lists.size(); i++) {
                    if (del_add_id.equalsIgnoreCase(user_address_lists.get(i).getId().toString())) {
                        user_address_lists.remove(i);
                        addressAdapter.notifyDataSetChanged();
                    }
                }
                Toast.makeText(Show_Address_Activity.this, "Address Deleted Successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

