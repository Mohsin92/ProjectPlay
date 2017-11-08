package com.varshaaweblabs.ecommerce.ProductFullDetails.Presenter;

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
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Combination;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Custmization_Resp;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Group;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.ProductFull;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.ProductFull_Features;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.ProductFull_Product;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Product_Recommend;
import com.varshaaweblabs.ecommerce.ProductFullDetails.View.ProductFullDetailsView;
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
 * Created by dinesh on 23/9/17.
 */

public class ProductFullDetailsPresenter extends BasePresenter<ProductFullDetailsView.View> implements IProductFullDetailsInterface {


    private final android.content.Context context;

    private final IProductFullDetailsInterface mListner;
    String cart_id;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;

    public ProductFullDetailsPresenter(Context context, IProductFullDetailsInterface mListner) {
        this.context = context;
        this.mListner = mListner;
        pref_login = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();

    }


    //API Call for full Product Details

    public void getfullproducts(String data) throws Exception {

        getView().showProgress();

        data = Constants.PROD_COMBINATIONID;

        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

//        String id_category_layerd=null;
        cart_id = pref_login.getString(Constants.CART_ID, "");
        String url = Constants.BASE_URL + "productdetail/?id_product=" + Constants.FULLPRODUCT_ID + "&id_product_attribute=" + data + "id_cart=" + cart_id + "&output_format=JSON";

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(url)
                .method("GET", null)
                .build();

        Log.e("url", request.url().toString());

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
                Log.e("Error", e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                final String myResponse = response.body().string();
                ProductFull full_prodresp = new ProductFull();

                Gson gson = new Gson();
                ArrayList<Group> prd_group = new ArrayList<Group>();
                ArrayList<ProductFull_Features> prod_feature = new ArrayList<ProductFull_Features>();
                ArrayList<Combination> prod_combi_list = new ArrayList<Combination>();
                ArrayList<Product_Recommend> prod_recomm_list = new ArrayList<Product_Recommend>();

                JSONObject json = null;
                try {
                    json = new JSONObject(myResponse);
                    JSONObject prod = json.getJSONObject("product");
                    Object manufac = json.get("manufacturer_image_url");
                    JSONArray group = json.getJSONArray("groups");
                    JSONArray combination = json.getJSONArray("combinations");
                    JSONArray prod_recomm = json.getJSONArray("accessories");
//                    JSONArray prod_features=json.getJSONArray("features");
                    ProductFull_Product product_data = new ProductFull_Product();
                    Combination prod_combi = new Combination();
                    String manu_url = manufac.toString();


                    for (int i = 0; i < prod.length(); i++) {
                        product_data = (ProductFull_Product) gson.fromJson(prod.toString(), ProductFull_Product.class);

                    }
                    //TODO for Product Recommened

                    for (int i = 0; i < prod_recomm.length(); i++) {
                        Product_Recommend prod_recomm_resp = new Product_Recommend();
                        prod_recomm_resp = (Product_Recommend) gson.fromJson(prod_recomm.getJSONObject(i).toString(), Product_Recommend.class);
                        prod_recomm_list.add(prod_recomm_resp);
                    }
                    for (int i = 0; i < group.length(); i++) {
                        Group groupresp = new Group();
                        groupresp = (Group) gson.fromJson(group.getJSONObject(i).toString(), Group.class);
                        prd_group.add(groupresp);

                    }

                    for (int i = 0; i < combination.length(); i++) {
                        prod_combi = (Combination) gson.fromJson(combination.getJSONObject(i).toString(), Combination.class);
                        prod_combi_list.add(prod_combi);
                    }


//                    for(int i=0;i<prod_features.length();i++){
//                        ProductFull_Features prodfull_feat=new ProductFull_Features();
//                        prodfull_feat=(ProductFull_Features)gson.fromJson(prod_features.getJSONObject(i).toString(),ProductFull_Features.class);
//                        prod_feature.add(prodfull_feat);
//                    }


                    full_prodresp.setCombinations(prod_combi_list);
                    full_prodresp.setProduct(product_data);
                    full_prodresp.setGroups(prd_group);
                    full_prodresp.setManufacturerImageUrl(manu_url);
                    full_prodresp.setAccessories(prod_recomm_list); //TODO for Product Recommened

                    if (myResponse.toString() != null) {

                        mListner.Prod_FullData(full_prodresp, getView());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error", e.toString());
                }

//                getView().hideProgress();

            }

        });


    }



    //API Call for getting Custmization

    public void getCustmization(String data) throws IOException {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, data);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "productcustomization/?output_format=JSON&display=full")
                .method("POST", body)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                Toast.makeText(context, "Error in Establish the connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                JSONObject json = null;
                Custmization_Resp resp_cust = new Custmization_Resp();

                try {
                    json = new JSONObject(myResponse);
                    Object customization = json.get("id_customization");
                    Object cart = json.get("id_cart");
                    String id_customization = customization.toString();
                    String id_cart = cart.toString();
                    resp_cust.setIdCustomization(id_customization);
                    resp_cust.setIdCart(id_cart);
                    Log.e("Presenter_cartID", id_cart);


                    if (myResponse.toString() != null) {
                        getView().hideProgress();
                        mListner.getCustmizations(resp_cust);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        });
    }

    @Override
    public void Prod_FullData(ProductFull prod_full, ProductFullDetailsView.View view) {

    }

    @Override
    public void getCustmizations(Custmization_Resp resp_cust) {

    }



}
