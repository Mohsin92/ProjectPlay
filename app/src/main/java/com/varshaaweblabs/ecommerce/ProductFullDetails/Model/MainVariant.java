package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 23/9/17.
 */

public class MainVariant implements Serializable {

    @SerializedName("id_product_attribute")
    @Expose
    private Integer idProductAttribute;
    @SerializedName("texture")
    @Expose
    private String texture;
    @SerializedName("id_product")
    @Expose
    private String idProduct;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("add_to_cart_url")
    @Expose
    private String addToCartUrl;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("html_color_code")
    @Expose
    private String htmlColorCode;

    public Integer getIdProductAttribute() {
        return idProductAttribute;
    }

    public void setIdProductAttribute(Integer idProductAttribute) {
        this.idProductAttribute = idProductAttribute;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddToCartUrl() {
        return addToCartUrl;
    }

    public void setAddToCartUrl(String addToCartUrl) {
        this.addToCartUrl = addToCartUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHtmlColorCode() {
        return htmlColorCode;
    }

    public void setHtmlColorCode(String htmlColorCode) {
        this.htmlColorCode = htmlColorCode;
    }
}
