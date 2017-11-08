package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.*;
import java.util.Properties;

/**
 * Created by dinesh on 7/9/17.
 */

public class Sub_Filter implements Serializable {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("displayed")
    @Expose
    private Boolean displayed;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("properties")
    @Expose
    private Properties properties = null;
    @SerializedName("filters")
    @Expose
    private List<Filters> filters = null;
    @SerializedName("multipleSelectionAllowed")
    @Expose
    private Boolean multipleSelectionAllowed;
    @SerializedName("widgetType")
    @Expose
    private String widgetType;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getDisplayed() {
        return displayed;
    }

    public void setDisplayed(Boolean displayed) {
        this.displayed = displayed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<Filters> getFilters() {
        return filters;
    }

    public void setFilters(List<Filters> filters) {
        this.filters = filters;
    }




    public Boolean getMultipleSelectionAllowed() {
        return multipleSelectionAllowed;
    }

    public void setMultipleSelectionAllowed(Boolean multipleSelectionAllowed) {
        this.multipleSelectionAllowed = multipleSelectionAllowed;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }
}
