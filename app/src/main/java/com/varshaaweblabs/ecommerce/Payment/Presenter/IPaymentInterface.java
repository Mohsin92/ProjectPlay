package com.varshaaweblabs.ecommerce.Payment.Presenter;

import com.varshaaweblabs.ecommerce.Payment.Model.OrderCheckOut_Resp;
import com.varshaaweblabs.ecommerce.Payment.Model.PaymentModule__Resp;

/**
 * Created by dinesh on 13/10/17.
 */

public interface IPaymentInterface {

    void PaymentOption(PaymentModule__Resp payment_option);
    void checkOut(OrderCheckOut_Resp checkout);
    void Paypal_Tranasaction();
}
