package com.varshaaweblabs.ecommerce.Payment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 28/10/17.
 */

public class PaymentModule__Resp implements Serializable {

    @SerializedName("PaymentModule")
    @Expose
    private List<PaymentModule_Data> paymentModule = null;

    public List<PaymentModule_Data> getPaymentModule() {
        return paymentModule;
    }

    public void setPaymentModule(List<PaymentModule_Data> paymentModule) {
        this.paymentModule = paymentModule;
    }
}
