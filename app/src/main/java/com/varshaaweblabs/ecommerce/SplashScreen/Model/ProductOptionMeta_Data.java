package com.varshaaweblabs.ecommerce.SplashScreen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 28/8/17.
 */

public class ProductOptionMeta_Data implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_color_group")
    @Expose
    private String isColorGroup;
    @SerializedName("group_type")
    @Expose
    private String groupType;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("public_name")
    @Expose
    private String publicName;
    @SerializedName("associations")
    @Expose
    private ProductAssociations_MetaResp associations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsColorGroup() {
        return isColorGroup;
    }

    public void setIsColorGroup(String isColorGroup) {
        this.isColorGroup = isColorGroup;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public ProductAssociations_MetaResp getAssociations() {
        return associations;
    }

    public void setAssociations(ProductAssociations_MetaResp associations) {
        this.associations = associations;
    }
}
