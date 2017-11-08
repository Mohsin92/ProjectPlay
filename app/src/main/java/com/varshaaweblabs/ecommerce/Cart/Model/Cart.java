package com.varshaaweblabs.ecommerce.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mohsin on 25/9/17.
 */

public class Cart {

    @SerializedName("products")
    @Expose
    private List<Cart_Product> products = null;
    @SerializedName("totals")
    @Expose
    private Cart_Total totals;
    @SerializedName("subtotals")
    @Expose
    private Cart_SubTotal subtotals;
    @SerializedName("products_count")
    @Expose
    private Integer productsCount;
    @SerializedName("summary_string")
    @Expose
    private String summaryString;
    @SerializedName("labels")
    @Expose
    private Cart_Label labels;
    @SerializedName("id_address_delivery")
    @Expose
    private Object idAddressDelivery = null;
    @SerializedName("id_address_invoice")
    @Expose
    private Object idAddressInvoice = null;

    @SerializedName("vouchers")
    @Expose
    private Cart_Vouchers vouchers;
    @SerializedName("discounts")
    @Expose
    private List<Object> discounts = null;
    @SerializedName("minimalPurchase")
    @Expose
    private String minimalPurchase;
    @SerializedName("minimalPurchaseRequired")
    @Expose
    private String minimalPurchaseRequired;

    public List<Cart_Product> getProducts() {
        return products;
    }

    public void setProducts(List<Cart_Product> products) {
        this.products = products;
    }

    public Cart_Total getTotals() {
        return totals;
    }

    public void setTotals(Cart_Total totals) {
        this.totals = totals;
    }

    public Cart_SubTotal getSubtotals() {
        return subtotals;
    }

    public void setSubtotals(Cart_SubTotal subtotals) {
        this.subtotals = subtotals;
    }

    public Integer getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

    public String getSummaryString() {
        return summaryString;
    }

    public void setSummaryString(String summaryString) {
        this.summaryString = summaryString;
    }

    public Cart_Label getLabels() {
        return labels;
    }

    public void setLabels(Cart_Label labels) {
        this.labels = labels;
    }

    public Object getIdAddressDelivery() {
        return idAddressDelivery;
    }

    public void setIdAddressDelivery(Object idAddressDelivery) {
        this.idAddressDelivery = idAddressDelivery;
    }

    public Object getIdAddressInvoice() {
        return idAddressInvoice;
    }

    public void setIdAddressInvoice(Object idAddressInvoice) {
        this.idAddressInvoice = idAddressInvoice;
    }


    public Cart_Vouchers getVouchers() {
        return vouchers;
    }

    public void setVouchers(Cart_Vouchers vouchers) {
        this.vouchers = vouchers;
    }

    public List<Object> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Object> discounts) {
        this.discounts = discounts;
    }

    public String getMinimalPurchase() {
        return minimalPurchase;
    }

    public void setMinimalPurchase(String minimalPurchase) {
        this.minimalPurchase = minimalPurchase;
    }

    public String getMinimalPurchaseRequired() {
        return minimalPurchaseRequired;
    }

    public void setMinimalPurchaseRequired(String minimalPurchaseRequired) {
        this.minimalPurchaseRequired = minimalPurchaseRequired;
    }
}
