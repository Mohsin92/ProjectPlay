package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 23/9/17.
 */

public class Attribute implements Serializable {
    @SerializedName("id_attribute")
    @Expose
    private String idAttribute;
    @SerializedName("id_attribute_group")
    @Expose
    private String idAttributeGroup;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("ean13")
    @Expose
    private String ean13;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("upc")
    @Expose
    private String upc;

    public String getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(String idAttribute) {
        this.idAttribute = idAttribute;
    }

    public String getIdAttributeGroup() {
        return idAttributeGroup;
    }

    public void setIdAttributeGroup(String idAttributeGroup) {
        this.idAttributeGroup = idAttributeGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }
}
