package com.varshaaweblabs.ecommerce.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginCustomer_Resp;

import org.w3c.dom.Document;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by dinesh on 24/8/17.
 */

public class Services {
    private static String BASE_URL = "http://prestashop.varshaawebdemo.com/api/";

    public interface API {

        @POST("account/login?output_format=JSON&display=full")
        Call<LoginCustomer_Resp> login(@Body Document data);
    }

    public OkHttpClient getAPI() {


        final OkHttpClient okHttpClient_auth = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                        MediaType JSON
                                = MediaType.parse("application/xml; charset=utf-8");

                        RequestBody body = RequestBody.create(JSON, "<prestashop><customer> <email>myk1@abc.com</email> <passwd>myk786</passwd></customer></prestashop>");

                        okhttp3.Request request = new okhttp3.Request.Builder()
                                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                .url("http://prestashop.varshaawebdemo.com/api/account/login?output_format=JSON&display=full")
                                .method("POST", body)
                                .build();

                        return chain.proceed(request);
                    }
                }).build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        final OkHttpClient okHttpClient_reg = new OkHttpClient.Builder()
//                .readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .addInterceptor(interceptor)
//                .addInterceptor(new BasicAuthInterceptor("M8UDKAEGZVF7Z6B4DHZNPZNTQ25UHNXC", ""))
//                .build();


//        Retrofit retrofit = new Retrofit
//                .Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(okHttpClient_auth)
//                .build();
//        return retrofit.create(API.class);
        return okHttpClient_auth;
    }
}