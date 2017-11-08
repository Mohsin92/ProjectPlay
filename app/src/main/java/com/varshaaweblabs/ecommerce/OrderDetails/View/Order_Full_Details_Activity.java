package com.varshaaweblabs.ecommerce.OrderDetails.View;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.OrderDetails.Adapter.OrderFullDetailsAdapter;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.GetOrder_Data;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.GetOrder_Resp;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.OrderItemRow;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.util.ArrayList;

/**
 * Created by dinesh on 31/10/17.
 */

public class Order_Full_Details_Activity extends BaseActivity {

    Gson gson;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor;
    GetOrder_Resp full_order_resp;
    ArrayList<GetOrder_Data> order_resp_data = new ArrayList<>();
    ArrayList<OrderItemRow> order_prod_data = new ArrayList<>();
    TextView tv_order_date, tv_delivery_date, tv_order_subtotal_value, tv_order_pay_module_value, tv_order_shipping_value, tv_cod_charges_value, tv_Order_total;
    ListView lv_order_full_details;
    OrderFullDetailsAdapter orderproductdetailsadapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_full_order_details);

        pref_login = Order_Full_Details_Activity.this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor = pref_login.edit();

        gson = new Gson();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_order_date = (TextView) findViewById(R.id.tv_order_date);
        tv_delivery_date = (TextView) findViewById(R.id.tv_delivery_date);
        tv_order_subtotal_value = (TextView) findViewById(R.id.tv_order_subtotal_value);
        tv_order_pay_module_value = (TextView) findViewById(R.id.tv_order_pay_module_value);
        tv_order_shipping_value = (TextView) findViewById(R.id.tv_order_shipping_value);
        tv_cod_charges_value = (TextView) findViewById(R.id.tv_cod_charges_value);
        tv_Order_total = (TextView) findViewById(R.id.tv_Order_total);
        lv_order_full_details = (ListView) findViewById(R.id.lv_order_full_details);

        full_order_resp = gson.fromJson(pref_login.getString(Pref_Data.ORDER_DATA, ""), GetOrder_Resp.class);
        order_resp_data.clear();
        order_prod_data.clear();
        for (int i = 0; i < full_order_resp.getOrders().size(); i++) {
            order_resp_data.add(full_order_resp.getOrders().get(i));
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setData();

    }

    private void setData() {
        for (int i = 0; i < order_resp_data.size(); i++) {
            if (order_resp_data.get(i).getId().toString().equalsIgnoreCase(Constants.ORDER_ID)) {
                getSupportActionBar().setTitle("Order ID : " + order_resp_data.get(i).getReference());
                String del_date = order_resp_data.get(i).getDeliveryDate();
                String[] parts_date = del_date.split(" ");
                String date = parts_date[0];
                tv_order_date.setText("Placed On : " + order_resp_data.get(i).getDateAdd());
                tv_delivery_date.setText("Delivered On " + date);
                tv_order_subtotal_value.setText("$" + Double.valueOf(order_resp_data.get(i).getTotalPaid()));
                tv_order_pay_module_value.setText(order_resp_data.get(i).getPayment());
                tv_order_shipping_value.setText("$"+ Double.valueOf(order_resp_data.get(i).getTotalShipping()));
                tv_cod_charges_value.setText("$" + Double.valueOf(order_resp_data.get(i).getCarrierTaxRate()));
                tv_Order_total.setText("$" + Double.valueOf(order_resp_data.get(i).getTotalPaid()));
                order_prod_data.clear();
                for (int j = 0; j < order_resp_data.get(i).getAssociations().getOrderRows().size(); j++) {
                    order_prod_data.add(order_resp_data.get(i).getAssociations().getOrderRows().get(j));
                }
                orderproductdetailsadapter = new OrderFullDetailsAdapter(Order_Full_Details_Activity.this, order_prod_data);
                lv_order_full_details.setAdapter(orderproductdetailsadapter);
            }
        }
    }
}
