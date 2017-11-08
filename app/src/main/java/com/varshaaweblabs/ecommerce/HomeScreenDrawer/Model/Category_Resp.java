package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 28/8/17.
 */

public class Category_Resp implements Serializable {

    @SerializedName("subcategories")
    @Expose
    private List<Category_Data> categories = null;



    public List<Category_Data> getCategories() {
        return categories;

    }

    public void setCategories(List<Category_Data> categories) {
        this.categories = categories;
    }
}
