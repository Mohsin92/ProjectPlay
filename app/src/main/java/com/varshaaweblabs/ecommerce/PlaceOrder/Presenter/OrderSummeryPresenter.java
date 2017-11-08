package com.varshaaweblabs.ecommerce.PlaceOrder.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_List;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;
import com.varshaaweblabs.ecommerce.PlaceOrder.View.OrderSummeryView;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dinesh on 13/10/17.
 */

public class OrderSummeryPresenter extends BasePresenter<OrderSummeryView.View> implements IOrderSummeryInterface {


    private final android.content.Context context;
    private final IOrderSummeryInterface mListner;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;


    public OrderSummeryPresenter(Context context, IOrderSummeryInterface mListner) {
        this.context = context;
        this.mListner = mListner;
        pref_login = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();
    }


    //API CAll For Get Address

    public void getAddress(String cust_id) throws IOException {
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

                    if (myResponse.toString() != null) {
                        mListner.getAddress(user_add);
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
    public void getAddress(User_Address_model user_address) {

    }
}
