package com.varshaaweblabs.ecommerce.OrderDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 28/10/17.
 */

public class OrderItemRow extends GetOrder_Resp implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_attribute_id")
    @Expose
    private String productAttributeId;
    @SerializedName("product_quantity")
    @Expose
    private String productQuantity;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_reference")
    @Expose
    private String productReference;
    @SerializedName("product_ean13")
    @Expose
    private String productEan13;
    @SerializedName("product_isbn")
    @Expose
    private String productIsbn;
    @SerializedName("product_upc")
    @Expose
    private String productUpc;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("unit_price_tax_incl")
    @Expose
    private String unitPriceTaxIncl;
    @SerializedName("unit_price_tax_excl")
    @Expose
    private String unitPriceTaxExcl;

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    @SerializedName("default_image")
    @Expose

    private String defaultImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttributeId(String productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public String getProductEan13() {
        return productEan13;
    }

    public void setProductEan13(String productEan13) {
        this.productEan13 = productEan13;
    }

    public String getProductIsbn() {
        return productIsbn;
    }

    public void setProductIsbn(String productIsbn) {
        this.productIsbn = productIsbn;
    }

    public String getProductUpc() {
        return productUpc;
    }

    public void setProductUpc(String productUpc) {
        this.productUpc = productUpc;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getUnitPriceTaxIncl() {
        return unitPriceTaxIncl;
    }

    public void setUnitPriceTaxIncl(String unitPriceTaxIncl) {
        this.unitPriceTaxIncl = unitPriceTaxIncl;
    }

    public String getUnitPriceTaxExcl() {
        return unitPriceTaxExcl;
    }

    public void setUnitPriceTaxExcl(String unitPriceTaxExcl) {
        this.unitPriceTaxExcl = unitPriceTaxExcl;
    }
}
