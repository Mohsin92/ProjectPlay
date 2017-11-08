package com.varshaaweblabs.ecommerce.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 12/10/17.
 */

public class Cart_Coupon_Added implements Serializable {
    @SerializedName("id_cart_rule")
    @Expose
    private String idCartRule;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("reduction_percent")
    @Expose
    private String reductionPercent;
    @SerializedName("reduction_currency")
    @Expose
    private String reductionCurrency;
    @SerializedName("reduction_amount")
    @Expose
    private String reductionAmount;
    @SerializedName("reduction_formatted")
    @Expose
    private String reductionFormatted;
    @SerializedName("delete_url")
    @Expose
    private String deleteUrl;

    public String getIdCartRule() {
        return idCartRule;
    }

    public void setIdCartRule(String idCartRule) {
        this.idCartRule = idCartRule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReductionPercent() {
        return reductionPercent;
    }

    public void setReductionPercent(String reductionPercent) {
        this.reductionPercent = reductionPercent;
    }

    public String getReductionCurrency() {
        return reductionCurrency;
    }

    public void setReductionCurrency(String reductionCurrency) {
        this.reductionCurrency = reductionCurrency;
    }

    public String getReductionAmount() {
        return reductionAmount;
    }

    public void setReductionAmount(String reductionAmount) {
        this.reductionAmount = reductionAmount;
    }

    public String getReductionFormatted() {
        return reductionFormatted;
    }

    public void setReductionFormatted(String reductionFormatted) {
        this.reductionFormatted = reductionFormatted;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }
}
