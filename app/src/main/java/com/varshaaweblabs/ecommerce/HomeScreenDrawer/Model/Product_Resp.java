package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 6/9/17.
 */

public class Product_Resp implements Serializable {

    @SerializedName("products")
    @Expose
    private List<Product_Data> products = null;

    public List<Product_Data> getProducts() {
        return products;
    }

    public void setProducts(List<Product_Data> products) {
        this.products = products;
    }
}
