package com.varshaaweblabs.ecommerce.OrderDetails.View;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.varshaaweblabs.ecommerce.Account.View.MyAccountView;
import com.varshaaweblabs.ecommerce.OrderDetails.Adapter.OrderHeaderListAdapter;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.GetOrder_Data;
import com.varshaaweblabs.ecommerce.OrderDetails.Model.GetOrder_Resp;
import com.varshaaweblabs.ecommerce.OrderDetails.Presenter.IUser_Order_ListInterface;
import com.varshaaweblabs.ecommerce.OrderDetails.Presenter.User_Order_List_Presenter;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.R;

import java.util.ArrayList;

/**
 * Created by dinesh on 28/10/17.
 */

public class User_Order_Activity extends BaseActivity implements IUser_Order_ListInterface, MyAccountView.View {

    ListView lv_order_list;
    OrderHeaderListAdapter order_list_adapter;
    User_Order_List_Presenter order_list_presenter;
    ArrayList<GetOrder_Data> order_dataresp_list = new ArrayList<>();
    LinearLayout ll_empty_order_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_full_order_list);
        order_list_presenter = new User_Order_List_Presenter(this, this);
        order_list_presenter.attachView(this);
        lv_order_list = (ListView) findViewById(R.id.lv_order_list);
        ll_empty_order_layout=(LinearLayout) findViewById(R.id.ll_empty_order_layout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order List");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        try {
            order_list_presenter.getOrderList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getOrderList(final GetOrder_Resp order_list_resp) {
        User_Order_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                order_dataresp_list.clear();
                if(order_list_resp!=null) {
                    ll_empty_order_layout.setVisibility(View.GONE);
                    lv_order_list.setVisibility(View.VISIBLE);
                    for (int i = 0; i < order_list_resp.getOrders().size(); i++) {
                        order_dataresp_list.add(order_list_resp.getOrders().get(i));
                    }

                    order_list_adapter = new OrderHeaderListAdapter(User_Order_Activity.this, order_dataresp_list);
                    lv_order_list.setAdapter(order_list_adapter);
                }else {
                    ll_empty_order_layout.setVisibility(View.VISIBLE);
                    lv_order_list.setVisibility(View.GONE);
                }
            }
        });
    }
}
