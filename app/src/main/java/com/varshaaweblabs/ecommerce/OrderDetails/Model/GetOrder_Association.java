package com.varshaaweblabs.ecommerce.OrderDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 26/10/17.
 */

public class GetOrder_Association implements Serializable {
    @SerializedName("order_rows")
    @Expose
    private List<OrderItemRow> orderRows = null;

    public List<OrderItemRow> getOrderRows() {
        return orderRows;
    }

    public void setOrderRows(List<OrderItemRow> orderRows) {
        this.orderRows = orderRows;
    }
}
