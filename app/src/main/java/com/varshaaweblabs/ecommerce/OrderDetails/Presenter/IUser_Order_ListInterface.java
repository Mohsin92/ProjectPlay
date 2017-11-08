package com.varshaaweblabs.ecommerce.OrderDetails.Presenter;

import com.varshaaweblabs.ecommerce.OrderDetails.Model.GetOrder_Resp;

/**
 * Created by dinesh on 28/10/17.
 */

public interface IUser_Order_ListInterface {
    void getOrderList(GetOrder_Resp order_list_resp);
}
