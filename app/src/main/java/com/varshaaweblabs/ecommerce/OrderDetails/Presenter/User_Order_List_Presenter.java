package com.varshaaweblabs.ecommerce.OrderDetails.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.GetOrder_Data;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.GetOrder_Resp;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.OrderItemRow;
import com.varshaaweblabs.ecommerce.Account.View.MyAccountView;
import com.varshaaweblabs.ecommerce.Base.BasePresenter;
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
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dinesh on 28/10/17.
 */

public class User_Order_List_Presenter extends BasePresenter<MyAccountView.View> implements IUser_Order_ListInterface {


    private final android.content.Context context;

    private final IUser_Order_ListInterface mListner;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;

    public User_Order_List_Presenter(Context context, IUser_Order_ListInterface mListner) {
        this.context = context;
        this.mListner = mListner;

        pref_login = context.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();

    }


    public void getOrderList() throws Exception {
        getView().showProgress();

        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.parse("application/xml; charset=utf-8");

        String user_id = pref_login.getString(Constants.USER_ID, "");
        okhttp3.Request request = new okhttp3.Request.Builder()
                .addHeader("Authorization", "Basic TThVREtBRUdaVkY3WjZCNERIWk5QWk5UUTI1VUhOWEM6")
                .url(Constants.BASE_URL + "orders?output_format=JSON&display=full&filter[id_customer]=" + user_id)
                .method("GET", null)
                .build();

        Log.e("User_id", user_id);

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                getView().hideProgress();
                final String myResponse = response.body().string();
                if (myResponse.toString().equalsIgnoreCase("")) {


                    mListner.getOrderList(null);

                } else {


                    GetOrder_Resp order_resp = new GetOrder_Resp();

                    Gson gson = new Gson();
                    ArrayList<GetOrder_Data> order_data_list = new ArrayList<GetOrder_Data>();
                    ArrayList<OrderItemRow> orderItemRows_list = new ArrayList<OrderItemRow>();


                    JSONObject json = null;
                    try {
                        json = new JSONObject(myResponse);
                        JSONArray order_resp_array = json.getJSONArray("orders");
//                    order_resp_array.getJSONObject(0).getJSONObject("associations").getJSONArray("order_rows");


                        for (int i = 0; i < order_resp_array.length(); i++) {
                            GetOrder_Data order_data = new GetOrder_Data();
                            order_data = (GetOrder_Data) gson.fromJson(order_resp_array.getJSONObject(i).toString(), GetOrder_Data.class);
                            order_data_list.add(order_data);
//                        OrderItemRow order_item_data=new OrderItemRow();
//                        order_item_data=(OrderItemRow)gson.fromJson(order_resp_array.getJSONObject(i).getJSONObject("associations").getJSONArray("order_rows").toString(), OrderItemRow.class);
//                        orderItemRows_list.add(order_item_data);
                        }

                        order_resp.setOrders(order_data_list);
                        String order_data = gson.toJson(order_resp);
                        editor_login.putString(Pref_Data.ORDER_DATA,order_data);
                        editor_login.commit();

                        mListner.getOrderList(order_resp);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }


        });
    }

    @Override
    public void getOrderList(GetOrder_Resp order_list_resp) {

    }
}
