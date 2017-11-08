package com.varshaaweblabs.ecommerce.Payment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 28/10/17.
 */

public class PaymentModule_Data implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("payment")
    @Expose
    private String payment;
    @SerializedName("module")
    @Expose
    private String module;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
