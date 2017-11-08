package com.varshaaweblabs.ecommerce.Payment.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
import com.varshaaweblabs.ecommerce.Payment.Model.OrderCheckOut_Resp;
import com.varshaaweblabs.ecommerce.Payment.Model.PaymentModule_Data;
import com.varshaaweblabs.ecommerce.Payment.Model.PaymentModule__Resp;
import com.varshaaweblabs.ecommerce.Payment.View.IPaymentView;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dinesh on 13/10/17.
 */

public class PaymentPresenter extends BasePresenter<IPaymentView.View> implements IPaymentInterface {


    private final android.content.Context context;

    private final IPaymentInterface mListner;

    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;


    public PaymentPresenter(Context context, IPaymentInterface mListner) {
        this.context = context;
        this.mListner = mListner;
        pref_login = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();

    }

    // API call to get Access Token for PayPal

    public void getToken() throws Exception {

        new PostClass(context).execute();
    }

    public void getTransactionId(String paymentDetails) {

        String token = pref_login.getString(Constants.PAYPAL_TOKEN, "");
        if (token.equalsIgnoreCase("")) {
            Toast.makeText(context, "Token is Null", Toast.LENGTH_SHORT).show();
        } else {
//            OkHttpClient client = new OkHttpClient();
//
//            final okhttp3.Request request = new okhttp3.Request.Builder()
//                    .addHeader("Content-Type", "application/json")
//                    .addHeader("Authorization", "Bearer " + token)
//                    .url("https://api.sandbox.paypal.com/v1/payments/payment/" + paymentDetails)
//                    .method("GET", null)
//                    .build();
//
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    call.cancel();
//                    Log.e("Fail: ", e.toString());
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    String resp = response.body().toString();
//                    Log.e("Resp: ", resp);
//                    JSONObject jsonObject = null;
//                    try {
//                        jsonObject = new JSONObject(resp);
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
            new GetTransactionId(context, token, paymentDetails).execute();

        }


    }


    private class PostClass extends AsyncTask<String, Void, Void> {

        private final Context context;

        public PostClass(Context c) {

            this.context = c;
//            this.error = status;
//            this.type = t;
        }

        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                final String basicAuth = "Basic " + Base64.encodeToString("AdVsqemLR7wb817WklDh3e1DLS0NaOkQVwn7kk1QhPT8yQXwjZ0IKXL3kuk1rlB7F-ttgQHx-mzilAby:EFTet5UyBwOk8oMXxd8xS-XkFmBB1PIWzBqnlMcXc6d56ADPhbsFqDpSyRFotKhQnAwt9YF6KQ_t8Zae".getBytes("UTF-8"), Base64.NO_WRAP);
                URL url = new URL("https://api.sandbox.paypal.com/v1/oauth2/token");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                String urlParameters = "grant_type=client_credentials";
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization", basicAuth);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(urlParameters);
                dStream.flush();
                dStream.close();
                int responseCode = connection.getResponseCode();

                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);

                final StringBuilder output = new StringBuilder("Request URL " + url);
                output.append(System.getProperty("line.separator") + "Request Parameters " + urlParameters);
                output.append(System.getProperty("line.separator") + "Response Code " + responseCode);
                output.append(System.getProperty("line.separator") + "Type " + "POST");
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder responseOutput = new StringBuilder();
                System.out.println("output===============" + br);
                while ((line = br.readLine()) != null) {
                    responseOutput.append(line);
                }
                br.close();

                try {
                    JSONObject json = new JSONObject(String.valueOf(responseOutput));

                    String token = json.getString("access_token");
                    editor_login.putString(Constants.PAYPAL_TOKEN, token);
                    editor_login.commit();

                    Log.e("Token: ", token);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                output.append(System.getProperty("line.separator") + "Response " + System.getProperty("line.separator") + System.getProperty("line.separator") + responseOutput.toString());

//                MainActivity.this.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        outputView.setText(output);
//                        progress.dismiss();
//                    }
//                });


            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute() {
//            progress.dismiss();
        }

    }

    private class GetTransactionId extends AsyncTask<String, Void, Void> {

        private final Context context;
        private String token, paymentDetails;

        public GetTransactionId(Context c, String token, String paymentDetails) {

            this.context = c;
            this.token = token;
            this.paymentDetails = paymentDetails;
//            this.error = status;
//            this.type = t;
        }

        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                final String basicAuth = "Basic " + Base64.encodeToString("AdVsqemLR7wb817WklDh3e1DLS0NaOkQVwn7kk1QhPT8yQXwjZ0IKXL3kuk1rlB7F-ttgQHx-mzilAby:EFTet5UyBwOk8oMXxd8xS-XkFmBB1PIWzBqnlMcXc6d56ADPhbsFqDpSyRFotKhQnAwt9YF6KQ_t8Zae".getBytes("UTF-8"), Base64.NO_WRAP);
                URL url = new URL("https://api.sandbox.paypal.com/v1/payments/payment/" + paymentDetails);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                String urlParameters = "grant_type=client_credentials";
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", "Bearer " + token);
                connection.setRequestProperty("Content-Type", "application/json");
//
                int responseCode = connection.getResponseCode();

                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);

                final StringBuilder output = new StringBuilder("Request URL " + url);
                //output.append(System.getProperty("line.separator") + "Request Parameters " + urlParameters);
                output.append(System.getProperty("line.separator") + "Response Code " + responseCode);
                output.append(System.getProperty("line.separator") + "Type " + "GET");
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder responseOutput = new StringBuilder();
                System.out.println("output===============" + br);
                while ((line = br.readLine()) != null) {
                    responseOutput.append(line);
                }
                br.close();

                output.append(System.getProperty("line.separator") + "Response " + System.getProperty("line.separator") + System.getProperty("line.separator") + responseOutput.toString());

                try {
                    JSONObject json = new JSONObject(String.valueOf(responseOutput));

                    JSONArray transaction = json.getJSONArray("transactions");
                    for (int i = 0; i < transaction.length(); i++) {
                        JSONArray jsonArray = transaction.getJSONObject(i).getJSONArray("related_resources");


                        JSONObject sale_obj = jsonArray.getJSONObject(0).getJSONObject("sale");
                        Log.e("TransactionId:  ", sale_obj.getString("id"));
                        String id = sale_obj.getString("id");
                        editor_login.putString(Constants.TRANSACTION_ID, id);
                        editor_login.commit();

                        mListner.Paypal_Tranasaction();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                output.append(System.getProperty("line.separator") + "Response " + System.getProperty("line.separator") + System.getProperty("line.separator") + responseOutput.toString());

//                MainActivity.this.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        outputView.setText(output);
//                        progress.dismiss();
//                    }
//                });


            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute() {
//            progress.dismiss();
        }

    }

    //API Call for Get Payment Module
    public void getPaymentModule() throws Exception {
        getView().showProgress();

        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");


        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(Constants.BASE_URL + "paymentmodule/?output_format=JSON&display=full")
                .method("GET", null)
                .build();


        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
                Log.e("Error", e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                final String myResponse = response.body().string();
                PaymentModule__Resp payment_resp = new PaymentModule__Resp();

                Gson gson = new Gson();
                ArrayList<PaymentModule_Data> payment_data_array = new ArrayList<PaymentModule_Data>();


                JSONObject json = null;
                try {
                    json = new JSONObject(myResponse);
                    JSONArray payment_module_array = json.getJSONArray("PaymentModule");


                    for (int i = 0; i < payment_module_array.length(); i++) {
                        PaymentModule_Data payment_data_object = new PaymentModule_Data();
                        payment_data_object = (PaymentModule_Data) gson.fromJson(payment_module_array.getJSONObject(i).toString(), PaymentModule_Data.class);
                        payment_data_array.add(payment_data_object);

                    }
                    payment_resp.setPaymentModule(payment_data_array);
                    String pay_data = gson.toJson(payment_resp);
                    editor_login.putString(Pref_Data.PAYMENT_MODULE, pay_data);
                    editor_login.commit();


                    if (myResponse.toString() != null) {
                        mListner.PaymentOption(payment_resp);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error", e.toString());
                }

                getView().hideProgress();

            }

        });

    }

    //API Call for Checkout
    public void checkOutAPI() {
        getView().showProgress();
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        String url = "";
        String user_id = pref_login.getString(Constants.USER_ID, "");
        String cart_id = pref_login.getString(Constants.CART_ID, "");
        String add_id = pref_login.getString(Constants.ADDRESS_ID, "");
        String transaction_id = pref_login.getString(Constants.TRANSACTION_ID, "");

        if (Constants.PAY_ID.equalsIgnoreCase("1")) {  // for COD

            url = "<prestashop><order><id_customer>" + user_id + "</id_customer><id_cart>" + cart_id + "</id_cart><payment>" + Constants.PAY_OPTION_PAYMENT + "</payment><module>" + Constants.PAY_OPTION_MODULE + "</module><id_carrier>2</id_carrier><id_address_delivery>" + add_id + "</id_address_delivery><id_address_invoice>" + add_id + "</id_address_invoice></order></prestashop>";

        } else {  // for PAYPAL
            url = "<prestashop><order><id_customer>" + user_id + "</id_customer><id_cart>" + cart_id + "</id_cart><payment>" + Constants.PAY_OPTION_PAYMENT + "</payment><module>" + Constants.PAY_OPTION_MODULE + "</module><extra_vars><transaction_id>" + transaction_id + "</transaction_id></extra_vars><id_carrier>2</id_carrier><id_address_delivery>" + add_id + "</id_address_delivery><id_address_invoice>" + add_id + "</id_address_invoice></order></prestashop>";
        }
        RequestBody body = RequestBody.create(JSON, url);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(Constants.BASE_URL + "checkout?output_format=JSON&display=full")
                .method("POST", body)
                .build();

        Log.e("Get Cart url", String.valueOf(request.url()));

        client.newCall(request).enqueue(new okhttp3.Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                Log.e("Error", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                OrderCheckOut_Resp chreck_resp = new OrderCheckOut_Resp();
                Gson gson = new Gson();
                String order_id;
                JSONObject json = null;
                try {
                    json = new JSONObject(myResponse);
                    if (json.has("id_order")) {
                        getView().hideProgress();
                        order_id = String.valueOf(json.getInt("id_order"));
                        chreck_resp.setIdOrder(order_id);
                        mListner.checkOut(chreck_resp);
                    } else {
                        getView().hideProgress();
                        mListner.checkOut(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("error", e.toString());
                }

            }
        });
    }

    @Override
    public void PaymentOption(PaymentModule__Resp payment_option) {

    }

    @Override
    public void checkOut(OrderCheckOut_Resp checkout) {

    }

    @Override
    public void Paypal_Tranasaction() {

    }
}
