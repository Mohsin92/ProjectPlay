package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Home_Slider_Resp;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Product_Data;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Slider_Images;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.HomeView;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Product_Recommend;
import com.varshaaweblabs.ecommerce.Utility.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by dinesh on 6/9/17.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeView.View> implements IHomeFragmentInterface {


    private final android.content.Context context;

    private final IHomeFragmentInterface mListner;


    public HomeFragmentPresenter(Context context, IHomeFragmentInterface mListner) {
        this.context = context;
        this.mListner = mListner;
    }

    //API Call For Get Products

//    public void getProducts(){
//        getView().showProgress();
//        OkHttpClient client = new OkHttpClient();
//        MediaType JSON
//                = MediaType.parse("application/xml; charset=utf-8");
//
//
//        okhttp3.Request request = new okhttp3.Request.Builder()
//                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
//                .url(Constants.BASE_URL + "products?output_format=JSON&display=full" )
//                .method("GET", null)
//                .build();
//
//
//        client.newCall(request).enqueue(new okhttp3.Callback() {
//
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                getView().hideProgress();
//                call.cancel();
//                Log.e("Error", e.toString());
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//                getView().hideProgress();
//                final String myResponse = response.body().string();
//                Product_Resp product_resp=new Product_Resp();
//                try {
//                    ArrayList<Product_Data> prod_array = new ArrayList<Product_Data>();
//                    JSONObject json = new JSONObject(myResponse);
//                    JSONArray ja = json.getJSONArray("products");
//
//                    Gson gson = new Gson();
//                    for (int i = 0; i < ja.length(); i++) {
//                        Product_Data product_data = new Product_Data();
//                        product_data = (Product_Data) gson.fromJson(ja.getJSONObject(i).toString(), Product_Data.class);
//                        prod_array.add(product_data);
//
//
//                    }
//                    product_resp.setProducts(prod_array);
//                    String prod_data = gson.toJson(product_resp);
//
//
//                    if (myResponse.toString() != null) {
//                        mListner.getProductList(product_resp);
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//        });
//    }

    public void HomeData() throws IOException {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(Constants.BASE_URL + "home?output_format=JSON")
                .method("GET", null)
                .build();


        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                Log.e("Slider Call", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                Home_Slider_Resp homeData_Resp = new Home_Slider_Resp();
                ArrayList<Slider_Images> slider_images_array = new ArrayList<>();
                ArrayList<Product_Recommend> prod_array = new ArrayList<Product_Recommend>();
                JSONObject json = null;
                try {
                    json = new JSONObject(myResponse);
                    JSONArray pro_json_array = json.getJSONArray("product");
                    JSONArray slider_array = json.getJSONArray("slider");

                    Gson gson = new Gson();
                    for (int i = 0; i < slider_array.length(); i++) {
                        Slider_Images slider_data = new Slider_Images();
                        slider_data = (Slider_Images) gson.fromJson(slider_array.getJSONObject(i).toString(), Slider_Images.class);
                        slider_images_array.add(slider_data);
                    }

                    for (int i = 0; i < pro_json_array.length(); i++) {
                        Product_Recommend product_data = new Product_Recommend();
                        product_data = (Product_Recommend) gson.fromJson(pro_json_array.getJSONObject(i).toString(), Product_Recommend.class);
                        prod_array.add(product_data);
                    }


                    homeData_Resp.setSlider(slider_images_array);
                    homeData_Resp.setProduct(prod_array);
                    String slider_data = gson.toJson(homeData_Resp);

                    if (homeData_Resp != null) {
                        getView().hideProgress();
                        mListner.getHomeData(homeData_Resp);
                    }


                } catch (JSONException e) {
                    Log.e("Slider Call Exception", e.toString());
                    e.printStackTrace();
                }


            }
        });

    }


    @Override
    public void getHomeData(Home_Slider_Resp homedataResp) {

    }
}
