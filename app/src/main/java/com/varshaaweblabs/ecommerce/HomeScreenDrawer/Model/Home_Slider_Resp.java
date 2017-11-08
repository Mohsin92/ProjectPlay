package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Product_Recommend;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 3/11/17.
 */

public class Home_Slider_Resp implements Serializable {

    @SerializedName("slider")
    @Expose
    private List<Slider_Images> slider = null;

    @SerializedName("product")
    @Expose
    private List<Product_Recommend> product = null;

    public List<Product_Recommend> getProduct() {
        return product;
    }

    public void setProduct(List<Product_Recommend> product) {
        this.product = product;
    }

    public List<Slider_Images> getSlider() {
        return slider;
    }

    public void setSlider(List<Slider_Images> slider) {
        this.slider = slider;
    }

}

