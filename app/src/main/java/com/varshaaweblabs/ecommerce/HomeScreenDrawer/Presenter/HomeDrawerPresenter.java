package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_Data;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_Resp;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.HomeView;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.MainActivity;
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
 * Created by dinesh on 28/8/17.
 */

public class HomeDrawerPresenter extends BasePresenter<HomeView.View> implements IHomeInterface {

    private final android.content.Context context;

    private final IHomeInterface mListner;
    final int PERMISSION_REQUEST_CODE = 420;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;

    public HomeDrawerPresenter(Context context, IHomeInterface mListner) {
        this.context = context;
        this.mListner = mListner;
        pref_login = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();
    }

    //API Call for MenuDrawer

    public void getDrawerMenu() throws IOException {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(Constants.BASE_URL + "categories?output_format=JSON&display=full&filter[active]=1")
                .method("GET", null)
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
                        mListner.getMenu(category_resp);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });


    }

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions((MainActivity) context, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"}, PERMISSION_REQUEST_CODE);
        return false;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE /*185*/:
                if (grantResults.length > 0 && grantResults[0] == 0) {
                    // finish();
                   /* Intent newIntent = new Intent(this, Dashboard.class);
                    newIntent.addFlags(335544320);
                    startActivity(newIntent);*/
                    Toast.makeText(context, "permission granted", Toast.LENGTH_SHORT).show();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void getGusetId() throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");
        String url = "";
        String device_id = pref_login.getString(Pref_Data.DEVICE_ID, "");
        if (device_id.equalsIgnoreCase("")) {
            url = "<prestashop><guest><id_device>" + device_id + "</id_device></guest></prestashop>";
        }

        RequestBody body = RequestBody.create(JSON, url);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "guestuser/?output_format=JSON&display=full")
                .method("POST", body)
                .build();
        Log.e("Get Guest ID", String.valueOf(url));

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String gustuserResponse = response.body().string();

                String id = "";

                try {
                    JSONObject json = new JSONObject(gustuserResponse);
                    if (json.has("id_guest")) {
                        id = json.getString("id_guest");
                        editor_login.putString(Pref_Data.GUEST_USER_ID, id);
                        editor_login.commit();
                    }
                } catch (Exception e) {
                    Log.e("Guest User Exceptions: ", e.toString());
                }

            }
        });
    }


    public void getCartItem(String cart_id, String user_id) {
        getView().showProgress();
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

        Log.e("Get Cart body Login", url);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getView().hideProgress();
                Log.e("AddToCart Call Fail: ", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                getView().hideProgress();

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
                    Log.e("GetCart error Login ", e.toString());
                }

            }
        });
    }

    @Override
    public void getMenu(Category_Resp response) {

    }

    @Override
    public void getCart(Cart_Response cart_response) {

    }


}
