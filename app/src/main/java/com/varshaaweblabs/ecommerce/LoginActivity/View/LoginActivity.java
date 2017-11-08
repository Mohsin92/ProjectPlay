package com.varshaaweblabs.ecommerce.LoginActivity.View;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Account.View.Add_Edit_User_Activity;
import com.varshaaweblabs.ecommerce.Account.View.Forgot_Password_Activity;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Added;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;
import com.varshaaweblabs.ecommerce.Cart.View.AddToCartActivity;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.MainActivity;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginCustomer_Data;
import com.varshaaweblabs.ecommerce.LoginActivity.Presenter.ILoginInterface;
import com.varshaaweblabs.ecommerce.LoginActivity.Presenter.LoginPresenter;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_List;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Presenter.GetAllAddressPresenter;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;

/**
 * Created by dinesh on 24/8/17.
 */


public class LoginActivity extends BaseActivity implements ILoginInterface, View.OnClickListener, LoginView.View {

    LoginPresenter loginpresenter;
    GetAllAddressPresenter address_presenter;
    EditText ed_email, ed_pass;
    Button btn_login;
    String email, password;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor;
    LinearLayout ll_signup;
    TextView tv_forgot_pass;
    ImageView iv_close;
    Cart_Coupon_Added coupon_list = new Cart_Coupon_Added();
    Boolean guest_flag = true;
    User_Address_List add_data = new User_Address_List();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginpresenter = new LoginPresenter(this, this, this);
        loginpresenter.attachView(this);


        Intent guest_login = getIntent();
        coupon_list = (Cart_Coupon_Added) guest_login.getSerializableExtra("coupon_data");
        guest_flag = guest_login.getBooleanExtra("guest_flag", true);

    }


    //For initialized
    @Override
    public void Init() {
        setContentView(R.layout.dialog_login);
        pref_login = LoginActivity.this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor = pref_login.edit();
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_pass = (EditText) findViewById(R.id.ed_pass);
        btn_login = (Button) findViewById(R.id.login_email_confirm);
        ll_signup = (LinearLayout) findViewById(R.id.ll_signup);
        tv_forgot_pass = (TextView) findViewById(R.id.tv_forgot_pass);
        iv_close = (ImageView) findViewById(R.id.iv_close);

        btn_login.setOnClickListener(LoginActivity.this);
        ll_signup.setOnClickListener(LoginActivity.this);
        tv_forgot_pass.setOnClickListener(this);
        iv_close.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_email_confirm:
                email = ed_email.getText().toString();
                password = ed_pass.getText().toString();
//                loginpresenter.getLogin();


                try {
                    loginpresenter.getLogin(email, password);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.ll_signup:
                Intent signup = new Intent(LoginActivity.this, Add_Edit_User_Activity.class);
                signup.putExtra("signup_flag", true);
                signup.putExtra("guest_flag", false);
                startActivity(signup);
                break;

            case R.id.tv_forgot_pass:

                Intent forgot_pass = new Intent(LoginActivity.this, Forgot_Password_Activity.class);
                startActivity(forgot_pass);
                break;

            case R.id.iv_close:
                finish();
                break;
        }

    }


    @Override
    public void Login(final LoginCustomer_Data response) {

        LoginActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response != null) {
                    Gson gson = new Gson();
                    LoginCustomer_Data cust_data = gson.fromJson(pref_login.getString(Pref_Data.LOGIN_RESPONSE, ""), LoginCustomer_Data.class);
                    Toast.makeText(LoginActivity.this, "Welcome " + response.getFirstname() + " "  +response.getLastname(), Toast.LENGTH_LONG).show();
                    editor.putString(Constants.USER_ID, cust_data.getId());
                    editor.commit();

                    try {
                        loginpresenter.getAllUserAddress(cust_data.getId().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    if (guest_flag) {
                        Intent cart = new Intent(LoginActivity.this, AddToCartActivity.class);
                        cart.putExtra("cart_intent_flag", true);
                        cart.putExtra("login_intent_flag", true);
                        startActivity(cart);
                    } else {
                        Intent in = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(in);
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Please check your E-mail id and Password", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void getAllAddress(User_Address_model address_list) {

    }

}
