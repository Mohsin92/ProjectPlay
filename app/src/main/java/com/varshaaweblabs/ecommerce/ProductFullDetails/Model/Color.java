package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 23/9/17.
 */

public class Color implements Serializable {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("attributes_quantity")
    @Expose
    private Integer attributesQuantity;
    @SerializedName("id_attributes")
    @Expose
    private Integer idAttributes;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttributesQuantity() {
        return attributesQuantity;
    }

    public void setAttributesQuantity(Integer attributesQuantity) {
        this.attributesQuantity = attributesQuantity;
    }

    public Integer getIdAttributes() {
        return idAttributes;
    }

    public void setIdAttributes(Integer idAttributes) {
        this.idAttributes = idAttributes;
    }
}
