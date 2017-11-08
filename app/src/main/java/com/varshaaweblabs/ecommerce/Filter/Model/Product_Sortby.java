package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 8/9/17.
 */

public class Product_Sortby implements Serializable {

    @SerializedName("entity")
    @Expose
    private String entity;
    @SerializedName("field")
    @Expose
    private String field;
    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("urlParameter")
    @Expose
    private String urlParameter;
    @SerializedName("current")
    @Expose
    private Boolean current;
    @SerializedName("url")
    @Expose
    private String url;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrlParameter() {
        return urlParameter;
    }

    public void setUrlParameter(String urlParameter) {
        this.urlParameter = urlParameter;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
