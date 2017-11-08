package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 23/9/17.
 */

public class ProductFull implements Serializable {
    @SerializedName("pictures")
    @Expose
    private List<Object> pictures = null;
    @SerializedName("textFields")
    @Expose
    private List<Object> textFields = null;
    @SerializedName("packItems")
    @Expose
    private List<Object> packItems = null;
    @SerializedName("noPackPrice")
    @Expose
    private String noPackPrice;
    @SerializedName("displayPackPrice")
    @Expose
    private Boolean displayPackPrice;
    @SerializedName("packs")
    @Expose
    private List<Object> packs = null;
    @SerializedName("priceDisplay")
    @Expose
    private Integer priceDisplay;
    @SerializedName("productPriceWithoutReduction")
    @Expose
    private Double productPriceWithoutReduction;
    @SerializedName("customizationFields")
    @Expose
    private Boolean customizationFields;
    @SerializedName("id_customization")
    @Expose
    private Object idCustomization;
    @SerializedName("accessories")
    @Expose
    private List<Product_Recommend> accessories = null;
    @SerializedName("product")
    @Expose
    private ProductFull_Product product;
    @SerializedName("displayUnitPrice")
    @Expose
    private Boolean displayUnitPrice;
    @SerializedName("product_manufacturer")
    @Expose
    private ProductManufacturer productManufacturer;
    @SerializedName("manufacturer_image_url")
    @Expose
    private String manufacturerImageUrl;
    @SerializedName("product_brand_url")
    @Expose
    private String productBrandUrl;
    @SerializedName("groups")
    @Expose
    private List<Group> groups = null;
    @SerializedName("colors")
    @Expose
    private List<Color> colors = null;
    @SerializedName("combinations")
    @Expose
    private List<Combination> combinations = null;
    @SerializedName("combinationImages")
    @Expose
    private List<List<CombinationImage>> combinationImages = null;


    public List<Object> getPictures() {
        return pictures;
    }

    public void setPictures(List<Object> pictures) {
        this.pictures = pictures;
    }

    public List<Object> getTextFields() {
        return textFields;
    }

    public void setTextFields(List<Object> textFields) {
        this.textFields = textFields;
    }

    public List<Object> getPackItems() {
        return packItems;
    }

    public void setPackItems(List<Object> packItems) {
        this.packItems = packItems;
    }

    public String getNoPackPrice() {
        return noPackPrice;
    }

    public void setNoPackPrice(String noPackPrice) {
        this.noPackPrice = noPackPrice;
    }

    public Boolean getDisplayPackPrice() {
        return displayPackPrice;
    }

    public void setDisplayPackPrice(Boolean displayPackPrice) {
        this.displayPackPrice = displayPackPrice;
    }

    public List<Object> getPacks() {
        return packs;
    }

    public void setPacks(List<Object> packs) {
        this.packs = packs;
    }

    public Integer getPriceDisplay() {
        return priceDisplay;
    }

    public void setPriceDisplay(Integer priceDisplay) {
        this.priceDisplay = priceDisplay;
    }

    public Double getProductPriceWithoutReduction() {
        return productPriceWithoutReduction;
    }

    public void setProductPriceWithoutReduction(Double productPriceWithoutReduction) {
        this.productPriceWithoutReduction = productPriceWithoutReduction;
    }

    public Boolean getCustomizationFields() {
        return customizationFields;
    }

    public void setCustomizationFields(Boolean customizationFields) {
        this.customizationFields = customizationFields;
    }

    public Object getIdCustomization() {
        return idCustomization;
    }

    public void setIdCustomization(Object idCustomization) {
        this.idCustomization = idCustomization;
    }

    public List<Product_Recommend> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Product_Recommend> accessories) {
        this.accessories = accessories;
    }

    public ProductFull_Product getProduct() {
        return product;
    }

    public void setProduct(ProductFull_Product product) {
        this.product = product;
    }

    public Boolean getDisplayUnitPrice() {
        return displayUnitPrice;
    }

    public void setDisplayUnitPrice(Boolean displayUnitPrice) {
        this.displayUnitPrice = displayUnitPrice;
    }

    public ProductManufacturer getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(ProductManufacturer productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public String getManufacturerImageUrl() {
        return manufacturerImageUrl;
    }

    public void setManufacturerImageUrl(String manufacturerImageUrl) {
        this.manufacturerImageUrl = manufacturerImageUrl;
    }

    public String getProductBrandUrl() {
        return productBrandUrl;
    }

    public void setProductBrandUrl(String productBrandUrl) {
        this.productBrandUrl = productBrandUrl;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public List<Combination> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<Combination> combinations) {
        this.combinations = combinations;
    }

    public List<List<CombinationImage>> getCombinationImages() {
        return combinationImages;
    }

    public void setCombinationImages(List<List<CombinationImage>> combinationImages) {
        this.combinationImages = combinationImages;
    }
}
