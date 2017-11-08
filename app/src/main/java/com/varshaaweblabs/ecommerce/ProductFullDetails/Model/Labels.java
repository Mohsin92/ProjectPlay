package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 23/9/17.
 */

public class Labels implements Serializable {

    @SerializedName("tax_short")
    @Expose
    private String taxShort;
    @SerializedName("tax_long")
    @Expose
    private String taxLong;

    public String getTaxShort() {
        return taxShort;
    }

    public void setTaxShort(String taxShort) {
        this.taxShort = taxShort;
    }

    public String getTaxLong() {
        return taxLong;
    }

    public void setTaxLong(String taxLong) {
        this.taxLong = taxLong;
    }
}
