package com.varshaaweblabs.ecommerce.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohsin on 25/9/17.
 */

public class Cart_Response {
    @SerializedName("id_cart")
    @Expose
    private String idCart;
    @SerializedName("cart")
    @Expose
    private Cart cart;

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
