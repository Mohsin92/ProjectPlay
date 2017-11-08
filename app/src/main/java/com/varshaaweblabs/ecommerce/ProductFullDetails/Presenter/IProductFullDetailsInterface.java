package com.varshaaweblabs.ecommerce.ProductFullDetails.Presenter;

import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Combination;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Custmization_Resp;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.ProductFull;
import com.varshaaweblabs.ecommerce.ProductFullDetails.View.ProductFullDetailsView;

import okhttp3.Response;

/**
 * Created by dinesh on 23/9/17.
 */

public interface IProductFullDetailsInterface {

    void Prod_FullData(ProductFull prod_full, ProductFullDetailsView.View view);
    void getCustmizations(Custmization_Resp resp_cust);

}
