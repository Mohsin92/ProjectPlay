package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_Data;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_Resp;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.HomeView;
import com.varshaaweblabs.ecommerce.Utility.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * Created by dinesh on 30/8/17.
 */

public class ItemFragmentPresenter extends BasePresenter<HomeView.View> implements IFragmentInterface {

    private final android.content.Context context;

    private final IFragmentInterface mListner;

    public ItemFragmentPresenter(Context context, IFragmentInterface mListner) {
        this.context = context;
        this.mListner = mListner;
    }


    //API Call for get Category

    public void getListCategory() {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");
        int id_parent = Constants.parent_id;


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(Constants.BASE_URL + "categories?output_format=JSON&display=full&filter[active]=1&filter[id_parent]=" + id_parent + "")
                .method("GET", null)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
                Toast.makeText(context, "Error in Establish the connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final String myResponse = response.body().string();
                Category_Resp category_resp = new Category_Resp();
                try {
                    ArrayList<Category_Data> cat_arrary = new ArrayList<Category_Data>();
                    JSONObject json = new JSONObject(myResponse);
                    JSONArray ja = json.getJSONArray("categories");
                    Gson gson = new Gson();
                    for (int i = 0; i < ja.length(); i++) {
                        Category_Data cat_data = new Category_Data();
                        cat_data = (Category_Data) gson.fromJson(ja.getJSONObject(i).toString(), Category_Data.class);
                        cat_arrary.add(cat_data);


                    }
                    category_resp.setCategories(cat_arrary);
                    String category_data = gson.toJson(category_resp);


                    if (myResponse.toString() != null) {
                        mListner.getList(category_resp);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });


    }

    @Override
    public void getList(Category_Resp response) {

    }
}
