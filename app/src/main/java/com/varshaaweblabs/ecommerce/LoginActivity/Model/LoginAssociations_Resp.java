package com.varshaaweblabs.ecommerce.LoginActivity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 24/8/17.
 */

public class LoginAssociations_Resp implements Serializable {

    @SerializedName("groups")
    @Expose
    private List<LoginAssociation_Data> groups = null;

    public List<LoginAssociation_Data> getGroups() {
        return groups;
    }

    public void setGroups(List<LoginAssociation_Data> groups) {
        this.groups = groups;
    }
}
