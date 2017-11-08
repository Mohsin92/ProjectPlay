package com.varshaaweblabs.ecommerce.Account.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.MainActivity;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginCustomer_Data;
import com.varshaaweblabs.ecommerce.OrderDetails.View.User_Order_Activity;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;
import com.varshaaweblabs.ecommerce.PlaceOrder.View.Add_Edit_Address_Activity;
import com.varshaaweblabs.ecommerce.PlaceOrder.View.Show_Address_Activity;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

/**
 * Created by dinesh on 23/10/17.
 */

public class My_Account_Activity extends BaseActivity implements View.OnClickListener {
    AnimationDrawable animationDrawable;
    LinearLayout ll_gradient_layout;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    LoginCustomer_Data cust_data;
    String classname, add_id;
    LinearLayout ll_add_layout;
    TextView tv_user_name, tv_user_phone, tv_user_email, tv_show_info, tv_all_order, tv_all_wishlist, tv_all_return, tv_login_user_image, tv_login_user_name, tv_login_user_address, tv_login_user_phone;
    Button btn_logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activty_user_account);

        pref = this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor = pref.edit();

        ll_gradient_layout = (LinearLayout) findViewById(R.id.ll_gradient_layout);
        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_user_phone = (TextView) findViewById(R.id.tv_user_phone);
        tv_user_email = (TextView) findViewById(R.id.tv_user_email);
        tv_login_user_name = (TextView) findViewById(R.id.tv_login_user_name);
        tv_login_user_address = (TextView) findViewById(R.id.tv_login_user_address);
        tv_login_user_phone = (TextView) findViewById(R.id.tv_login_user_phone);
        btn_logout=(Button) findViewById(R.id.btn_logout);
        tv_show_info = (TextView) findViewById(R.id.tv_show_info);
        tv_all_order = (TextView) findViewById(R.id.tv_all_order);
        tv_all_wishlist = (TextView) findViewById(R.id.tv_all_wishlist);
        tv_all_return = (TextView) findViewById(R.id.tv_all_return);
        tv_login_user_image = (TextView) findViewById(R.id.tv_login_user_image);

        FloatingActionButton edit_user = (FloatingActionButton) findViewById(R.id.edit_profile);
        edit_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent signup = new Intent(My_Account_Activity.this, Add_Edit_User_Activity.class);
                signup.putExtra("signup_flag", false);
                startActivity(signup);
            }
        });

        setData();


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Constants.PROD_COMBINATIONID = "";

        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle(" ");

        animationDrawable = (AnimationDrawable) ll_gradient_layout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(1000);

        animationDrawable.start();
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if ((collapsingToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbar))) {
                    collapsingToolbar.setTitle("My Account");


                } else {
                    collapsingToolbar.setTitle(" ");


                }
            }

        });

        tv_show_info.setOnClickListener(this);
        tv_all_order.setOnClickListener(this);
        btn_logout.setOnClickListener(this);

    }

    private void setData() {

        String add_id_1 = pref.getString(Constants.ADDRESS_ID, "");
        Gson gson = new Gson();
        User_Address_model add_data = gson.fromJson(pref.getString(Pref_Data.ADDRESS_DATA, ""), User_Address_model.class);
        cust_data = gson.fromJson(pref.getString(Pref_Data.LOGIN_RESPONSE, ""), LoginCustomer_Data.class);
        String name = cust_data.getFirstname().toString();
        String first = name.substring(0, 1);
        tv_login_user_image.setText(first);
        tv_user_name.setText(cust_data.getFirstname().toString() + " " + cust_data.getLastname().toString());
        tv_user_email.setText(cust_data.getEmail());


        if (add_data != null) {
            for (int i = 0; i < add_data.getAddresses().size(); i++) {
                if (add_id_1.equalsIgnoreCase(add_data.getAddresses().get(i).getId().toString())) {
                    if (!add_id_1.equalsIgnoreCase("")) {
                        if (add_id_1.equalsIgnoreCase(add_data.getAddresses().get(i).getId().toString())) {
                            tv_login_user_name.setText(add_data.getAddresses().get(i).getFirstname() + " " + add_data.getAddresses().get(i).getLastname());
                            tv_login_user_address.setText(add_data.getAddresses().get(i).getAddress1());
                            tv_login_user_phone.setText(add_data.getAddresses().get(i).getPhoneMobile());
                            tv_user_phone.setText(add_data.getAddresses().get(i).getPhoneMobile());
                            add_id = add_data.getAddresses().get(i).getId().toString();
                            editor.putString(Constants.ADDRESS_ID, add_id);
                            editor.commit();
                        }

                    } else {
                        tv_login_user_name.setText(add_data.getAddresses().get(0).getFirstname() + " " + add_data.getAddresses().get(0).getLastname());
                        tv_login_user_address.setText(add_data.getAddresses().get(0).getAddress1());
                        tv_login_user_phone.setText(add_data.getAddresses().get(0).getPhoneMobile());
                        tv_user_phone.setText(add_data.getAddresses().get(0).getPhoneMobile());
                        add_id = add_data.getAddresses().get(0).getId().toString();
                        editor.putString(Constants.ADDRESS_ID, add_id);
                        editor.commit();
                    }

                }
            }
        } else {
            tv_show_info.setText("ADD INFORMATION");
            tv_login_user_address.setText("NO INFORMATION AVAILABLE");
            tv_login_user_address.setGravity(Gravity.CENTER);
            tv_show_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent add_address = new Intent(My_Account_Activity.this, Add_Edit_Address_Activity.class);
                    add_address.putExtra("address_flag", true);
                    startActivity(add_address);
                }
            });
        }
    }


    private void clearedPref() {
        editor.putString(Pref_Data.LOGIN_RESPONSE, "");
        editor.putString(Constants.USER_ID, "");
        editor.putString(Constants.CUSTOMIZATION_ID, "");
        editor.putString(Constants.ADDRESS_ID, "");
        editor.putString(Constants.CART_COUNT, "");
        editor.putString(Pref_Data.CART_DATA, "");
        editor.putString(Pref_Data.CART_PRODUCT, "");
        editor.putString(Constants.CART_ID, "");
        editor.putString(Pref_Data.ADDRESS_DATA, "");
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_show_info:
                Intent address = new Intent(My_Account_Activity.this, Show_Address_Activity.class);

                try {
                    Constants.ADDRESS_CLASSNAME = Class.forName("com.varshaaweblabs.ecommerce.Account.View.My_Account_Activity");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                address.putExtra("show_address_flag", true);
                startActivity(address);
                break;

            case R.id.tv_all_order:
                Intent order_list = new Intent(My_Account_Activity.this, User_Order_Activity.class);
                startActivity(order_list);
                break;

            case R.id.btn_logout:
                clearedPref();
                Intent main=new Intent(My_Account_Activity.this, MainActivity.class);
                startActivity(main);
        }
    }
}
