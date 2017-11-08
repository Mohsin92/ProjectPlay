package com.varshaaweblabs.ecommerce.Cart.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Error;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Label;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Product;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_SubTotal;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Total;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Vouchers;
import com.varshaaweblabs.ecommerce.Cart.View.AddToCartView;
import com.varshaaweblabs.ecommerce.Retrofit.Services;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import org.json.JSONArray;
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
 * Created by mohsin on 26/9/17.
 */

public class AddToCartPresenter extends BasePresenter<AddToCartView.View> implements ICartInterface {

    private Context context;

    private ICartInterface cartview;

    private Services cartServices;

    ICartInterface mListener;
    Gson gson;

    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;
    String user_id, guest_user_id;

    //API For Add To Cart
    public AddToCartPresenter(Context context, ICartInterface cartview, ICartInterface mListener) {
        this.context = context;
        this.cartview = cartview;
        this.cartServices = new Services();
        this.mListener = mListener;
        pref_login = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();
        gson = new Gson();
        user_id = pref_login.getString(Constants.USER_ID, "");
        guest_user_id = pref_login.getString(Pref_Data.GUEST_USER_ID, "");
    }

    public void AddTocartAPI(String id, String qty, String id_attribute, String operator, final String cart_id, String cust_id) throws IOException {
        getView().showProgress();
        String url;
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");


        if (user_id.equalsIgnoreCase("")) {
            url = "<prestashop> <cart>  <id_guest>" + guest_user_id + "</id_guest>   <id_cart>" + cart_id + "</id_cart> <action>update</action> <id_product>" + id + "</id_product>  <id_product_attribute>" + id_attribute + "</id_product_attribute>  <qty>" + qty + "</qty> <id_customization>" + cust_id + "</id_customization> <id_address_delivery>0</id_address_delivery> " + operator + " </cart> </prestashop>";
        } else {
            url = "<prestashop> <cart>  <id_guest>" + guest_user_id + "</id_guest> <id_cart>" + cart_id + "</id_cart>   <id_customer>" + user_id + "</id_customer> <action>update</action> <id_product>" + id + "</id_product>  <id_product_attribute>" + id_attribute + "</id_product_attribute>  <qty>" + qty + "</qty> <id_customization>" + cust_id + "</id_customization> <id_address_delivery>0</id_address_delivery> " + operator + " </cart> </prestashop>";
        }


        RequestBody body = RequestBody.create(JSON, url);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "cart/?output_format=JSON")
                .method("POST", body)
                .build();
        Log.e("Add to Cart URL", String.valueOf(url));
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
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
                ArrayList<Cart_Product> order_product_list = new ArrayList<Cart_Product>();

                String id = "";

                try {
                    JSONObject json = new JSONObject(cartResponse);
                    JSONObject cart_json = json.getJSONObject("cart");
                    JSONArray product_array = cart_json.getJSONArray("products");
                    cart_products.clear();

                    if (json.has("id_cart")) {

                        id = json.getString("id_cart");
                        Cart cart = (Cart) gson.fromJson(json.getJSONObject("cart").toString(), Cart.class);
                        String cart_data = gson.toJson(cart);
                        editor_login.putString(Pref_Data.CART_DATA, cart_data);
                        editor_login.commit();
                        cartObj.setIdCart(id);
                        cartObj.setCart(cart);


                        for (int i = 0; i < product_array.length(); i++) {
                            Cart_Product cart_product = new Cart_Product();
                            cart_product = (Cart_Product) gson.fromJson(product_array.getJSONObject(i).toString(), Cart_Product.class);
                            order_product_list.add(cart_product);
                        }

                        String prod_data = gson.toJson(order_product_list);
                        editor_login.putString(Pref_Data.CART_PRODUCT, prod_data);
                        editor_login.putString(Constants.CART_ID,id);
                        editor_login.commit();


                    }
                    if (cartResponse.toString() != null) {
                        mListener.AddToCart(cartObj);
                        getView().hideProgress();
                    }

                } catch (Exception e) {
                    Log.e("AddToCart Exceptions: ", e.toString());
                }

            }
        });
    }

    //API for Remove Cart
    public void removeCart(Cart_Product cart_product, String cart_id) {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");


        String url = "<prestashop> <cart>  <action>delete</action>   <id_cart>" + cart_id + "</id_cart><id_customer>" + user_id + "</id_customer><id_guest>" + guest_user_id + "</id_guest> <id_product>" + cart_product.getIdProduct() + "</id_product>  <id_product_attribute>" + cart_product.getIdProductAttribute() + "</id_product_attribute> <id_customization>" + cart_product.getIdCustomization() + "</id_customization> <id_address_delivery>0</id_address_delivery> </cart> </prestashop>";

        RequestBody body = RequestBody.create(JSON, url);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "cart/?output_format=JSON")
                .method("POST", body)
                .build();
        Log.e("Delete Cart URL: ", String.valueOf(url));
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

                String id = "";

                try {
                    JSONObject json = new JSONObject(cartResponse);
                    JSONObject cart_json = json.getJSONObject("cart");
                    JSONArray product_array = cart_json.getJSONArray("products");
                    cart_products.clear();


                    Cart cart = (Cart) gson.fromJson(json.getJSONObject("cart").toString(), Cart.class);
                    cartObj.setIdCart(id);
                    cartObj.setCart(cart);
                    String cart_data = gson.toJson(cart);
                    editor_login.putString(Pref_Data.CART_DATA, cart_data);
                    editor_login.commit();



                    for (int i = 0; i < product_array.length(); i++) {
                        Cart_Product cart_product = new Cart_Product();
                        cart_product = (Cart_Product) gson.fromJson(product_array.getJSONObject(i).toString(), Cart_Product.class);
                        String prod_data = gson.toJson(cart_product);
                        editor_login.putString(Pref_Data.CART_PRODUCT, prod_data);
                        editor_login.commit();

                    }

                    if (cartResponse.toString() != null) {
                        mListener.AddToCart(cartObj);
                    }

                } catch (Exception e) {
                    Log.e("AddToCart Exceptions: ", e.toString());
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

        url = "<prestashop><cart><id_cart>" + cart_id + "</id_cart><id_customer>" + user_id + "</id_customer><id_guest>" + guest_user_id + "</id_guest></cart></prestashop>";


        RequestBody body = RequestBody.create(JSON, url);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "cart?output_format=JSON")
                .method("POST", body)
                .build();

        Log.e("Get Cart body Cart", url);

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

                    JSONObject cart_json = json.getJSONObject("cart");
                    JSONArray product_array = cart_json.getJSONArray("products");
                    cart_products.clear();

                    String id = json.get("id_cart").toString();
                    Cart cart = (Cart) gson.fromJson(json.getJSONObject("cart").toString(), Cart.class);
                    cartObj.setIdCart(id);
                    cartObj.setCart(cart);

                    editor_login.putString(Constants.CART_ID, id);
                    editor_login.commit();


                    if (cartResponse.toString() != null) {
                        mListener.AddToCart(cartObj);
                    } else {
                        mListener.AddToCart(null);
                    }

                } catch (Exception e) {
                    Log.e("Get Cart Error", e.toString());
                }

            }
        });
    }

    public void AddCoupon(String coupon_data) {
        getView().showProgress();
        String cart_id = pref_login.getString(Constants.CART_ID, "");
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");


        String url = "<prestashop><cart><id_guest>" + guest_user_id + "</id_guest><id_cart>" + cart_id + "</id_cart><id_customer>" + user_id + "</id_customer><action>voucher</action><voucher_name>" + coupon_data + "</voucher_name></cart></prestashop>";


        RequestBody body = RequestBody.create(JSON, url);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "cart/?output_format=JSON")
                .method("POST", body)
                .build();

        Log.e("Coupon ", String.valueOf(request.url()));

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
                Cart_Coupon_Error coupon_error = new Cart_Coupon_Error();

                ArrayList<Cart_Product> cart_products = new ArrayList<Cart_Product>();

                Cart_Total cart_total = new Cart_Total();
                Cart_SubTotal cart_subTotal = new Cart_SubTotal();
                Cart_Vouchers cart_vouchers = new Cart_Vouchers();
                Cart_Label cart_label = new Cart_Label();


                String id = "";

                try {
                    JSONObject json = new JSONObject(cartResponse);
                    if (json.has("error")) {
                        JSONArray cart_error = json.getJSONArray("error");
                        ArrayList<String> error_list = new ArrayList<String>();
                        error_list.add(cart_error.getString(0));
                        coupon_error.setError(error_list);
                        mListener.AddCoupon(coupon_error);

                    } else {
                        JSONObject cart_json = json.getJSONObject("cart");
                        JSONArray product_array = cart_json.getJSONArray("products");
                        cart_products.clear();


                        Cart cart = (Cart) gson.fromJson(json.getJSONObject("cart").toString(), Cart.class);
                        String cart_data = gson.toJson(cart);
                        editor_login.putString(Pref_Data.CART_DATA, cart_data);
                        editor_login.commit();
                        cartObj.setIdCart(id);
                        cartObj.setCart(cart);


                        if (cartResponse.toString() != null) {
                            mListener.AddToCart(cartObj);
                        }
                    }
                } catch (Exception e) {
                    Log.e("AddToCart Exceptions: ", e.toString());
                }

            }
        });
    }


    public void RemoveCoupon(String coupon_id) {
        getView().showProgress();
        String cart_id = pref_login.getString(Constants.CART_ID, "");
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        String url = "<prestashop><cart><id_guest>" + guest_user_id + "</id_guest><id_cart>" + cart_id + "</id_cart><id_customer>" + user_id + "</id_customer><action>voucher</action><deletevoucher>" + coupon_id + "</deletevoucher></cart></prestashop>";


        RequestBody body = RequestBody.create(JSON, url);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "cart/?output_format=JSON")
                .method("POST", body)
                .build();

        Log.e("Get Cart url", String.valueOf(request.url()));

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
                Cart_Coupon_Error coupon_error = new Cart_Coupon_Error();

                ArrayList<Cart_Product> cart_products = new ArrayList<Cart_Product>();

                Cart_Total cart_total = new Cart_Total();
                Cart_SubTotal cart_subTotal = new Cart_SubTotal();
                Cart_Vouchers cart_vouchers = new Cart_Vouchers();
                Cart_Label cart_label = new Cart_Label();


                String id = "";

                try {
                    JSONObject json = new JSONObject(cartResponse);

                    JSONObject cart_json = json.getJSONObject("cart");
                    JSONArray product_array = cart_json.getJSONArray("products");
                    cart_products.clear();


                    Cart cart = (Cart) gson.fromJson(json.getJSONObject("cart").toString(), Cart.class);
                    String cart_data = gson.toJson(cart);
                    editor_login.putString(Pref_Data.CART_DATA, cart_data);
                    editor_login.commit();
                    cartObj.setIdCart(id);
                    cartObj.setCart(cart);


                    if (cartResponse.toString() != null) {
                        mListener.AddToCart(cartObj);
                    }

                } catch (Exception e) {
                    Log.e("AddToCart Exceptions: ", e.toString());
                }

            }
        });
    }

    @Override
    public void AddToCart(final Cart_Response cart_response) {

    }

    @Override
    public void removeCart(Cart_Response cart_response) {

    }

    @Override
    public void getCart(Cart_Response cart_response) {

    }

    @Override
    public void AddCoupon(Cart_Coupon_Error cart_response) {

    }

    @Override
    public void DeleteCoupon(Cart_Response cart_response) {

    }
}
