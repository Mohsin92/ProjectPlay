package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 23/9/17.
 */

public class Image_Bysize implements Serializable {


    @SerializedName("large")
    @Expose
    private Img_Large large;

    public Img_Large getLarge() {
        return large;
    }

    public void setLarge(Img_Large large) {
        this.large = large;
    }
}
