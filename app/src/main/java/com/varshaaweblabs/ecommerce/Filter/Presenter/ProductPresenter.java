package com.varshaaweblabs.ecommerce.Filter.Presenter;

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
import com.varshaaweblabs.ecommerce.Filter.Model.Filter_Resp;
import com.varshaaweblabs.ecommerce.Filter.Model.Main_Filters;
import com.varshaaweblabs.ecommerce.Filter.Model.Product;
import com.varshaaweblabs.ecommerce.Filter.Model.Product_Sortby;
import com.varshaaweblabs.ecommerce.Filter.Model.Sub_Filter;
import com.varshaaweblabs.ecommerce.Filter.View.FilterView;
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
 * Created by dinesh on 11/9/17.
 */

public class ProductPresenter extends BasePresenter<FilterView.View> implements IProductInterface {

    private final android.content.Context context;

    private final IProductInterface mListner;

    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;

    public ProductPresenter(Context context, IProductInterface mListner) {
        this.context = context;
        this.mListner = mListner;
        Constants.FILTER_SORT = "product.position.asc";
        pref_login = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();

    }


    //API CAll
    //List<String> data
    public void getApplyFilters(String data) throws IOException {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(Constants.BASE_URL + "filter?output_format=JSON&display=full&id_category_layered=" + Constants.VALUE + "&" + "order=" + Constants.FILTER_SORT + "&" + "q=" + data + "")
                .method("GET", null)
                .build();

        Log.e("Product url", String.valueOf(request.url()));

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

                final String myResponse = response.body().string();
                Filter_Resp filter_resp = new Filter_Resp();
                try {

                    ArrayList<Product> prod_array = new ArrayList<Product>();
                    ArrayList<Sub_Filter> sub_filtersarray = new ArrayList<Sub_Filter>();
                    ArrayList<Product_Sortby> sort_array = new ArrayList<Product_Sortby>();
                    JSONObject json = new JSONObject(myResponse);

                    JSONArray prod = json.getJSONArray("products");
                    JSONObject main_filter = json.getJSONObject("main_filters");
                    JSONArray filter_array = main_filter.getJSONArray("sub_filter");
                    JSONArray sort = json.getJSONArray("sortby");

                    Gson gson = new Gson();
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
//
                    Main_Filters main_filters = new Main_Filters();
                    for (int i = 0; i < filter_array.length(); i++) {
                        if (!filter_array.getJSONObject(i).getString("filters").equalsIgnoreCase("[]")) {
                            Sub_Filter sub_filter = (Sub_Filter) gson.fromJson(filter_array.getJSONObject(i).toString(), Sub_Filter.class);
                            sub_filtersarray.add(sub_filter);
                        }

                    }

                    main_filters.setSub_filter(sub_filtersarray);
                    filter_resp.setProduct(prod_array);
                    filter_resp.setMain_filters(main_filters);
                    filter_resp.setSortby(sort_array);


                    if (myResponse.toString() != null) {
                        mListner.Filter_selected_data(filter_resp);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getView().hideProgress();
            }
        });
    }


    public void getCartItem(String cart_id, String user_id) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        String url;


        String guest_user_id = pref_login.getString(Pref_Data.GUEST_USER_ID, "");
        url = "<prestashop><cart><id_cart>" + cart_id + "</id_cart><id_customer>" + user_id + "</id_customer><id_guest>" + guest_user_id + "</id_guest></cart></prestashop>";


        RequestBody body = RequestBody.create(JSON, url);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "cart?output_format=JSON")
                .method("POST", body)
                .build();

        Log.e("Get Cart body product", url);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getView().hideProgress();
                Log.e("AddToCart Call Fail: ", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String cartResponse = response.body().string();
                Cart_Response cartObj = new Cart_Response();

                ArrayList<Cart_Product> cart_products = new ArrayList<Cart_Product>();
                Cart_Total cart_total = new Cart_Total();
                Cart_SubTotal cart_subTotal = new Cart_SubTotal();
                Cart_Vouchers cart_vouchers = new Cart_Vouchers();
                Cart_Label cart_label = new Cart_Label();


                try {
                    JSONObject json = new JSONObject(cartResponse);
                    Gson gson = new Gson();
                    JSONObject cart_json = json.getJSONObject("cart");
                    JSONArray product_array = cart_json.getJSONArray("products");


                    String id = json.get("id_cart").toString();
                    Cart cart = (Cart) gson.fromJson(json.getJSONObject("cart").toString(), Cart.class);
                    String cart_data = gson.toJson(cart);
                    editor_login.putString(Pref_Data.CART_DATA, cart_data);
                    editor_login.commit();
                    cartObj.setIdCart(id);
                    cartObj.setCart(cart);


                    for (int i = 0; i < product_array.length(); i++) {
                        Cart_Product cart_product = new Cart_Product();
                        cart_product = (Cart_Product) gson.fromJson(product_array.getJSONObject(i).toString(), Cart_Product.class);
                        String prod_data = gson.toJson(cart_product);
                        editor_login.putString(Pref_Data.CART_PRODUCT, prod_data);
                        editor_login.commit();
                    }

                    editor_login.putString(Constants.CART_COUNT, String.valueOf(cart.getProducts().size()));
                    editor_login.putString(Constants.CART_ID, id);
                    editor_login.commit();
                    if (cartResponse.toString() != null) {
                        mListner.getCart(cartObj);

                    }

                } catch (Exception e) {
                    Log.e("GetCart error Product", e.toString());
                }

            }
        });
    }

    @Override
    public void Filter_selected_data(Filter_Resp filter_resp) {

    }

    @Override
    public void getCart(Cart_Response cart_response) {

    }
}
