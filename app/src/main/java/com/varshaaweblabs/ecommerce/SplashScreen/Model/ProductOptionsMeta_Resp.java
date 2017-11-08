package com.varshaaweblabs.ecommerce.SplashScreen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 28/8/17.
 */

public class ProductOptionsMeta_Resp implements Serializable {
    @SerializedName("product_options")
    @Expose
    private List<ProductOptionMeta_Data> productOptions = null;

    public void setProductOptions(List<ProductOptionMeta_Data> productOptions) {
        this.productOptions = productOptions;
    }

    public List<ProductOptionMeta_Data> getProductOptions() {
        return productOptions;



    }
}
