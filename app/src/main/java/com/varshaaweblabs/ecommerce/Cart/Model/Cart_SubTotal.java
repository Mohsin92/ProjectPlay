package com.varshaaweblabs.ecommerce.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohsin on 25/9/17.
 */

public class Cart_SubTotal {
    @SerializedName("products")
    @Expose
    private Cart_Product_Value products;
    @SerializedName("discounts")
    @Expose
    private Object discounts = null;
    @SerializedName("shipping")
    @Expose
    private Cart_Shipping_Value shipping;
    @SerializedName("tax")
    @Expose
    private Cart_Tax tax;


    public Cart_Product_Value getProducts() {
        return products;
    }

    public void setProducts(Cart_Product_Value products) {
        this.products = products;
    }

    public Object getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Object discounts) {
        this.discounts = discounts;
    }

    public Cart_Shipping_Value getShipping() {
        return shipping;
    }

    public void setShipping(Cart_Shipping_Value shipping) {
        this.shipping = shipping;
    }

    public Cart_Tax getTax() {
        return tax;
    }

    public void setTax(Cart_Tax tax) {
        this.tax = tax;
    }
}
