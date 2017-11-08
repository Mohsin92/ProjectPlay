package com.varshaaweblabs.ecommerce.Account.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Account.View.MyAccountView;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginCustomer_Data;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dinesh on 25/10/17.
 */

public class AddEditUserPresenter extends BasePresenter<MyAccountView.View> implements IAdd_Edit_User_Interface {


    private final android.content.Context context;

    private final IAdd_Edit_User_Interface mListner;
    String cart_id;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;

    public AddEditUserPresenter(Context context, IAdd_Edit_User_Interface mListner) {
        this.context = context;
        this.mListner = mListner;
        pref_login = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();

    }


    public void createAccount(String data, String link) throws IOException {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, data);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + link)
                .method("POST", body)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                getView().hideProgress();
                call.cancel();
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
                        JSONArray email = json.getJSONObject("error").getJSONArray("email");


                        LoginCustomer_Data cust_data = new LoginCustomer_Data();
                        cust_data.setError_msg(email.get(0).toString());
                        if (cust_data.toString() != null) {
                            mListner.Create_Account(cust_data);

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
                                mListner.Create_Account(cust_data);

                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

    }


    @Override
    public void Create_Account(LoginCustomer_Data customer_data) {

    }
}
