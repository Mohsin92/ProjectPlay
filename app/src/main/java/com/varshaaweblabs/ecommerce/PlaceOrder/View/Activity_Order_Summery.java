package com.varshaaweblabs.ecommerce.PlaceOrder.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;
import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Added;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Product;
import com.varshaaweblabs.ecommerce.Payment.View.Payment_Activity;
import com.varshaaweblabs.ecommerce.PlaceOrder.Adapter.Order_ProductList_Adapter;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Presenter.IOrderSummeryInterface;
import com.varshaaweblabs.ecommerce.PlaceOrder.Presenter.OrderSummeryPresenter;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dinesh on 13/10/17.
 */

public class Activity_Order_Summery extends BaseActivity implements IOrderSummeryInterface, OrderSummeryView.View, View.OnClickListener, OnProgressBarListener {


    OrderSummeryPresenter order_summery_presenter;
    TextView tv_name, tv_add, tv_phone, tv_cart_subtotal, tv_cart_subtotal_value, tv_cart_tax_value, tv_cart_shipping_value, tv_cart_total, tv_cart_coupon, tv_cart_coupon_value, tv_add_change;
    Boolean flag_order = true;
    SharedPreferences pref_login;
    Cart cart_prod_data;
    ArrayList<Cart_Product> order_prod_data = new ArrayList<Cart_Product>();
    LinearLayout ll_coupon_details, ll_add_action;
    String coupon_id;
    ListView lv_order_product_list;
    ImageView coupon_delete;
    Cart_Coupon_Added coupon_list = new Cart_Coupon_Added();
    Order_ProductList_Adapter order_adapter;
    Button btn_payment;
    RadioButton check_text;
    View view;
    SharedPreferences.Editor editor_login;
    String add_id;
    private Timer timer;
    ImageView iv_order_summ;
    private NumberProgressBar bnp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        order_summery_presenter = new OrderSummeryPresenter(this, this);
        order_summery_presenter.attachView(this);
        setContentView(R.layout.activity_order);
        pref_login = Activity_Order_Summery.this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order Summery");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_add = (TextView) findViewById(R.id.tv_address);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_cart_subtotal = (TextView) findViewById(R.id.tv_cart_subtotal);
        tv_cart_subtotal_value = (TextView) findViewById(R.id.tv_cart_subtotal_value);
        tv_cart_tax_value = (TextView) findViewById(R.id.tv_cart_tax_value);
        tv_cart_shipping_value = (TextView) findViewById(R.id.tv_cart_shipping_value);
        tv_cart_total = (TextView) findViewById(R.id.tv_cart_total);
        tv_cart_coupon = (TextView) findViewById(R.id.tv_cart_coupon);
        tv_cart_coupon_value = (TextView) findViewById(R.id.tv_cart_coupon_value);
        ll_coupon_details = (LinearLayout) findViewById(R.id.ll_coupon_details);
        lv_order_product_list = (ListView) findViewById(R.id.lv_order_product_list);
        coupon_delete = (ImageView) findViewById(R.id.coupon_delete);
        btn_payment = (Button) findViewById(R.id.btn_payment);
        tv_add_change = (TextView) findViewById(R.id.tv_add_change);
        check_text = (RadioButton) findViewById(R.id.check_text);
        ll_add_action = (LinearLayout) findViewById(R.id.ll_add_action);
        iv_order_summ = (ImageView) findViewById(R.id.iv_order_summ);
        view = (View) findViewById(R.id.view);


        Intent order = getIntent();
        flag_order = order.getBooleanExtra("order_flag", true);
        coupon_list = (Cart_Coupon_Added) order.getSerializableExtra("coupon_data");
        check_text.setVisibility(View.GONE);
        ll_add_action.setVisibility(View.GONE);
        view.setVisibility(View.GONE);




        Gson gson = new Gson();
        cart_prod_data = gson.fromJson(pref_login.getString(Pref_Data.CART_DATA, ""), Cart.class);
        btn_payment.setOnClickListener(this);
        tv_add_change.setOnClickListener(this);

        String user_id = pref_login.getString(Constants.USER_ID, "");
        try {

            order_summery_presenter.getAddress(user_id);
        } catch (IOException e) {
            e.printStackTrace();
        }


        bnp = (NumberProgressBar) findViewById(R.id.numberbar1);
        bnp.setOnProgressBarListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(5);
                        bnp.setSuffix(" ");
                        bnp.setReachedBarColor(ContextCompat.getColor(Activity_Order_Summery.this, (R.color.grey)));
//                        bnp.setProgressTextVisibility(NumberProgressBar.ProgressTextVisibility.Invisible);
                        bnp.setProgressTextSize(0);
                        bnp.setReachedBarHeight(5);

                    }
                });
            }
        }, 1000, 100);

    }

    @Override
    public void getAddress(final User_Address_model user_address) {
        Activity_Order_Summery.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String add_id_1 = pref_login.getString(Constants.ADDRESS_ID, "");

                for (int i = 0; i < user_address.getAddresses().size(); i++) {

                    if (!add_id_1.equalsIgnoreCase("")) {
                        if (add_id_1.equalsIgnoreCase(user_address.getAddresses().get(i).getId().toString())) {
                            tv_name.setText(user_address.getAddresses().get(i).getFirstname() + " " + user_address.getAddresses().get(i).getLastname());
                            tv_add.setText(user_address.getAddresses().get(i).getAddress1());
                            tv_phone.setText(user_address.getAddresses().get(i).getPhoneMobile());
                            add_id = user_address.getAddresses().get(i).getId().toString();
                            editor_login.putString(Constants.ADDRESS_ID, add_id);
                            editor_login.commit();
                        }
                    } else {
                        tv_name.setText(user_address.getAddresses().get(0).getFirstname() + " " + user_address.getAddresses().get(0).getLastname());
                        tv_add.setText(user_address.getAddresses().get(0).getAddress1());
                        tv_phone.setText(user_address.getAddresses().get(0).getPhoneMobile());
                        add_id = user_address.getAddresses().get(0).getId().toString();
                        editor_login.putString(Constants.ADDRESS_ID, add_id);
                        editor_login.commit();
                    }

                }

                tv_cart_subtotal_value.setText("$" + cart_prod_data.getSubtotals().getProducts().getValue());
                tv_cart_tax_value.setText("$" + cart_prod_data.getSubtotals().getTax().getValue());
                tv_cart_shipping_value.setText("$" + cart_prod_data.getSubtotals().getShipping().getValue());
                tv_cart_total.setText("$" + cart_prod_data.getTotals().getTotal().getValue());
                btn_payment.setText("Proceed To Pay $" +cart_prod_data.getTotals().getTotal().getValue() );


                //TODO To Show the Coupon Details
                if (coupon_list != null) {
                    ll_coupon_details.setVisibility(View.VISIBLE);
                    tv_cart_coupon.setText(coupon_list.getName());
                    tv_cart_coupon_value.setText("- $ " + coupon_list.getReductionAmount());
                    coupon_id = coupon_list.getIdCartRule();
                    coupon_delete.setVisibility(View.GONE);
                } else {
                    ll_coupon_details.setVisibility(View.GONE);

                }

                order_adapter = new Order_ProductList_Adapter(Activity_Order_Summery.this, Constants.order_prod);
                lv_order_product_list.setAdapter(order_adapter);

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_payment:
                Intent payment = new Intent(Activity_Order_Summery.this, Payment_Activity.class);
                payment.putExtra("coupon_data1", coupon_list);
                startActivity(payment);
                break;

            case R.id.tv_add_change:
                Intent address = new Intent(Activity_Order_Summery.this, Show_Address_Activity.class);
                address.putExtra("coupon_data", coupon_list);
                try {
                    Constants.ADDRESS_CLASSNAME = Class.forName("com.varshaaweblabs.ecommerce.PlaceOrder.View.Activity_Order_Summery");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                address.putExtra("show_address_flag", false);
                startActivity(address);
                break;
        }
    }

    @Override
    public void onProgressChange(int current, int max) {
        if (current == max) {

//            bnp.setProgress(0);
            iv_order_summ.setImageResource(R.mipmap.order_checkout_fill);
        }
    }
}
