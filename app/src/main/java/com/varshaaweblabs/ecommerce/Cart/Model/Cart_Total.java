package com.varshaaweblabs.ecommerce.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohsin on 25/9/17.
 */

public class Cart_Total {
    @SerializedName("total")
    @Expose
    private Cart_Totals total;
    @SerializedName("total_including_tax")
    @Expose
    private Cart_TotalIncluding_Tax totalIncludingTax;
    @SerializedName("total_excluding_tax")
    @Expose
    private Cart_TotalExcluding_Tax totalExcludingTax;

    public Cart_Totals getTotal() {
        return total;
    }

    public void setTotal(Cart_Totals total) {
        this.total = total;
    }

    public Cart_TotalIncluding_Tax getTotalIncludingTax() {
        return totalIncludingTax;
    }

    public void setTotalIncludingTax(Cart_TotalIncluding_Tax totalIncludingTax) {
        this.totalIncludingTax = totalIncludingTax;
    }

    public Cart_TotalExcluding_Tax getTotalExcludingTax() {
        return totalExcludingTax;
    }

    public void setTotalExcludingTax(Cart_TotalExcluding_Tax totalExcludingTax) {
        this.totalExcludingTax = totalExcludingTax;
    }
}
