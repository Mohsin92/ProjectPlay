package com.varshaaweblabs.ecommerce.LoginActivity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 24/8/17.
 */

public class LoginCustomer_Resp implements Serializable {
    @SerializedName("customers")
    @Expose
    private List<LoginCustomer_Data> customers = null;

    public List<LoginCustomer_Data> getCustomers() {
        return customers;
    }

    public void setCustomers(List<LoginCustomer_Data> customers) {
        this.customers = customers;
    }
}
