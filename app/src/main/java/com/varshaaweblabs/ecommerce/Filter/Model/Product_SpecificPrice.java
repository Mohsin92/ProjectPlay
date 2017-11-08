package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 8/9/17.
 */

public class Product_SpecificPrice implements Serializable {

    @SerializedName("id_specific_price")
    @Expose
    private String idSpecificPrice;
    @SerializedName("id_specific_price_rule")
    @Expose
    private String idSpecificPriceRule;
    @SerializedName("id_cart")
    @Expose
    private String idCart;
    @SerializedName("id_product")
    @Expose
    private String idProduct;
    @SerializedName("id_shop")
    @Expose
    private String idShop;
    @SerializedName("id_shop_group")
    @Expose
    private String idShopGroup;
    @SerializedName("id_currency")
    @Expose
    private String idCurrency;
    @SerializedName("id_country")
    @Expose
    private String idCountry;
    @SerializedName("id_group")
    @Expose
    private String idGroup;
    @SerializedName("id_customer")
    @Expose
    private String idCustomer;
    @SerializedName("id_product_attribute")
    @Expose
    private String idProductAttribute;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("from_quantity")
    @Expose
    private String fromQuantity;
    @SerializedName("reduction")
    @Expose
    private String reduction;
    @SerializedName("reduction_tax")
    @Expose
    private String reductionTax;
    @SerializedName("reduction_type")
    @Expose
    private String reductionType;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("score")
    @Expose
    private String score;

    public String getIdSpecificPrice() {
        return idSpecificPrice;
    }

    public void setIdSpecificPrice(String idSpecificPrice) {
        this.idSpecificPrice = idSpecificPrice;
    }

    public String getIdSpecificPriceRule() {
        return idSpecificPriceRule;
    }

    public void setIdSpecificPriceRule(String idSpecificPriceRule) {
        this.idSpecificPriceRule = idSpecificPriceRule;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }

    public String getIdShopGroup() {
        return idShopGroup;
    }

    public void setIdShopGroup(String idShopGroup) {
        this.idShopGroup = idShopGroup;
    }

    public String getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(String idCurrency) {
        this.idCurrency = idCurrency;
    }

    public String getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdProductAttribute() {
        return idProductAttribute;
    }

    public void setIdProductAttribute(String idProductAttribute) {
        this.idProductAttribute = idProductAttribute;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFromQuantity() {
        return fromQuantity;
    }

    public void setFromQuantity(String fromQuantity) {
        this.fromQuantity = fromQuantity;
    }

    public String getReduction() {
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }

    public String getReductionTax() {
        return reductionTax;
    }

    public void setReductionTax(String reductionTax) {
        this.reductionTax = reductionTax;
    }

    public String getReductionType() {
        return reductionType;
    }

    public void setReductionType(String reductionType) {
        this.reductionType = reductionType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
