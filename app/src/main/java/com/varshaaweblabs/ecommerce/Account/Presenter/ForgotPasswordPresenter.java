package com.varshaaweblabs.ecommerce.Account.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Account.View.MyAccountView;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.Utility.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by dinesh on 26/10/17.
 */

public class ForgotPasswordPresenter extends BasePresenter<MyAccountView.View> implements IForgotPasswordInterface {

    private final android.content.Context context;

    private final IForgotPasswordInterface mListner;

    public ForgotPasswordPresenter(Context context, IForgotPasswordInterface mListner) {
        this.context = context;
        this.mListner = mListner;
    }

    //API CAll


    public void forgotPassword(String data) throws IOException {

        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, data);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "account/password-recovery?output_format=JSON")
                .method("POST", body)
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
                Gson gson = new Gson();
                try {
                    JSONObject json = new JSONObject(myResponse);
                    if (json.has("error")) {
                        if (myResponse.toString() != null) {
                            mListner.forgetpass(json.getString("error").toString());

                        }
                    } else {
                        mListner.forgetpass(json.getString("success").toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

    }


    @Override
    public void forgetpass(String msg) {

    }
}
