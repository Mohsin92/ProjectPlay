package com.varshaaweblabs.ecommerce.PlaceOrder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 14/10/17.
 */

public class Country_List_resp implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_zone")
    @Expose
    private String idZone;
    @SerializedName("id_currency")
    @Expose
    private String idCurrency;
    @SerializedName("call_prefix")
    @Expose
    private String callPrefix;
    @SerializedName("iso_code")
    @Expose
    private String isoCode;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("contains_states")
    @Expose
    private String containsStates;
    @SerializedName("need_identification_number")
    @Expose
    private String needIdentificationNumber;
    @SerializedName("need_zip_code")
    @Expose
    private String needZipCode;
    @SerializedName("zip_code_format")
    @Expose
    private String zipCodeFormat;
    @SerializedName("display_tax_label")
    @Expose
    private String displayTaxLabel;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdZone() {
        return idZone;
    }

    public void setIdZone(String idZone) {
        this.idZone = idZone;
    }

    public String getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(String idCurrency) {
        this.idCurrency = idCurrency;
    }

    public String getCallPrefix() {
        return callPrefix;
    }

    public void setCallPrefix(String callPrefix) {
        this.callPrefix = callPrefix;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getContainsStates() {
        return containsStates;
    }

    public void setContainsStates(String containsStates) {
        this.containsStates = containsStates;
    }

    public String getNeedIdentificationNumber() {
        return needIdentificationNumber;
    }

    public void setNeedIdentificationNumber(String needIdentificationNumber) {
        this.needIdentificationNumber = needIdentificationNumber;
    }

    public String getNeedZipCode() {
        return needZipCode;
    }

    public void setNeedZipCode(String needZipCode) {
        this.needZipCode = needZipCode;
    }

    public String getZipCodeFormat() {
        return zipCodeFormat;
    }

    public void setZipCodeFormat(String zipCodeFormat) {
        this.zipCodeFormat = zipCodeFormat;
    }

    public String getDisplayTaxLabel() {
        return displayTaxLabel;
    }

    public void setDisplayTaxLabel(String displayTaxLabel) {
        this.displayTaxLabel = displayTaxLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
