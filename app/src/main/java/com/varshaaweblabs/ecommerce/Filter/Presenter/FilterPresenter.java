package com.varshaaweblabs.ecommerce.Filter.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.Filter.Model.Active_Filter;
import com.varshaaweblabs.ecommerce.Filter.Model.Filter_Resp;
import com.varshaaweblabs.ecommerce.Filter.Model.Main_Filters;
import com.varshaaweblabs.ecommerce.Filter.Model.Product;
import com.varshaaweblabs.ecommerce.Filter.Model.Product_Sortby;
import com.varshaaweblabs.ecommerce.Filter.Model.Sub_Filter;
import com.varshaaweblabs.ecommerce.Filter.View.FilterView;
import com.varshaaweblabs.ecommerce.Utility.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * Created by dinesh on 8/9/17.
 */

public class FilterPresenter extends BasePresenter<FilterView.View> implements IFilterInterface {

    private final android.content.Context context;

    private final IFilterInterface mListner;

    public FilterPresenter(Context context, IFilterInterface mListner) {
        this.context = context;
        this.mListner = mListner;
    }

    //API Call for getFilter Data


    public void getFilters(String data) throws IOException {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");


        String id_category_layerd = Constants.VALUE;
//        String id_category_layerd=null;
            String url=Constants.BASE_URL + "filter/?output_format=JSON&display=full&id_category_layered=" + id_category_layerd + "&" + "q=" + data + "";

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(url)
                .method("GET", null)
                .build();

        Log.e("url", request.url().toString());


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
                Constants.FILTER_SORT=null;
//                Constants.FILTER_URL=null;
                Constants.filterResp=null;
                final String myResponse = response.body().string();
                Filter_Resp filter_resp = new Filter_Resp();
                try {
                    JSONArray active_filter_array = null;
                    Gson gson = new Gson();
                    ArrayList<Product> prod_array = new ArrayList<Product>();
                    ArrayList<Sub_Filter> sub_filtersarray = new ArrayList<Sub_Filter>();
                    ArrayList<Product_Sortby> sort_array = new ArrayList<Product_Sortby>();
                    ArrayList<Active_Filter> active_filter = new ArrayList<Active_Filter>();
                    JSONObject json = new JSONObject(myResponse);

                    JSONArray prod = json.getJSONArray("product");
                    JSONObject main_filter = json.getJSONObject("main_filters");
                    JSONArray filter_array = main_filter.getJSONArray("sub_filter");
                    JSONArray sort = json.getJSONArray("sortby");

                    if (main_filter.getString("active_filter").equalsIgnoreCase("{}")) {
//                        JSONObject active_filter_array = main_filter.getJSONObject("active_filter");
                    } else {
                        active_filter_array = main_filter.getJSONArray("active_filter");

                        for (int i = 0; i < active_filter_array.length(); i++) {
                            Active_Filter active_filter1 = new Active_Filter();
                            active_filter1 = (Active_Filter) gson.fromJson(active_filter_array.getJSONObject(i).toString(), Active_Filter.class);
                            active_filter.add(active_filter1);
                        }
                    }


                    for (int i = 0; i < prod.length(); i++) {

                        Product product_data = new Product();
                        product_data = (Product) gson.fromJson(prod.getJSONObject(i).toString(), Product.class);
                        prod_array.add(product_data);

                    }
                    for (int i = 0; i < sort.length(); i++) {
                        Product_Sortby sort_data = new Product_Sortby();
                        sort_data = (Product_Sortby) gson.fromJson(sort.getJSONObject(i).toString(), Product_Sortby.class);
                        sort_array.add(sort_data);
                    }


                    for (int i = 0; i < filter_array.length(); i++) {

                        Sub_Filter sub_filter = (Sub_Filter) gson.fromJson(filter_array.getJSONObject(i).toString(), Sub_Filter.class);
                        sub_filtersarray.add(sub_filter);

                    }

                    Main_Filters main_filters = new Main_Filters();

                    main_filters.setSub_filter(sub_filtersarray);
                    main_filters.setActiveFilter(active_filter);
                    filter_resp.setProduct(prod_array);
                    filter_resp.setMain_filters(main_filters);
                    filter_resp.setSortby(sort_array);


                    if (myResponse.toString() != null) {
                        mListner.Filter_Data(filter_resp);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getView().hideProgress();
            }
        });
    }


    @Override
    public void Filter_Data(Filter_Resp filter_resp) {

    }


}
