package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 23/9/17.
 */

public class Attribute_Group implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("html_color_code")
    @Expose
    private String htmlColorCode;
    @SerializedName("texture")
    @Expose
    private String texture;
    @SerializedName("selected")
    @Expose
    private Boolean selected;

    @SerializedName("is_visible")
    @Expose
    private Boolean is_visible;
    @SerializedName("id_attributes")
    @Expose
    private Integer idAttributes;
    @SerializedName("attributes_quantity")
    @Expose
    private Integer attributesQuantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlColorCode() {
        return htmlColorCode;
    }

    public void setHtmlColorCode(String htmlColorCode) {
        this.htmlColorCode = htmlColorCode;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getIdAttributes() {
        return idAttributes;
    }

    public void setIdAttributes(Integer idAttributes) {
        this.idAttributes = idAttributes;
    }

    public Integer getAttributesQuantity() {
        return attributesQuantity;
    }

    public void setAttributesQuantity(Integer attributesQuantity) {
        this.attributesQuantity = attributesQuantity;
    }

    public Boolean getIs_visible() {
        return is_visible;
    }

    public void setIs_visible(Boolean is_visible) {
        this.is_visible = is_visible;
    }
}
