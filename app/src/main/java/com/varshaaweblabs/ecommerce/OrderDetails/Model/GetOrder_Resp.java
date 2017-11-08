package com.varshaaweblabs.ecommerce.OrderDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 26/10/17.
 */

public class GetOrder_Resp implements Serializable {

    @SerializedName("orders")
    @Expose
    private List<GetOrder_Data> orders = null;

    public List<GetOrder_Data> getOrders() {
        return orders;
    }

    public void setOrders(List<GetOrder_Data> orders) {
        this.orders = orders;
    }
}
