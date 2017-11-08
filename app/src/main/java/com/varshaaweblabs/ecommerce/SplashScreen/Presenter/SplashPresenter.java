package com.varshaaweblabs.ecommerce.SplashScreen.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.SplashScreen.Model.OrderState_Data;
import com.varshaaweblabs.ecommerce.SplashScreen.Model.OrderStates_Resp;
import com.varshaaweblabs.ecommerce.SplashScreen.Model.ProductOptionMeta_Data;
import com.varshaaweblabs.ecommerce.SplashScreen.Model.ProductOptionsMeta_Resp;
import com.varshaaweblabs.ecommerce.SplashScreen.View.SplashActivity;
import com.varshaaweblabs.ecommerce.SplashScreen.View.SplashView;
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
 * Created by dinesh on 28/8/17.
 */

public class SplashPresenter extends BasePresenter<SplashView.View> implements ISplashInterface {

    private final android.content.Context context;

    private final ISplashInterface Splash;

    ISplashInterface mListner;
    SharedPreferences pref_product;
    SharedPreferences.Editor editor_prodmeta;
    final int PERMISSION_REQUEST_CODE=420;


    public SplashPresenter(Context context, ISplashInterface loginview, ISplashInterface mListner) {
        this.context = context;
        this.Splash = loginview;
        this.mListner = mListner;
        pref_product = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_prodmeta = pref_product.edit();
    }

    //API Call for Product Meta Data

    public void getProdectMeta() throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(Constants.BASE_URL + "product_options/?display=full&output_format=JSON")
                .method("GET", null)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

                call.cancel();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final String myResponse = response.body().string();
                ProductOptionsMeta_Resp meta_resp = new ProductOptionsMeta_Resp();
                try {
                    ArrayList<ProductOptionMeta_Data> prod_array = new ArrayList<ProductOptionMeta_Data>();
                    JSONObject json = new JSONObject(myResponse);

                    JSONArray ja = json.getJSONArray("product_options");

                    Gson gson = new Gson();
                    for (int i = 0; i < ja.length(); i++) {
                        ProductOptionMeta_Data prod_meta = new ProductOptionMeta_Data();
                        prod_meta = (ProductOptionMeta_Data) gson.fromJson(ja.getJSONObject(i).toString(), ProductOptionMeta_Data.class);
                        prod_array.add(prod_meta);


                    }
                    meta_resp.setProductOptions(prod_array);
                    String prod_metaData = gson.toJson(meta_resp);
                    editor_prodmeta.putString(Pref_Data.PRODUCT_METADATA, prod_metaData);
                    editor_prodmeta.commit();

                    if (myResponse.toString() != null) {
                        mListner.product_meta(meta_resp);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });

    }

    //API CAll for OrderStates
    public void getOrderStates() throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(Constants.BASE_URL + "order_states?output_format=JSON&display=full")
                .method("GET", null)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

                call.cancel();

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final String myResponse = response.body().string();
                OrderStates_Resp orderstate_resp = new OrderStates_Resp();
                try {
                    ArrayList<OrderState_Data> orderstates_array = new ArrayList<OrderState_Data>();
                    JSONObject json = new JSONObject(myResponse);

                    JSONArray ja = json.getJSONArray("order_states");

                    Gson gson = new Gson();
                    for (int i = 0; i < ja.length(); i++) {
                        OrderState_Data orderState_data=new OrderState_Data();
                        orderState_data = (OrderState_Data) gson.fromJson(ja.getJSONObject(i).toString(), OrderState_Data.class);
                        orderstates_array.add(orderState_data);

                    }
                    orderstate_resp.setOrderStates(orderstates_array);
                    String order_data = gson.toJson(orderstate_resp);
                    editor_prodmeta.putString(Pref_Data.ORDER_STATES, order_data);
                    editor_prodmeta.commit();

                    if (myResponse.toString() != null) {
                        mListner.orderStates(orderstate_resp);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });

    }

    @Override
    public void product_meta(ProductOptionsMeta_Resp product_data) {

    }

    @Override
    public void orderStates(OrderStates_Resp orderStates_resp) {

    }
}



