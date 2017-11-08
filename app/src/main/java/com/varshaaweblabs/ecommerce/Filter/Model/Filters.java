package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinesh on 8/9/17.
 */

public class Filters implements Serializable {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("displayed")
    @Expose
    private Boolean displayed;
    @SerializedName("properties")
    @Expose
    private Properties properties;
    @SerializedName("magnitude")
    @Expose
    private Integer magnitude;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("nextEncodedFacets")
    @Expose
    private String nextEncodedFacets;
    @SerializedName("facetLabel")
    @Expose
    private String facetLabel;
    @SerializedName("nextEncodedFacetsURL")
    @Expose
    private String nextEncodedFacetsURL;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDisplayed() {
        return displayed;
    }

    public void setDisplayed(Boolean displayed) {
        this.displayed = displayed;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Integer getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Integer magnitude) {
        this.magnitude = magnitude;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNextEncodedFacets() {
        return nextEncodedFacets;
    }

    public void setNextEncodedFacets(String nextEncodedFacets) {
        this.nextEncodedFacets = nextEncodedFacets;
    }

    public String getFacetLabel() {
        return facetLabel;
    }

    public void setFacetLabel(String facetLabel) {
        this.facetLabel = facetLabel;
    }

    public String getNextEncodedFacetsURL() {
        return nextEncodedFacetsURL;
    }

    public void setNextEncodedFacetsURL(String nextEncodedFacetsURL) {
        this.nextEncodedFacetsURL = nextEncodedFacetsURL;
    }


}
