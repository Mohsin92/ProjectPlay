package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 8/9/17.
 */

public class Product_Cover implements Serializable {
    @SerializedName("bySize")
    @Expose
    private Image_BySize bySize;
    @SerializedName("small")
    @Expose
    private Image_Small small;
    @SerializedName("medium")
    @Expose
    private Image_Medium medium;
    @SerializedName("large")
    @Expose
    private Image_Large large;
    @SerializedName("legend")
    @Expose
    private String legend;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("id_image")
    @Expose
    private String idImage;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("associatedVariants")
    @Expose
    private List<String> associatedVariants = null;


    public Image_BySize getBySize() {
        return bySize;
    }

    public void setBySize(Image_BySize bySize) {
        this.bySize = bySize;
    }

    public Image_Small getSmall() {
        return small;
    }

    public void setSmall(Image_Small small) {
        this.small = small;
    }

    public Image_Medium getMedium() {
        return medium;
    }

    public void setMedium(Image_Medium medium) {
        this.medium = medium;
    }

    public Image_Large getLarge() {
        return large;
    }

    public void setLarge(Image_Large large) {
        this.large = large;
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<String> getAssociatedVariants() {
        return associatedVariants;
    }

    public void setAssociatedVariants(List<String> associatedVariants) {
        this.associatedVariants = associatedVariants;
    }
}
