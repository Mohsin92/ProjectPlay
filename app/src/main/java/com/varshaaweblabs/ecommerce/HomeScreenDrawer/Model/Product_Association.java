package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 6/9/17.
 */

public class Product_Association implements Serializable {

    @SerializedName("categories")
    @Expose
    private List<Product_Category> categories = null;
    @SerializedName("images")
    @Expose
    private List<Product_Image> images = null;
    @SerializedName("combinations")
    @Expose
    private List<Product_Combination> combinations = null;
    @SerializedName("product_option_values")
    @Expose
    private List<ProductOptionValues> productOptionValues = null;
    @SerializedName("stock_availables")
    @Expose
    private List<Product_StockAvailable> stockAvailables = null;

    public List<Product_Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Product_Category> categories) {
        this.categories = categories;
    }

    public List<Product_Image> getImages() {
        return images;
    }

    public void setImages(List<Product_Image> images) {
        this.images = images;
    }

    public List<Product_Combination> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<Product_Combination> combinations) {
        this.combinations = combinations;
    }

    public List<ProductOptionValues> getProductOptionValues() {
        return productOptionValues;
    }

    public void setProductOptionValues(List<ProductOptionValues> productOptionValues) {
        this.productOptionValues = productOptionValues;
    }

    public List<Product_StockAvailable> getStockAvailables() {
        return stockAvailables;
    }

    public void setStockAvailables(List<Product_StockAvailable> stockAvailables) {
        this.stockAvailables = stockAvailables;
    }
}

