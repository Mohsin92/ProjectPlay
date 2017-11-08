package com.varshaaweblabs.ecommerce.Payment.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Filter.View.FilterProductList;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

/**
 * Created by dinesh on 13/10/17.
 */

public class Activity_Congratulations extends BaseActivity implements View.OnClickListener {
    Button btn_cont_shopping;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
        pref_login = this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();
        btn_cont_shopping = (Button) findViewById(R.id.btn_cont_shopping);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Congratulations");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_cont_shopping.setOnClickListener(this);
        editor_login.putString(Pref_Data.CART_DATA, "");
        editor_login.putString(Pref_Data.CART_PRODUCT, "");
        editor_login.putString(Constants.CART_COUNT, "");
        editor_login.putString(Constants.CART_ID, "");
        editor_login.commit();
        Constants.order_prod.clear();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cont_shopping:
                Intent prod_full_details = new Intent(Activity_Congratulations.this, FilterProductList.class);
                prod_full_details.putExtra("flag", true);
                prod_full_details.putExtra("Congratulations_flag", true);
                startActivity(prod_full_details);
                break;
        }

    }
}
