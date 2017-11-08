package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 2/10/17.
 */

public class Custmization_Resp implements Serializable {
    @SerializedName("id_customization")
    @Expose
    private String idCustomization;
    @SerializedName("id_cart")
    @Expose
    private String idCart;

    public String getIdCustomization() {
        return idCustomization;
    }

    public void setIdCustomization(String idCustomization) {
        this.idCustomization = idCustomization;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }
}
