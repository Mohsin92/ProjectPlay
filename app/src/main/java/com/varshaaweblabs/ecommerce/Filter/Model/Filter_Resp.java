package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 7/9/17.
 */

public class Filter_Resp implements Serializable {


    @SerializedName("product")
    @Expose
    private List<Product> product = null;
    @SerializedName("main_filters")
    @Expose
    private Main_Filters main_filters;
    @SerializedName("sortby")
    @Expose
    private List<Product_Sortby> sortby = null;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Main_Filters getMain_filters() {
        return main_filters;
    }

    public void setMain_filters(Main_Filters main_filters) {
        this.main_filters = main_filters;
    }

    public List<Product_Sortby> getSortby() {
        return sortby;
    }

    public void setSortby(List<Product_Sortby> sortby) {
        this.sortby = sortby;
    }
}
