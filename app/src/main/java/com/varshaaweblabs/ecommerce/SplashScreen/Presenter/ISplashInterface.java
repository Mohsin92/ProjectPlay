package com.varshaaweblabs.ecommerce.SplashScreen.Presenter;

import com.varshaaweblabs.ecommerce.SplashScreen.Model.OrderStates_Resp;
import com.varshaaweblabs.ecommerce.SplashScreen.Model.ProductOptionMeta_Data;
import com.varshaaweblabs.ecommerce.SplashScreen.Model.ProductOptionsMeta_Resp;

import java.util.ArrayList;

/**
 * Created by dinesh on 28/8/17.
 */

public interface ISplashInterface {
    void product_meta(ProductOptionsMeta_Resp product_data);
    void orderStates(OrderStates_Resp orderStates_resp);
}
