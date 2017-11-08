package com.varshaaweblabs.ecommerce.PlaceOrder.Presenter;

import com.varshaaweblabs.ecommerce.PlaceOrder.Model.Country_Model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.State_Model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;

/**
 * Created by dinesh on 14/10/17.
 */

public interface IAdd_Edit_AddressInterface {

    void getCountry(Country_Model country_resp);
    void getState(State_Model state_resp);
    void addAddress(User_Address_model add_address_list);
    void editAddress(User_Address_model edit_address_list);
}
