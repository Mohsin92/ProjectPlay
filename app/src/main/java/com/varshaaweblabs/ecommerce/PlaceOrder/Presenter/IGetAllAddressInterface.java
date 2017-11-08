package com.varshaaweblabs.ecommerce.PlaceOrder.Presenter;

import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_List;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;

/**
 * Created by dinesh on 14/10/17.
 */

public interface IGetAllAddressInterface {
    void getAllAddress(User_Address_model address_list);
    void deleteAddress(User_Address_model del_add_list);
}
