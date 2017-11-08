package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Presenter;

import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_Resp;

/**
 * Created by dinesh on 28/8/17.
 */

public interface IHomeInterface {
    void getMenu(Category_Resp response);
    void getCart(Cart_Response cart_response);
}
