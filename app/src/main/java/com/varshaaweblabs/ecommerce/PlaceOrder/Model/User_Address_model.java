package com.varshaaweblabs.ecommerce.PlaceOrder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 13/10/17.
 */

public class User_Address_model implements Serializable {

    @SerializedName("addresses")
    @Expose
    private List<User_Address_List> addresses = null;

    public List<User_Address_List> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<User_Address_List> addresses) {
        this.addresses = addresses;
    }
}
