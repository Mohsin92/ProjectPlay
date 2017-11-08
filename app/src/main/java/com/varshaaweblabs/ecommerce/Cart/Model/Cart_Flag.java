package com.varshaaweblabs.ecommerce.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mohsin on 3/10/17.
 */

public class Cart_Flag implements Serializable {
    @SerializedName("discount")
    @Expose
    private Cart_Discount discount;

    public Cart_Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Cart_Discount discount) {
        this.discount = discount;
    }
}
