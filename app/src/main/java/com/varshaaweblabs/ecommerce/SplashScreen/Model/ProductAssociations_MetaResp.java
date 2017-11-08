package com.varshaaweblabs.ecommerce.SplashScreen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 28/8/17.
 */

public class ProductAssociations_MetaResp implements Serializable {

    @SerializedName("product_option_values")
    @Expose
    private List<ProductOptionValue_MetaData> productOptionValues = null;

    public List<ProductOptionValue_MetaData> getProductOptionValues() {
        return productOptionValues;
    }

    public void setProductOptionValues(List<ProductOptionValue_MetaData> productOptionValues) {
        this.productOptionValues = productOptionValues;
    }
}
