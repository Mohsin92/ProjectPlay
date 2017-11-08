package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 23/9/17.
 */

public class Combination implements Serializable {

    @SerializedName("attributes_values")
    @Expose
    private List<AttributesValue> attributesValues = null;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("specific_price")
    @Expose
    private ProductFull_Product_SpecificPrice specificPrice;
    @SerializedName("ecotax")
    @Expose
    private Integer ecotax;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("unit_impact")
    @Expose
    private String unitImpact;
    @SerializedName("minimal_quantity")
    @Expose
    private String minimalQuantity;
    @SerializedName("date_formatted")
    @Expose
    private String dateFormatted;
    @SerializedName("available_date")
    @Expose
    private String availableDate;
    @SerializedName("id_image")
    @Expose
    private Integer idImage;
    @SerializedName("list")
    @Expose
    private String list;
    @SerializedName("id_product_attribute")
    @Expose
    private Integer idProductAttribute;

    public List<AttributesValue> getAttributesValues() {
        return attributesValues;
    }

    public void setAttributesValues(List<AttributesValue> attributesValues) {
        this.attributesValues = attributesValues;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ProductFull_Product_SpecificPrice getSpecificPrice() {
        return specificPrice;
    }

    public void setSpecificPrice(ProductFull_Product_SpecificPrice specificPrice) {
        this.specificPrice = specificPrice;
    }

    public Integer getEcotax() {
        return ecotax;
    }

    public void setEcotax(Integer ecotax) {
        this.ecotax = ecotax;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUnitImpact() {
        return unitImpact;
    }

    public void setUnitImpact(String unitImpact) {
        this.unitImpact = unitImpact;
    }

    public String getMinimalQuantity() {
        return minimalQuantity;
    }

    public void setMinimalQuantity(String minimalQuantity) {
        this.minimalQuantity = minimalQuantity;
    }

    public String getDateFormatted() {
        return dateFormatted;
    }

    public void setDateFormatted(String dateFormatted) {
        this.dateFormatted = dateFormatted;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public Integer getIdImage() {
        return idImage;
    }

    public void setIdImage(Integer idImage) {
        this.idImage = idImage;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public Integer getIdProductAttribute() {
        return idProductAttribute;
    }

    public void setIdProductAttribute(Integer idProductAttribute) {
        this.idProductAttribute = idProductAttribute;
    }
}
