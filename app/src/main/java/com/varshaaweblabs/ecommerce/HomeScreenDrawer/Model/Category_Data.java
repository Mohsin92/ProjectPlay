package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 28/8/17.
 */

public class Category_Data implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_parent")
    @Expose
    private String idParent;
    @SerializedName("level_depth")
    @Expose
    private String levelDepth;
    @SerializedName("slider_image")
    @Expose
    private String sliderImage;

    public String getSliderImage() {
        return sliderImage;
    }

    public void setSliderImage(String sliderImage) {
        this.sliderImage = sliderImage;
    }

    @SerializedName("nb_products_recursive")
    @Expose
    private String nbProductsRecursive;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("id_shop_default")
    @Expose
    private String idShopDefault;
    @SerializedName("is_root_category")
    @Expose
    private String isRootCategory;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("date_add")
    @Expose
    private String dateAdd;
    @SerializedName("date_upd")
    @Expose
    private String dateUpd;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("link_rewrite")
    @Expose
    private String linkRewrite;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("meta_description")
    @Expose
    private String metaDescription;
    @SerializedName("meta_keywords")
    @Expose
    private String metaKeywords;
    @SerializedName("associations")
    @Expose
    private Category_Associations associations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public String getLevelDepth() {
        return levelDepth;
    }

    public void setLevelDepth(String levelDepth) {
        this.levelDepth = levelDepth;
    }

    public String getNbProductsRecursive() {
        return nbProductsRecursive;
    }

    public void setNbProductsRecursive(String nbProductsRecursive) {
        this.nbProductsRecursive = nbProductsRecursive;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIdShopDefault() {
        return idShopDefault;
    }

    public void setIdShopDefault(String idShopDefault) {
        this.idShopDefault = idShopDefault;
    }

    public String getIsRootCategory() {
        return isRootCategory;
    }

    public void setIsRootCategory(String isRootCategory) {
        this.isRootCategory = isRootCategory;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(String dateUpd) {
        this.dateUpd = dateUpd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkRewrite() {
        return linkRewrite;
    }

    public void setLinkRewrite(String linkRewrite) {
        this.linkRewrite = linkRewrite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public Category_Associations getAssociations() {
        return associations;
    }

    public void setAssociations(Category_Associations associations) {
        this.associations = associations;
    }
}
