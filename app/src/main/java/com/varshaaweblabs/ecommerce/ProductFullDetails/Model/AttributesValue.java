package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 23/9/17.
 */

public class AttributesValue implements Serializable {

    @SerializedName("attributes_values")
    @Expose
    private String attributesValues;
    @SerializedName("id_attributes_values")
    @Expose
    private Integer idAttributesValues;
    @SerializedName("id_attributes")
    @Expose
    private Integer idAttributes;

    public String getAttributesValues() {
        return attributesValues;
    }

    public void setAttributesValues(String attributesValues) {
        this.attributesValues = attributesValues;
    }

    public Integer getIdAttributesValues() {
        return idAttributesValues;
    }

    public void setIdAttributesValues(Integer idAttributesValues) {
        this.idAttributesValues = idAttributesValues;
    }

    public Integer getIdAttributes() {
        return idAttributes;
    }

    public void setIdAttributes(Integer idAttributes) {
        this.idAttributes = idAttributes;
    }
}
