package com.varshaaweblabs.ecommerce.Filter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 8/9/17.
 */

public class Product_EmbeddedAttributes implements Serializable {

    @SerializedName("id_product")
    @Expose
    private String idProduct;
    @SerializedName("id_supplier")
    @Expose
    private String idSupplier;
    @SerializedName("id_manufacturer")
    @Expose
    private String idManufacturer;
    @SerializedName("id_category_default")
    @Expose
    private String idCategoryDefault;
    @SerializedName("id_shop_default")
    @Expose
    private String idShopDefault;
    @SerializedName("on_sale")
    @Expose
    private String onSale;
    @SerializedName("online_only")
    @Expose
    private String onlineOnly;
    @SerializedName("ecotax")
    @Expose
    private String ecotax;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("minimal_quantity")
    @Expose
    private String minimalQuantity;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("unity")
    @Expose
    private String unity;
    @SerializedName("unit_price_ratio")
    @Expose
    private String unitPriceRatio;
    @SerializedName("additional_shipping_cost")
    @Expose
    private String additionalShippingCost;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("out_of_stock")
    @Expose
    private String outOfStock;
    @SerializedName("customizable")
    @Expose
    private String customizable;
    @SerializedName("uploadable_files")
    @Expose
    private String uploadableFiles;
    @SerializedName("text_fields")
    @Expose
    private String textFields;
    @SerializedName("redirect_type")
    @Expose
    private String redirectType;
    @SerializedName("id_type_redirected")
    @Expose
    private String idTypeRedirected;
    @SerializedName("available_for_order")
    @Expose
    private String availableForOrder;
    @SerializedName("available_date")
    @Expose
    private Object availableDate;
    @SerializedName("show_condition")
    @Expose
    private String showCondition;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("show_price")
    @Expose
    private String showPrice;
    @SerializedName("indexed")
    @Expose
    private String indexed;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("is_virtual")
    @Expose
    private String isVirtual;
    @SerializedName("cache_default_attribute")
    @Expose
    private String cacheDefaultAttribute;
    @SerializedName("date_add")
    @Expose
    private String dateAdd;
    @SerializedName("date_upd")
    @Expose
    private String dateUpd;
    @SerializedName("advanced_stock_management")
    @Expose
    private String advancedStockManagement;
    @SerializedName("pack_stock_type")
    @Expose
    private String packStockType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_short")
    @Expose
    private String descriptionShort;
    @SerializedName("link_rewrite")
    @Expose
    private String linkRewrite;
    @SerializedName("meta_description")
    @Expose
    private String metaDescription;
    @SerializedName("meta_keywords")
    @Expose
    private String metaKeywords;
    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("available_now")
    @Expose
    private String availableNow;
    @SerializedName("available_later")
    @Expose
    private String availableLater;
    @SerializedName("id_image")
    @Expose
    private String idImage;
    @SerializedName("id_product_attribute")
    @Expose
    private Integer idProductAttribute;
    @SerializedName("new")
    @Expose
    private String _new;
    @SerializedName("allow_oosp")
    @Expose
    private Integer allowOosp;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("attribute_price")
    @Expose
    private Integer attributePrice;
    @SerializedName("price_tax_exc")
    @Expose
    private Double priceTaxExc;
    @SerializedName("price_without_reduction")
    @Expose
    private Double priceWithoutReduction;
    @SerializedName("reduction")
    @Expose
    private Integer reduction;
    @SerializedName("specific_prices")
    @Expose
    private Boolean specificPrices;
    @SerializedName("quantity_all_versions")
    @Expose
    private Integer quantityAllVersions;
    @SerializedName("features")
    @Expose
    private List<Object> features = null;
    @SerializedName("attachments")
    @Expose
    private List<Object> attachments = null;
    @SerializedName("virtual")
    @Expose
    private Integer virtual;
    @SerializedName("pack")
    @Expose
    private Integer pack;
    @SerializedName("packItems")
    @Expose
    private List<Object> packItems = null;
    @SerializedName("nopackprice")
    @Expose
    private Integer nopackprice;
    @SerializedName("customization_required")
    @Expose
    private Boolean customizationRequired;
    @SerializedName("attributes")
    @Expose
    private Product_attributes attributes;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("tax_name")
    @Expose
    private String taxName;
    @SerializedName("ecotax_rate")
    @Expose
    private Integer ecotaxRate;
    @SerializedName("unit_price")
    @Expose
    private Integer unitPrice;

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
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

    public String getIdCategoryDefault() {
        return idCategoryDefault;
    }

    public void setIdCategoryDefault(String idCategoryDefault) {
        this.idCategoryDefault = idCategoryDefault;
    }

    public String getIdShopDefault() {
        return idShopDefault;
    }

    public void setIdShopDefault(String idShopDefault) {
        this.idShopDefault = idShopDefault;
    }

    public String getOnSale() {
        return onSale;
    }

    public void setOnSale(String onSale) {
        this.onSale = onSale;
    }

    public String getOnlineOnly() {
        return onlineOnly;
    }

    public void setOnlineOnly(String onlineOnly) {
        this.onlineOnly = onlineOnly;
    }

    public String getEcotax() {
        return ecotax;
    }

    public void setEcotax(String ecotax) {
        this.ecotax = ecotax;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMinimalQuantity() {
        return minimalQuantity;
    }

    public void setMinimalQuantity(String minimalQuantity) {
        this.minimalQuantity = minimalQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public String getAdditionalShippingCost() {
        return additionalShippingCost;
    }

    public void setAdditionalShippingCost(String additionalShippingCost) {
        this.additionalShippingCost = additionalShippingCost;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(String outOfStock) {
        this.outOfStock = outOfStock;
    }

    public String getCustomizable() {
        return customizable;
    }

    public void setCustomizable(String customizable) {
        this.customizable = customizable;
    }

    public String getUploadableFiles() {
        return uploadableFiles;
    }

    public void setUploadableFiles(String uploadableFiles) {
        this.uploadableFiles = uploadableFiles;
    }

    public String getTextFields() {
        return textFields;
    }

    public void setTextFields(String textFields) {
        this.textFields = textFields;
    }

    public String getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(String redirectType) {
        this.redirectType = redirectType;
    }

    public String getIdTypeRedirected() {
        return idTypeRedirected;
    }

    public void setIdTypeRedirected(String idTypeRedirected) {
        this.idTypeRedirected = idTypeRedirected;
    }

    public String getAvailableForOrder() {
        return availableForOrder;
    }

    public void setAvailableForOrder(String availableForOrder) {
        this.availableForOrder = availableForOrder;
    }

    public Object getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Object availableDate) {
        this.availableDate = availableDate;
    }

    public String getShowCondition() {
        return showCondition;
    }

    public void setShowCondition(String showCondition) {
        this.showCondition = showCondition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice;
    }

    public String getIndexed() {
        return indexed;
    }

    public void setIndexed(String indexed) {
        this.indexed = indexed;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(String isVirtual) {
        this.isVirtual = isVirtual;
    }

    public String getCacheDefaultAttribute() {
        return cacheDefaultAttribute;
    }

    public void setCacheDefaultAttribute(String cacheDefaultAttribute) {
        this.cacheDefaultAttribute = cacheDefaultAttribute;
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

    public String getAdvancedStockManagement() {
        return advancedStockManagement;
    }

    public void setAdvancedStockManagement(String advancedStockManagement) {
        this.advancedStockManagement = advancedStockManagement;
    }

    public String getPackStockType() {
        return packStockType;
    }

    public void setPackStockType(String packStockType) {
        this.packStockType = packStockType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getLinkRewrite() {
        return linkRewrite;
    }

    public void setLinkRewrite(String linkRewrite) {
        this.linkRewrite = linkRewrite;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public Integer getIdProductAttribute() {
        return idProductAttribute;
    }

    public void setIdProductAttribute(Integer idProductAttribute) {
        this.idProductAttribute = idProductAttribute;
    }

    public String get_new() {
        return _new;
    }

    public void set_new(String _new) {
        this._new = _new;
    }

    public Integer getAllowOosp() {
        return allowOosp;
    }

    public void setAllowOosp(Integer allowOosp) {
        this.allowOosp = allowOosp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getAttributePrice() {
        return attributePrice;
    }

    public void setAttributePrice(Integer attributePrice) {
        this.attributePrice = attributePrice;
    }

    public Double getPriceTaxExc() {
        return priceTaxExc;
    }

    public void setPriceTaxExc(Double priceTaxExc) {
        this.priceTaxExc = priceTaxExc;
    }

    public Double getPriceWithoutReduction() {
        return priceWithoutReduction;
    }

    public void setPriceWithoutReduction(Double priceWithoutReduction) {
        this.priceWithoutReduction = priceWithoutReduction;
    }

    public Integer getReduction() {
        return reduction;
    }

    public void setReduction(Integer reduction) {
        this.reduction = reduction;
    }

    public Boolean getSpecificPrices() {
        return specificPrices;
    }

    public void setSpecificPrices(Boolean specificPrices) {
        this.specificPrices = specificPrices;
    }

    public Integer getQuantityAllVersions() {
        return quantityAllVersions;
    }

    public void setQuantityAllVersions(Integer quantityAllVersions) {
        this.quantityAllVersions = quantityAllVersions;
    }

    public List<Object> getFeatures() {
        return features;
    }

    public void setFeatures(List<Object> features) {
        this.features = features;
    }

    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    public Integer getVirtual() {
        return virtual;
    }

    public void setVirtual(Integer virtual) {
        this.virtual = virtual;
    }

    public Integer getPack() {
        return pack;
    }

    public void setPack(Integer pack) {
        this.pack = pack;
    }

    public List<Object> getPackItems() {
        return packItems;
    }

    public void setPackItems(List<Object> packItems) {
        this.packItems = packItems;
    }

    public Integer getNopackprice() {
        return nopackprice;
    }

    public void setNopackprice(Integer nopackprice) {
        this.nopackprice = nopackprice;
    }

    public Boolean getCustomizationRequired() {
        return customizationRequired;
    }

    public void setCustomizationRequired(Boolean customizationRequired) {
        this.customizationRequired = customizationRequired;
    }

    public Product_attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Product_attributes attributes) {
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

    public Integer getEcotaxRate() {
        return ecotaxRate;
    }

    public void setEcotaxRate(Integer ecotaxRate) {
        this.ecotaxRate = ecotaxRate;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }
}
