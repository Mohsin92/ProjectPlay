package com.varshaaweblabs.ecommerce.SplashScreen.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.MainActivity;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginCustomer_Data;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.SplashScreen.Model.OrderStates_Resp;
import com.varshaaweblabs.ecommerce.SplashScreen.Model.ProductOptionsMeta_Resp;
import com.varshaaweblabs.ecommerce.SplashScreen.Presenter.ISplashInterface;
import com.varshaaweblabs.ecommerce.SplashScreen.Presenter.SplashPresenter;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;

/**
 * Created by dinesh on 28/8/17.
 */

public class SplashActivity extends BaseActivity implements ISplashInterface, SplashView.View {

    SplashPresenter splashPresenter;
    Gson gson = new Gson();
    SharedPreferences pref_login;


    private final int SPLASH_DISPLAY_LENGTH = 3500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref_login = this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        splashPresenter = new SplashPresenter(this, this, this);
        splashPresenter.attachView(this);
//        splashPresenter.checkPermission();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

//
        try {

            splashPresenter.getProdectMeta();


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Pref_Data.ORDER_STATES.equalsIgnoreCase("order_states")) {
            try {
                splashPresenter.getOrderStates();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginCustomer_Data cust_data = gson.fromJson(pref_login.getString(Pref_Data.LOGIN_RESPONSE, ""), LoginCustomer_Data.class);

                Intent in = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(in);
                finish();


            }
        }, SPLASH_DISPLAY_LENGTH);


    }


    @Override
    public void product_meta(final ProductOptionsMeta_Resp product_data) {

        SplashActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Gson gson=new Gson();
//                ProductOptionsMeta_Resp product_meta = gson.fromJson(pref_productOptions.getString(Pref_Data.PRODUCT_METADATA, ""),ProductOptionsMeta_Resp.class);
                for (int i = 0; i < product_data.getProductOptions().size(); i++) {
//                    String id = product_data.getProductOptions().get(i).getId().toString();
//                    Toast.makeText(SplashActivity.this, "Get Product Details " + product_data.getProductOptions().get(i).getName() + id, Toast.LENGTH_SHORT).show();
                }

//
            }
        });
    }

    @Override
    public void orderStates(OrderStates_Resp orderStates_resp) {
        SplashActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                OrderState_Data orderstate_data = gson.fromJson(pref_login.getString(Pref_Data.ORDER_STATES, ""), OrderState_Data.class);
            }
        });
    }
}
