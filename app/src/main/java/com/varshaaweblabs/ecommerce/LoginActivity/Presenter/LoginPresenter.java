package com.varshaaweblabs.ecommerce.LoginActivity.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Label;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Product;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_SubTotal;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Total;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Vouchers;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginCustomer_Data;
import com.varshaaweblabs.ecommerce.LoginActivity.View.LoginView;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_List;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;
import com.varshaaweblabs.ecommerce.Retrofit.Services;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dinesh on 24/8/17.
 */

public class LoginPresenter extends BasePresenter<LoginView.View> implements ILoginInterface {

    private final android.content.Context context;

    private final ILoginInterface loginview;

    private final Services loginService;

    ILoginInterface mListener;


    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;


    public LoginPresenter(ILoginInterface mListener, ILoginInterface loginview, Context context) {
        this.context = context;
        this.loginview = loginview;
        this.mListener = mListener;
        this.loginService = new Services();
        pref_login = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();
    }

    //API Call for login

    public void getLogin(String email, String password) throws IOException {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, "<prestashop><customer> <email>" + email + "</email> <passwd>" + password + "</passwd></customer></prestashop>");

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "account/login?output_format=JSON&display=full")
                .method("POST", body)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                getView().hideProgress();
                call.cancel();
                Toast.makeText(context, "Error in Establish the connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                getView().hideProgress();
                final String myResponse = response.body().string();
                Gson gson = new Gson();
                try {
                    JSONObject json = new JSONObject(myResponse);
                    if (json.has("error")) {
                        if (myResponse.toString() != null) {
                            mListener.Login(null);

                        }
                    } else {
                        JSONArray ja = json.getJSONArray("customers");

                        for (int i = 0; i < ja.length(); i++) {
                            LoginCustomer_Data cust_data = new LoginCustomer_Data();
                            cust_data = (LoginCustomer_Data) gson.fromJson(ja.getJSONObject(i).toString(), LoginCustomer_Data.class);
                            String login_data = gson.toJson(cust_data);
                            editor_login.putString(Pref_Data.LOGIN_RESPONSE, login_data);
                            editor_login.commit();

                            if (myResponse.toString() != null) {
                                mListener.Login(cust_data);

                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

    }


    //API CAll For Get Address

    public void getAllUserAddress(String cust_id) throws IOException {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        String url = Constants.BASE_URL + "addresses/?output_format=JSON&display=full&filter[id_customer]=" + cust_id + "&filter[deleted]=0";


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(url)
                .method("GET", null)
                .build();

        Log.e("get_add_url", request.url().toString());

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
                Toast.makeText(context, "Error in Establish the connection", Toast.LENGTH_SHORT).show();
                Log.e("GetAddress Call Fail: ", e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                final String myResponse = response.body().string();
                User_Address_model user_add = new User_Address_model();
                Gson gson = new Gson();

                ArrayList<User_Address_List> user_address_lists = new ArrayList<User_Address_List>();
                JSONObject json = null;


                try {
                    json = new JSONObject(myResponse);
                    JSONArray address = json.getJSONArray("addresses");

                    for (int i = 0; i < address.length(); i++) {
                        User_Address_List addresp = new User_Address_List();
                        addresp = (User_Address_List) gson.fromJson(address.getJSONObject(i).toString(), User_Address_List.class);

                        user_address_lists.add(addresp);

                    }

                    user_add.setAddresses(user_address_lists);
                    String add_data = gson.toJson(user_add);
                    editor_login.putString(Pref_Data.ADDRESS_DATA, add_data);
                    editor_login.putString(Constants.ADDRESS_ID, user_add.getAddresses().get(0).getId().toString());
                    editor_login.commit();
                    if (myResponse.toString() != null) {
                        mListener.getAllAddress(user_add);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("GetAddress Exceptions: ", e.toString());
                }

                getView().hideProgress();
            }
        });
    }





    @Override
    public void Login(LoginCustomer_Data response) {

    }

    @Override
    public void getAllAddress(User_Address_model address_list) {

    }

}
