package com.varshaaweblabs.ecommerce.LoginActivity.Presenter;

import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginAssociations_Resp;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginCustomer_Data;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginCustomer_Resp;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by dinesh on 24/8/17.
 */

public interface ILoginInterface {
    void Login(LoginCustomer_Data response);
    void getAllAddress(User_Address_model address_list);
//    void getCart(Cart_Response cart_response);
}
