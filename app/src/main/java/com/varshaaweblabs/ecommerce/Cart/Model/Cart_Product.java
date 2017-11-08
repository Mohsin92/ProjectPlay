package com.varshaaweblabs.ecommerce.Cart.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.varshaaweblabs.ecommerce.Filter.Model.Ecotax;
import com.varshaaweblabs.ecommerce.Filter.Model.Product_Cover;
import com.varshaaweblabs.ecommerce.Filter.Model.Product_Image;
import com.varshaaweblabs.ecommerce.Filter.Model.Product_Label;
import com.varshaaweblabs.ecommerce.Filter.Model.Product_MainVariant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohsin on 25/9/17.
 */

public class Cart_Product implements Serializable {

    @SerializedName("id_product_attribute")
    @Expose
    private String idProductAttribute;
    @SerializedName("id_product")
    @Expose
    private String idProduct;
    @SerializedName("cart_quantity")
    @Expose
    private String cartQuantity;
    @SerializedName("id_shop")
    @Expose
    private String idShop;
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
    @SerializedName("manufacturer_name")
    @Expose
    private String manufacturerName;
    @SerializedName("on_sale")
    @Expose
    private String onSale;
    @SerializedName("ecotax")
    @Expose
    private Ecotax ecotax;
    @SerializedName("additional_shipping_cost")
    @Expose
    private String additionalShippingCost;
    @SerializedName("available_for_order")
    @Expose
    private String availableForOrder;
    @SerializedName("show_price")
    @Expose
    private Boolean showPrice;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("unity")
    @Expose
    private String unity;
    @SerializedName("unit_price_ratio")
    @Expose
    private String unitPriceRatio;
    @SerializedName("quantity_available")
    @Expose
    private String quantityAvailable;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("depth")
    @Expose
    private String depth;
    @SerializedName("out_of_stock")
    @Expose
    private String outOfStock;
    @SerializedName("weight")
    @Expose
    private Integer weight;
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
    @SerializedName("unique_id")
    @Expose
    private String uniqueId;
    @SerializedName("id_address_delivery")
    @Expose
    private String idAddressDelivery;
    @SerializedName("advanced_stock_management")
    @Expose
    private String advancedStockManagement;
    @SerializedName("supplier_reference")
    @Expose
    private String supplierReference;
    @SerializedName("customization_quantity")
    @Expose
    private Object customizationQuantity;
    @SerializedName("price_attribute")
    @Expose
    private String priceAttribute;
    @SerializedName("ecotax_attr")
    @Expose
    private String ecotaxAttr;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("weight_attribute")
    @Expose
    private Integer weightAttribute;
    @SerializedName("ean13")
    @Expose
    private String ean13;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("minimal_quantity")
    @Expose
    private String minimalQuantity;
    @SerializedName("wholesale_price")
    @Expose
    private String wholesalePrice;
    @SerializedName("id_image")
    @Expose
    private String idImage;
    @SerializedName("legend")
    @Expose
    private String legend;
    @SerializedName("reduction_type")
    @Expose
    private String reductionType;
    @SerializedName("is_gift")
    @Expose
    private Boolean isGift;
    @SerializedName("reduction")
    @Expose
    private Double reduction;
    @SerializedName("price_without_reduction")
    @Expose
    private String priceWithoutReduction;
    @SerializedName("specific_prices")
    @Expose
    private Object specificPrices;
    @SerializedName("stock_quantity")
    @Expose
    private Integer stockQuantity;
    @SerializedName("price_with_reduction")
    @Expose
    private String priceWithReduction;
    @SerializedName("price_with_reduction_without_tax")
    @Expose
    private String priceWithReductionWithoutTax;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("total_wt")
    @Expose
    private String totalWt;
    @SerializedName("price_wt")
    @Expose
    private String priceWt;
    @SerializedName("reduction_applies")
    @Expose
    private Boolean reductionApplies;
    @SerializedName("quantity_discount_applies")
    @Expose
    private Boolean quantityDiscountApplies;
    @SerializedName("allow_oosp")
    @Expose
    private Integer allowOosp;
    @SerializedName("features")
    @Expose
    private List<Object> features = null;
    @SerializedName("attributes")
    @Expose
    private Cart_Product_Attributes attributes;
    @SerializedName("attributes_small")
    @Expose
    private String attributesSmall;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("tax_name")
    @Expose
    private String taxName;
    @SerializedName("remove_from_cart_url")
    @Expose
    private String removeFromCartUrl;
    @SerializedName("up_quantity_url")
    @Expose
    private String upQuantityUrl;
    @SerializedName("down_quantity_url")
    @Expose
    private String downQuantityUrl;
    @SerializedName("update_quantity_url")
    @Expose
    private String updateQuantityUrl;
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
    private Boolean condition;
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
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("weight_unit")
    @Expose
    private String weightUnit;
    @SerializedName("images")
    @Expose
    private List<Product_Image> images = null;
    @SerializedName("cover")
    @Expose
    private Product_Cover cover;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("canonical_url")
    @Expose
    private String canonicalUrl;
    @SerializedName("has_discount")
    @Expose
    private Boolean hasDiscount;
    @SerializedName("discount_type")
    @Expose
    private Object discountType;
    @SerializedName("discount_percentage")
    @Expose
    private String discountPercentage;
    @SerializedName("discount_percentage_absolute")
    @Expose
    private String discountPercentageAbsolute;
    @SerializedName("discount_amount")
    @Expose
    private Object discountAmount;
    @SerializedName("regular_price_amount")
    @Expose
    private String regularPriceAmount;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("discount_to_display")
    @Expose
    private Object discountToDisplay;
    @SerializedName("unit_price_full")
    @Expose
    private String unitPriceFull;
    @SerializedName("unit_price")
    @Expose
    private String unitPrice;
    @SerializedName("add_to_cart_url")
    @Expose
    private String addToCartUrl;
    @SerializedName("main_variants")
    @Expose
    private List<Product_MainVariant> mainVariants = null;
    @SerializedName("flags")
    @Expose
    private Cart_Flag flags;
    @SerializedName("labels")
    @Expose
    private Product_Label labels;
    @SerializedName("show_availability")
    @Expose
    private Boolean showAvailability;
    @SerializedName("availability_date")
    @Expose
    private Object availabilityDate;
    @SerializedName("availability_message")
    @Expose
    private String availabilityMessage;
    @SerializedName("availability")
    @Expose
    private String availability;
    @SerializedName("quantity_discounts")
    @Expose
    private List<Object> quantityDiscounts = null;
    @SerializedName("reference_to_display")
    @Expose
    private String referenceToDisplay;
    @SerializedName("embedded_attributes")
    @Expose
    private Cart_Product_Embedded_Attributes embeddedAttributes;
    @SerializedName("customizations")
    @Expose
    private List<Object> customizations = null;

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

    public String getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(String cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
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

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getOnSale() {
        return onSale;
    }

    public void setOnSale(String onSale) {
        this.onSale = onSale;
    }

    public Ecotax getEcotax() {
        return ecotax;
    }

    public void setEcotax(Ecotax ecotax) {
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

    public Boolean getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(Boolean showPrice) {
        this.showPrice = showPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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

    public String getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(String quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(String outOfStock) {
        this.outOfStock = outOfStock;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getIdAddressDelivery() {
        return idAddressDelivery;
    }

    public void setIdAddressDelivery(String idAddressDelivery) {
        this.idAddressDelivery = idAddressDelivery;
    }

    public String getAdvancedStockManagement() {
        return advancedStockManagement;
    }

    public void setAdvancedStockManagement(String advancedStockManagement) {
        this.advancedStockManagement = advancedStockManagement;
    }

    public String getSupplierReference() {
        return supplierReference;
    }

    public void setSupplierReference(String supplierReference) {
        this.supplierReference = supplierReference;
    }

    public Object getCustomizationQuantity() {
        return customizationQuantity;
    }

    public void setCustomizationQuantity(Object customizationQuantity) {
        this.customizationQuantity = customizationQuantity;
    }

    public String getPriceAttribute() {
        return priceAttribute;
    }

    public void setPriceAttribute(String priceAttribute) {
        this.priceAttribute = priceAttribute;
    }

    public String getEcotaxAttr() {
        return ecotaxAttr;
    }

    public void setEcotaxAttr(String ecotaxAttr) {
        this.ecotaxAttr = ecotaxAttr;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getWeightAttribute() {
        return weightAttribute;
    }

    public void setWeightAttribute(Integer weightAttribute) {
        this.weightAttribute = weightAttribute;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getMinimalQuantity() {
        return minimalQuantity;
    }

    public void setMinimalQuantity(String minimalQuantity) {
        this.minimalQuantity = minimalQuantity;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public String getReductionType() {
        return reductionType;
    }

    public void setReductionType(String reductionType) {
        this.reductionType = reductionType;
    }

    public Boolean getIsGift() {
        return isGift;
    }

    public void setIsGift(Boolean isGift) {
        this.isGift = isGift;
    }

    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    public String getPriceWithoutReduction() {
        return priceWithoutReduction;
    }

    public void setPriceWithoutReduction(String priceWithoutReduction) {
        this.priceWithoutReduction = priceWithoutReduction;
    }

    public Object getSpecificPrices() {
        return specificPrices;
    }

    public void setSpecificPrices(Object specificPrices) {
        this.specificPrices = specificPrices;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getPriceWithReduction() {
        return priceWithReduction;
    }

    public void setPriceWithReduction(String priceWithReduction) {
        this.priceWithReduction = priceWithReduction;
    }

    public String getPriceWithReductionWithoutTax() {
        return priceWithReductionWithoutTax;
    }

    public void setPriceWithReductionWithoutTax(String priceWithReductionWithoutTax) {
        this.priceWithReductionWithoutTax = priceWithReductionWithoutTax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalWt() {
        return totalWt;
    }

    public void setTotalWt(String totalWt) {
        this.totalWt = totalWt;
    }

    public String getPriceWt() {
        return priceWt;
    }

    public void setPriceWt(String priceWt) {
        this.priceWt = priceWt;
    }

    public Boolean getReductionApplies() {
        return reductionApplies;
    }

    public void setReductionApplies(Boolean reductionApplies) {
        this.reductionApplies = reductionApplies;
    }

    public Boolean getQuantityDiscountApplies() {
        return quantityDiscountApplies;
    }

    public void setQuantityDiscountApplies(Boolean quantityDiscountApplies) {
        this.quantityDiscountApplies = quantityDiscountApplies;
    }

    public Integer getAllowOosp() {
        return allowOosp;
    }

    public void setAllowOosp(Integer allowOosp) {
        this.allowOosp = allowOosp;
    }

    public List<Object> getFeatures() {
        return features;
    }

    public void setFeatures(List<Object> features) {
        this.features = features;
    }


    public String getAttributesSmall() {
        return attributesSmall;
    }

    public void setAttributesSmall(String attributesSmall) {
        this.attributesSmall = attributesSmall;
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

    public String getRemoveFromCartUrl() {
        return removeFromCartUrl;
    }

    public void setRemoveFromCartUrl(String removeFromCartUrl) {
        this.removeFromCartUrl = removeFromCartUrl;
    }

    public String getUpQuantityUrl() {
        return upQuantityUrl;
    }

    public void setUpQuantityUrl(String upQuantityUrl) {
        this.upQuantityUrl = upQuantityUrl;
    }

    public String getDownQuantityUrl() {
        return downQuantityUrl;
    }

    public void setDownQuantityUrl(String downQuantityUrl) {
        this.downQuantityUrl = downQuantityUrl;
    }

    public String getUpdateQuantityUrl() {
        return updateQuantityUrl;
    }

    public void setUpdateQuantityUrl(String updateQuantityUrl) {
        this.updateQuantityUrl = updateQuantityUrl;
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

    public String getNew() {
        return _new;
    }

    public void setNew(String _new) {
        this._new = _new;
    }

    public Boolean getCondition() {
        return condition;
    }

    public void setCondition(Boolean condition) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Object getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Object discountType) {
        this.discountType = discountType;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getDiscountPercentageAbsolute() {
        return discountPercentageAbsolute;
    }

    public void setDiscountPercentageAbsolute(String discountPercentageAbsolute) {
        this.discountPercentageAbsolute = discountPercentageAbsolute;
    }

    public Object getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Object discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getRegularPriceAmount() {
        return regularPriceAmount;
    }

    public void setRegularPriceAmount(String regularPriceAmount) {
        this.regularPriceAmount = regularPriceAmount;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Object getDiscountToDisplay() {
        return discountToDisplay;
    }

    public void setDiscountToDisplay(Object discountToDisplay) {
        this.discountToDisplay = discountToDisplay;
    }

    public String getUnitPriceFull() {
        return unitPriceFull;
    }

    public void setUnitPriceFull(String unitPriceFull) {
        this.unitPriceFull = unitPriceFull;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAddToCartUrl() {
        return addToCartUrl;
    }

    public void setAddToCartUrl(String addToCartUrl) {
        this.addToCartUrl = addToCartUrl;
    }


    public Boolean getShowAvailability() {
        return showAvailability;
    }

    public void setShowAvailability(Boolean showAvailability) {
        this.showAvailability = showAvailability;
    }

    public Object getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(Object availabilityDate) {
        this.availabilityDate = availabilityDate;
    }

    public String getAvailabilityMessage() {
        return availabilityMessage;
    }

    public void setAvailabilityMessage(String availabilityMessage) {
        this.availabilityMessage = availabilityMessage;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public List<Object> getQuantityDiscounts() {
        return quantityDiscounts;
    }

    public void setQuantityDiscounts(List<Object> quantityDiscounts) {
        this.quantityDiscounts = quantityDiscounts;
    }

    public String getReferenceToDisplay() {
        return referenceToDisplay;
    }

    public void setReferenceToDisplay(String referenceToDisplay) {
        this.referenceToDisplay = referenceToDisplay;
    }


    public List<Object> getCustomizations() {
        return customizations;
    }

    public void setCustomizations(List<Object> customizations) {
        this.customizations = customizations;
    }

    public Boolean getGift() {
        return isGift;
    }

    public void setGift(Boolean gift) {
        isGift = gift;
    }

    public Cart_Product_Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Cart_Product_Attributes attributes) {
        this.attributes = attributes;
    }

    public String get_new() {
        return _new;
    }

    public void set_new(String _new) {
        this._new = _new;
    }

    public List<Product_Image> getImages() {
        return images;
    }

    public void setImages(List<Product_Image> images) {
        this.images = images;
    }

    public Product_Cover getCover() {
        return cover;
    }

    public void setCover(Product_Cover cover) {
        this.cover = cover;
    }

    public List<Product_MainVariant> getMainVariants() {
        return mainVariants;
    }

    public void setMainVariants(List<Product_MainVariant> mainVariants) {
        this.mainVariants = mainVariants;
    }

    public Product_Label getLabels() {
        return labels;
    }

    public void setLabels(Product_Label labels) {
        this.labels = labels;
    }

    public Cart_Product_Embedded_Attributes getEmbeddedAttributes() {
        return embeddedAttributes;
    }

    public void setEmbeddedAttributes(Cart_Product_Embedded_Attributes embeddedAttributes) {
        this.embeddedAttributes = embeddedAttributes;
    }

    public Cart_Flag getFlags() {
        return flags;
    }

    public void setFlags(Cart_Flag flags) {
        this.flags = flags;
    }
}
