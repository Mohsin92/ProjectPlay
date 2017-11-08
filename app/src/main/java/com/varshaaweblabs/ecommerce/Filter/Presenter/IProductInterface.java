package com.varshaaweblabs.ecommerce.Filter.Presenter;

import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;
import com.varshaaweblabs.ecommerce.Filter.Model.Filter_Resp;

/**
 * Created by dinesh on 11/9/17.
 */

public interface IProductInterface {
    void Filter_selected_data(Filter_Resp filter_resp) ;
    void getCart(Cart_Response cart_response);

}
