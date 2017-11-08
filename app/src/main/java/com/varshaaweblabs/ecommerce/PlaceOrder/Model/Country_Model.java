package com.varshaaweblabs.ecommerce.PlaceOrder.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 14/10/17.
 */

public class Country_Model implements Serializable {

    @SerializedName("countries")
    @Expose
    private List<Country_List_resp> countries = null;

    public List<Country_List_resp> getCountries() {
        return countries;
    }

    public void setCountries(List<Country_List_resp> countries) {
        this.countries = countries;
    }
}
