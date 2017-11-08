package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 7/9/17.
 */

public class Main_Filters implements Serializable {

    @SerializedName("sub_filter")
    @Expose
    private List<Sub_Filter> subFilter = null;
    @SerializedName("active_filter")
    @Expose
    private List<Active_Filter> activeFilter = null;

    public List<Sub_Filter> getSub_filter() {
        return subFilter;
    }

    public void setSub_filter(List<Sub_Filter> sub_filter) {
        this.subFilter = sub_filter;
    }

    public List<Active_Filter> getActiveFilter() {
        return activeFilter;
    }

    public void setActiveFilter(List<Active_Filter> activeFilter) {
        this.activeFilter = activeFilter;
    }
}
