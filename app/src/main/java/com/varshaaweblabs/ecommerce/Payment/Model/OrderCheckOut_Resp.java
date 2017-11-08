package com.varshaaweblabs.ecommerce.Payment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 30/10/17.
 */

public class OrderCheckOut_Resp implements Serializable {
    @SerializedName("id_order")
    @Expose
    private String idOrder;

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }
}
