package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 14/9/17.
 */

public class Active_Filter implements Serializable {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("filters")
    @Expose
    private List<Active_Filter_List> list_active_filters = null;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Active_Filter_List> getList_active_filters() {
        return list_active_filters;
    }

    public void setList_active_filters(List<Active_Filter_List> list_active_filters) {
        this.list_active_filters = list_active_filters;
    }
}
