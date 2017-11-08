package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 28/8/17.
 */

public class Category_Associations implements Serializable {
    @SerializedName("subcategories")
    @Expose
    private List<Category_id> categories = null;
    @SerializedName("products")
    @Expose
    private List<Product_id> products = null;

    public List<Category_id> getCategories() {
        return categories;
    }

    public void setCategories(List<Category_id> categories) {
        this.categories = categories;
    }

    public List<Product_id> getProducts() {
        return products;
    }

    public void setProducts(List<Product_id> products) {
        this.products = products;
    }
}
