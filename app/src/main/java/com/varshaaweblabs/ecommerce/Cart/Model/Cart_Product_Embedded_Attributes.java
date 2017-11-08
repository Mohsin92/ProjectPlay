package com.varshaaweblabs.ecommerce.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mohsin on 27/9/17.
 */

public class Cart_Product_Embedded_Attributes implements Serializable {

    @SerializedName("id_product_attribute")
    @Expose
    private String idProductAttribute;
    @SerializedName("id_product")
    @Expose
    private String idProduct;
    @SerializedName("id_customization")
    @Expose
    private Object idCustomization;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_virtual")
    @Expose
    private String isVirtual;
    @SerializedName("description_short")
    @Expose
    private String descriptionShort;
    @SerializedName("available_now")
    @Expose
    private String availableNow;
    @SerializedName("available_later")
    @Expose
    private String availableLater;
    @SerializedName("id_category_default")
    @Expose
    private String idCategoryDefault;
    @SerializedName("id_supplier")
    @Expose
    private String idSupplier;
    @SerializedName("id_manufacturer")
    @Expose
    private String idManufacturer;
    @SerializedName("on_sale")
    @Expose
    private String onSale;
    @SerializedName("ecotax")
    @Expose
    private String ecotax;
    @SerializedName("additional_shipping_cost")
    @Expose
    private String additionalShippingCost;
    @SerializedName("available_for_order")
    @Expose
    private String availableForOrder;
    @SerializedName("show_price")
    @Expose
    private String showPrice;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("unity")
    @Expose
    private String unity;
    @SerializedName("unit_price_ratio")
    @Expose
    private String unitPriceRatio;
    @SerializedName("out_of_stock")
    @Expose
    private String outOfStock;
    @SerializedName("available_date")
    @Expose
    private String availableDate;
    @SerializedName("date_add")
    @Expose
    private String dateAdd;
    @SerializedName("date_upd")
    @Expose
    private String dateUpd;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("link_rewrite")
    @Expose
    private String linkRewrite;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("advanced_stock_management")
    @Expose
    private String advancedStockManagement;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("minimal_quantity")
    @Expose
    private String minimalQuantity;
    @SerializedName("id_image")
    @Expose
    private String idImage;
    @SerializedName("reduction")
    @Expose
    private Double reduction;
    @SerializedName("price_without_reduction")
    @Expose
    private Double priceWithoutReduction;
    @SerializedName("specific_prices")
    @Expose
    private Object specificPrices;
    @SerializedName("allow_oosp")
    @Expose
    private Integer allowOosp;
    @SerializedName("features")
    @Expose
    private List<Cart_Feature> features = null;
    @SerializedName("attributes")
    @Expose
    private Cart_Product_Attributes attributes;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("tax_name")
    @Expose
    private String taxName;
    @SerializedName("ecotax_rate")
    @Expose
    private String ecotaxRate;
    @SerializedName("customizable")
    @Expose
    private String customizable;
    @SerializedName("online_only")
    @Expose
    private String onlineOnly;
    @SerializedName("new")
    @Expose
    private String _new;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("pack")
    @Expose
    private String pack;
    @SerializedName("price_amount")
    @Expose
    private String priceAmount;
    @SerializedName("price_tax_exc")
    @Expose
    private String priceTaxExc;
    @SerializedName("quantity_wanted")
    @Expose
    private String quantityWanted;

    public String getIdProductAttribute() {
        return idProductAttribute;
    }

    public void setIdProductAttribute(String idProductAttribute) {
        this.idProductAttribute = idProductAttribute;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Object getIdCustomization() {
        return idCustomization;
    }

    public void setIdCustomization(Object idCustomization) {
        this.idCustomization = idCustomization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(String isVirtual) {
        this.isVirtual = isVirtual;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getAvailableNow() {
        return availableNow;
    }

    public void setAvailableNow(String availableNow) {
        this.availableNow = availableNow;
    }

    public String getAvailableLater() {
        return availableLater;
    }

    public void setAvailableLater(String availableLater) {
        this.availableLater = availableLater;
    }

    public String getIdCategoryDefault() {
        return idCategoryDefault;
    }

    public void setIdCategoryDefault(String idCategoryDefault) {
        this.idCategoryDefault = idCategoryDefault;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getIdManufacturer() {
        return idManufacturer;
    }

    public void setIdManufacturer(String idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    public String getOnSale() {
        return onSale;
    }

    public void setOnSale(String onSale) {
        this.onSale = onSale;
    }

    public String getEcotax() {
        return ecotax;
    }

    public void setEcotax(String ecotax) {
        this.ecotax = ecotax;
    }

    public String getAdditionalShippingCost() {
        return additionalShippingCost;
    }

    public void setAdditionalShippingCost(String additionalShippingCost) {
        this.additionalShippingCost = additionalShippingCost;
    }

    public String getAvailableForOrder() {
        return availableForOrder;
    }

    public void setAvailableForOrder(String availableForOrder) {
        this.availableForOrder = availableForOrder;
    }

    public String getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getUnitPriceRatio() {
        return unitPriceRatio;
    }

    public void setUnitPriceRatio(String unitPriceRatio) {
        this.unitPriceRatio = unitPriceRatio;
    }

    public String getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(String outOfStock) {
        this.outOfStock = outOfStock;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public String getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(String dateUpd) {
        this.dateUpd = dateUpd;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getLinkRewrite() {
        return linkRewrite;
    }

    public void setLinkRewrite(String linkRewrite) {
        this.linkRewrite = linkRewrite;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdvancedStockManagement() {
        return advancedStockManagement;
    }

    public void setAdvancedStockManagement(String advancedStockManagement) {
        this.advancedStockManagement = advancedStockManagement;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMinimalQuantity() {
        return minimalQuantity;
    }

    public void setMinimalQuantity(String minimalQuantity) {
        this.minimalQuantity = minimalQuantity;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    public Double getPriceWithoutReduction() {
        return priceWithoutReduction;
    }

    public void setPriceWithoutReduction(Double priceWithoutReduction) {
        this.priceWithoutReduction = priceWithoutReduction;
    }

    public Object getSpecificPrices() {
        return specificPrices;
    }

    public void setSpecificPrices(Object specificPrices) {
        this.specificPrices = specificPrices;
    }

    public Integer getAllowOosp() {
        return allowOosp;
    }

    public void setAllowOosp(Integer allowOosp) {
        this.allowOosp = allowOosp;
    }

    public List<Cart_Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Cart_Feature> features) {
        this.features = features;
    }

    public Cart_Product_Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Cart_Product_Attributes attributes) {
        this.attributes = attributes;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getEcotaxRate() {
        return ecotaxRate;
    }

    public void setEcotaxRate(String ecotaxRate) {
        this.ecotaxRate = ecotaxRate;
    }

    public String getCustomizable() {
        return customizable;
    }

    public void setCustomizable(String customizable) {
        this.customizable = customizable;
    }

    public String getOnlineOnly() {
        return onlineOnly;
    }

    public void setOnlineOnly(String onlineOnly) {
        this.onlineOnly = onlineOnly;
    }

    public String get_new() {
        return _new;
    }

    public void set_new(String _new) {
        this._new = _new;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(String priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPriceTaxExc() {
        return priceTaxExc;
    }

    public void setPriceTaxExc(String priceTaxExc) {
        this.priceTaxExc = priceTaxExc;
    }

    public String getQuantityWanted() {
        return quantityWanted;
    }

    public void setQuantityWanted(String quantityWanted) {
        this.quantityWanted = quantityWanted;
    }
}
