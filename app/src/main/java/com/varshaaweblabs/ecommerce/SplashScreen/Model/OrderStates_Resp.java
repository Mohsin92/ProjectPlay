package com.varshaaweblabs.ecommerce.SplashScreen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 26/10/17.
 */

public class OrderStates_Resp implements Serializable {

    @SerializedName("order_states")
    @Expose
    private List<OrderState_Data> orderStates = null;

    public List<OrderState_Data> getOrderStates() {
        return orderStates;
    }

    public void setOrderStates(List<OrderState_Data> orderStates) {
        this.orderStates = orderStates;
    }
}
