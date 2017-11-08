package com.varshaaweblabs.ecommerce.Cart.Presenter;

import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Error;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;

/**
 * Created by mohsin on 26/9/17.
 */

public interface ICartInterface {
    void AddToCart(Cart_Response cart_response);
    void removeCart(Cart_Response cart_response);
    void getCart(Cart_Response cart_response);
    void AddCoupon(Cart_Coupon_Error cart_response);
    void DeleteCoupon(Cart_Response cart_response);
}
