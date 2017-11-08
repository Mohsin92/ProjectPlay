package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 8/9/17.
 */

public class Product_attributes implements Serializable {

    @SerializedName("Size")
    @Expose
    private Attribute_Size attribute_size;
    @SerializedName("Shoe_Size")
    @Expose
    private Attribute_ShoeSize attribute_shoeSize;
    @SerializedName("Color")
    @Expose
    private Attribute_Color attribute_color ;

    public Attribute_Size getAttribute_size() {
        return attribute_size;
    }

    public void setAttribute_size(Attribute_Size attribute_size) {
        this.attribute_size = attribute_size;
    }

    public Attribute_ShoeSize getAttribute_shoeSize() {
        return attribute_shoeSize;
    }

    public void setAttribute_shoeSize(Attribute_ShoeSize attribute_shoeSize) {
        this.attribute_shoeSize = attribute_shoeSize;
    }

    public Attribute_Color getAttribute_color() {
        return attribute_color;
    }

    public void setAttribute_color(Attribute_Color attribute_color) {
        this.attribute_color = attribute_color;
    }
}
