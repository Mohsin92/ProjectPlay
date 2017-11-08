package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 7/9/17.
 */

public class Properties implements Serializable {

    @SerializedName("min")
    @Expose
    private Object min;
    @SerializedName("max")
    @Expose
    private String max;
    @SerializedName("range")
    @Expose
    private Boolean range;
    @SerializedName("color")
    @Expose
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @SerializedName("id_attribute_group")

    @Expose
    private Boolean id_attribute_group;

    public Boolean getId_attribute_group() {
        return id_attribute_group;
    }

    public void setId_attribute_group(Boolean id_attribute_group) {
        this.id_attribute_group = id_attribute_group;
    }

    public Object getMin() {
        return min;
    }

    public void setMin(Object min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public Boolean getRange() {
        return range;
    }

    public void setRange(Boolean range) {
        this.range = range;
    }
}
