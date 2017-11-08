package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 2/10/17.
 */

public class Product_custmization implements Serializable {

    @SerializedName("fields")
    @Expose
    private List<Customization_Field> fields = null;

    public List<Customization_Field> getFields() {
        return fields;
    }

    public void setFields(List<Customization_Field> fields) {
        this.fields = fields;
    }
}
