package com.varshaaweblabs.ecommerce.PlaceOrder.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.Country_List_resp;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.Country_Model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.State_List_Resp;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.State_Model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_List;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;
import com.varshaaweblabs.ecommerce.PlaceOrder.View.OrderSummeryView;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dinesh on 14/10/17.
 */

public class Add_Edit_AddressPresenter extends BasePresenter<OrderSummeryView.View> implements IAdd_Edit_AddressInterface {


    private final android.content.Context context;
    private final IAdd_Edit_AddressInterface mListner;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;

    public Add_Edit_AddressPresenter(Context context, IAdd_Edit_AddressInterface mListner) {
        this.context = context;
        this.mListner = mListner;
        pref_login = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();
    }


    //Get all country

    public void getAllCountry() throws IOException {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        String url = Constants.BASE_URL + "countries/?output_format=JSON&display=full&filter[active]=1";


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(url)
                .method("GET", null)
                .build();


        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getView().hideProgress();
                Log.e("Country Call Fail: ", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                Country_Model country_model = new Country_Model();
                Gson gson = new Gson();

                ArrayList<Country_List_resp> countr_lists = new ArrayList<Country_List_resp>();
                JSONObject json = null;


                try {
                    json = new JSONObject(myResponse);
                    JSONArray country = json.getJSONArray("countries");

                    for (int i = 0; i < country.length(); i++) {
                        Country_List_resp countryresp = new Country_List_resp();
                        countryresp = (Country_List_resp) gson.fromJson(country.getJSONObject(i).toString(), Country_List_resp.class);
                        countr_lists.add(countryresp);

                    }
                    country_model.setCountries(countr_lists);
                    String country_data = gson.toJson(country_model);
                    editor_login.putString(Pref_Data.COUNTRIES, country_data);
                    editor_login.commit();
                    if (myResponse.toString() != null) {
                        mListner.getCountry(country_model);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getView().hideProgress();
            }
        });

    }

    //Get all States

    public void getAllStates() throws IOException {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        String url = Constants.BASE_URL + "/states/?output_format=JSON&display=full&filter[active]=1&filter[id_country]=21";


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(url)
                .method("GET", null)
                .build();


        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getView().hideProgress();
                Log.e("State Call Fail: ", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                State_Model state_model = new State_Model();
                Gson gson = new Gson();

                ArrayList<State_List_Resp> state_lists = new ArrayList<State_List_Resp>();
                JSONObject json = null;


                try {
                    json = new JSONObject(myResponse);
                    JSONArray states = json.getJSONArray("states");

                    for (int i = 0; i < states.length(); i++) {
                        State_List_Resp state_list_resp = new State_List_Resp();
                        state_list_resp = (State_List_Resp) gson.fromJson(states.getJSONObject(i).toString(), State_List_Resp.class);
                        state_lists.add(state_list_resp);

                    }


                    state_model.setStates(state_lists);
                    String state_data = gson.toJson(state_model);
                    editor_login.putString(Pref_Data.STATES, state_data);
                    editor_login.commit();
                    if (myResponse.toString() != null) {
                        mListner.getState(state_model);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getView().hideProgress();
            }
        });

    }

    //API CAll for Add Adress

    public void addAddress(String data) {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, data);


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(Constants.BASE_URL + "addresses/?output_format=JSON&display=full")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .method("POST", body)
                .build();

        Log.e("get_add_url", request.url().toString());

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
                Toast.makeText(context, "Error in Establish the connection", Toast.LENGTH_SHORT).show();
                Log.e("GetAddress Call Fail: ", e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                final String myResponse = response.body().string();
                User_Address_model user_add = new User_Address_model();
                Gson gson = new Gson();

                ArrayList<User_Address_List> user_address_lists = new ArrayList<User_Address_List>();
                JSONObject json = null;


                try {
                    json = new JSONObject(myResponse);
                    JSONArray address = json.getJSONArray("addresses");

                    for (int i = 0; i < address.length(); i++) {
                        User_Address_List addresp = new User_Address_List();
                        addresp = (User_Address_List) gson.fromJson(address.getJSONObject(i).toString(), User_Address_List.class);
                        user_address_lists.add(addresp);

                    }

                    user_add.setAddresses(user_address_lists);
                    if (myResponse.toString() != null) {
                        mListner.addAddress(user_add);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("GetAddress Exceptions: ", e.toString());
                }

                getView().hideProgress();
            }
        });
    }

    public void editAddress(String data) {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, data);


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(Constants.BASE_URL + "addresses/?output_format=JSON&display=full")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .method("PUT", body)
                .build();

        Log.e("edit_add_url", request.url().toString());
        Log.e("Body", data);

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
                Toast.makeText(context, "Error in Establish the connection", Toast.LENGTH_SHORT).show();
                Log.e("GetAddress Call Fail: ", e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                final String myResponse = response.body().string();
                User_Address_model user_add = new User_Address_model();
                Gson gson = new Gson();

                ArrayList<User_Address_List> user_address_lists = new ArrayList<User_Address_List>();
                JSONObject json = null;


                try {
                    json = new JSONObject(myResponse);
                    JSONArray address = json.getJSONArray("addresses");

                    for (int i = 0; i < address.length(); i++) {
                        User_Address_List addresp = new User_Address_List();
                        addresp = (User_Address_List) gson.fromJson(address.getJSONObject(i).toString(), User_Address_List.class);
                        user_address_lists.add(addresp);

                    }


                    user_add.setAddresses(user_address_lists);
                    if (myResponse.toString() != null) {
                        mListner.editAddress(user_add);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("GetAddress Exceptions: ", e.toString());
                }

                getView().hideProgress();
            }
        });
    }


    @Override
    public void getCountry(Country_Model country_resp) {

    }

    @Override
    public void getState(State_Model state_resp) {

    }

    @Override
    public void addAddress(User_Address_model add_address_list) {

    }

    @Override
    public void editAddress(User_Address_model edit_address_list) {

    }
}
