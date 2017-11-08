package com.varshaaweblabs.ecommerce.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mohsin on 25/9/17.
 */

public class Cart_Vouchers {
    @SerializedName("allowed")
    @Expose
    private Integer allowed;
    @SerializedName("added")
    @Expose
    private List<Cart_Coupon_Added> added = null;

    public Integer getAllowed() {
        return allowed;
    }

    public void setAllowed(Integer allowed) {
        this.allowed = allowed;
    }

    public List<Cart_Coupon_Added> getAdded() {
        return added;
    }

    public void setAdded(List<Cart_Coupon_Added> added) {
        this.added = added;
    }
}
