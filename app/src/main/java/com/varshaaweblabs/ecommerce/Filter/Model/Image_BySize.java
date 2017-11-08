package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 8/9/17.
 */

public class Image_BySize implements Serializable {

    @SerializedName("small_default")
    @Expose
    private Image_SmallDefault smallDefault;
    @SerializedName("cart_default")
    @Expose
    private Image_CartDefault cartDefault;
    @SerializedName("home_default")
    @Expose
    private Image_HomeDefault homeDefault;
    @SerializedName("medium_default")
    @Expose
    private Image_MediumDefault mediumDefault;
    @SerializedName("large_default")
    @Expose
    private Image_Largedefault largeDefault;


    public Image_SmallDefault getSmallDefault() {
        return smallDefault;
    }

    public void setSmallDefault(Image_SmallDefault smallDefault) {
        this.smallDefault = smallDefault;
    }

    public Image_CartDefault getCartDefault() {
        return cartDefault;
    }

    public void setCartDefault(Image_CartDefault cartDefault) {
        this.cartDefault = cartDefault;
    }

    public Image_HomeDefault getHomeDefault() {
        return homeDefault;
    }

    public void setHomeDefault(Image_HomeDefault homeDefault) {
        this.homeDefault = homeDefault;
    }

    public Image_MediumDefault getMediumDefault() {
        return mediumDefault;
    }

    public void setMediumDefault(Image_MediumDefault mediumDefault) {
        this.mediumDefault = mediumDefault;
    }

    public Image_Largedefault getLargeDefault() {
        return largeDefault;
    }

    public void setLargeDefault(Image_Largedefault largeDefault) {
        this.largeDefault = largeDefault;
    }
}
