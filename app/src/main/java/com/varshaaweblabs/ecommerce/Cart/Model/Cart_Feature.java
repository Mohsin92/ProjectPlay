package com.varshaaweblabs.ecommerce.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mohsin on 27/9/17.
 */

public class Cart_Feature implements Serializable {

    @SerializedName("id_feature")
    @Expose
    private String idFeature;
    @SerializedName("id_product")
    @Expose
    private String idProduct;
    @SerializedName("id_feature_value")
    @Expose
    private String idFeatureValue;

    public String getIdFeature() {
        return idFeature;
    }

    public void setIdFeature(String idFeature) {
        this.idFeature = idFeature;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdFeatureValue() {
        return idFeatureValue;
    }

    public void setIdFeatureValue(String idFeatureValue) {
        this.idFeatureValue = idFeatureValue;
    }
}
