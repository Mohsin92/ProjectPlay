package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 23/9/17.
 */

public class Group implements Serializable {
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("group_type")
    @Expose
    private String groupType;
    @SerializedName("default")
    @Expose
    private String _default;
    @SerializedName("attributes")
    @Expose
    private List<Attribute_Group> attributes = null;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String get_default() {
        return _default;
    }

    public void set_default(String _default) {
        this._default = _default;
    }

    public List<Attribute_Group> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute_Group> attributes) {
        this.attributes = attributes;
    }
}
