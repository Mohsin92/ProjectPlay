package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dinesh on 23/9/17.
 */

public class ProductFull_Product implements Serializable {

    @SerializedName("id_shop_default")
    @Expose
    private String idShopDefault;
    @SerializedName("id_manufacturer")
    @Expose
    private String idManufacturer;
    @SerializedName("id_supplier")
    @Expose
    private String idSupplier;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("supplier_reference")
    @Expose
    private String supplierReference;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("depth")
    @Expose
    private String depth;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("quantity_discount")
    @Expose
    private String quantityDiscount;
    @SerializedName("ean13")
    @Expose
    private String ean13;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("cache_is_pack")
    @Expose
    private String cacheIsPack;
    @SerializedName("cache_has_attachments")
    @Expose
    private String cacheHasAttachments;
    @SerializedName("is_virtual")
    @Expose
    private String isVirtual;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("id_category_default")
    @Expose
    private String idCategoryDefault;
    @SerializedName("id_tax_rules_group")
    @Expose
    private String idTaxRulesGroup;
    @SerializedName("on_sale")
    @Expose
    private String onSale;
    @SerializedName("online_only")
    @Expose
    private String onlineOnly;
    @SerializedName("ecotax")
    @Expose
    private ProductFull_Ecotax ecotax;
    @SerializedName("minimal_quantity")
    @Expose
    private String minimalQuantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("wholesale_price")
    @Expose
    private String wholesalePrice;
    @SerializedName("unity")
    @Expose
    private String unity;
    @SerializedName("unit_price_ratio")
    @Expose
    private String unitPriceRatio;
    @SerializedName("additional_shipping_cost")
    @Expose
    private String additionalShippingCost;
    @SerializedName("customizable")
    @Expose
    private String customizable;
    @SerializedName("text_fields")
    @Expose
    private String textFields;
    @SerializedName("uploadable_files")
    @Expose
    private String uploadableFiles;
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
    private Boolean condition;
    @SerializedName("show_price")
    @Expose
    private Boolean showPrice;
    @SerializedName("indexed")
    @Expose
    private String indexed;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("cache_default_attribute")
    @Expose
    private String cacheDefaultAttribute;
    @SerializedName("advanced_stock_management")
    @Expose
    private String advancedStockManagement;
    @SerializedName("date_add")
    @Expose
    private String dateAdd;
    @SerializedName("date_upd")
    @Expose
    private String dateUpd;
    @SerializedName("pack_stock_type")
    @Expose
    private String packStockType;
    @SerializedName("meta_description")
    @Expose
    private String metaDescription;
    @SerializedName("meta_keywords")
    @Expose
    private String metaKeywords;
    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("link_rewrite")
    @Expose
    private String linkRewrite;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_short")
    @Expose
    private String descriptionShort;
    @SerializedName("available_now")
    @Expose
    private String availableNow;
    @SerializedName("available_later")
    @Expose
    private String availableLater;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_product")
    @Expose
    private Integer idProduct;
    @SerializedName("out_of_stock")
    @Expose
    private Integer outOfStock;
    @SerializedName("new")
    @Expose
    private Integer _new;
    @SerializedName("id_product_attribute")
    @Expose
    private String idProductAttribute;
    @SerializedName("quantity_wanted")
    @Expose
    private Integer quantityWanted;
    @SerializedName("extraContent")
    @Expose
    private List<Object> extraContent = null;
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
    private Double reduction;
    @SerializedName("specific_prices")
    @Expose
    private ProductFull_Product_SpecificPrice specificPrices;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("quantity_all_versions")
    @Expose
    private Integer quantityAllVersions;
    @SerializedName("id_image")
    @Expose
    private String idImage;
    @SerializedName("features")
    @Expose
    private List<ProductFull_Features> features = null;
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
    private List<Attribute> attributes = null;
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
    private String unitPrice;
    @SerializedName("customizations")
    @Expose
    private Customization Customization;
    @SerializedName("id_customization")
    @Expose
    private Integer idCustomization;
    @SerializedName("is_customizable")
    @Expose

    private Boolean isCustomizable;

    @SerializedName("show_quantities")
    @Expose
    private Boolean showQuantities;
    @SerializedName("quantity_label")
    @Expose
    private String quantityLabel;
    @SerializedName("quantity_discounts")
    @Expose
    private List<Object> quantityDiscounts = null;
    @SerializedName("customer_group_discount")
    @Expose
    private Integer customerGroupDiscount;
    @SerializedName("weight_unit")
    @Expose
    private String weightUnit;
    @SerializedName("images")
    @Expose
    private List<ProductFull_image> images = null;
    @SerializedName("ProductFullImage")
    @Expose
    private ProductFull_image ProductFullImage;
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
    private Object discountPercentage;
    @SerializedName("discount_percentage_absolute")
    @Expose
    private Object discountPercentageAbsolute;
    @SerializedName("discount_amount")
    @Expose
    private Object discountAmount;
    @SerializedName("price_amount")
    @Expose
    private Double priceAmount;
    @SerializedName("regular_price_amount")
    @Expose
    private Double regularPriceAmount;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("discount_to_display")
    @Expose
    private Object discountToDisplay;
    @SerializedName("unit_price_full")
    @Expose
    private String unitPriceFull;
    @SerializedName("add_to_cart_url")
    @Expose
    private String addToCartUrl;
    @SerializedName("main_variants")
    @Expose
    private List<MainVariant> mainVariants = null;
    @SerializedName("flags")
    @Expose
    private ProductFull_Product_Flags flags = null;
    @SerializedName("labels")
    @Expose
    private Labels labels;
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
    @SerializedName("reference_to_display")
    @Expose
    private String referenceToDisplay;





    public String getIdShopDefault() {
        return idShopDefault;
    }

    public void setIdShopDefault(String idShopDefault) {
        this.idShopDefault = idShopDefault;
    }

    public String getIdManufacturer() {
        return idManufacturer;
    }

    public void setIdManufacturer(String idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSupplierReference() {
        return supplierReference;
    }

    public void setSupplierReference(String supplierReference) {
        this.supplierReference = supplierReference;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getQuantityDiscount() {
        return quantityDiscount;
    }

    public void setQuantityDiscount(String quantityDiscount) {
        this.quantityDiscount = quantityDiscount;
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

    public String getCacheIsPack() {
        return cacheIsPack;
    }

    public void setCacheIsPack(String cacheIsPack) {
        this.cacheIsPack = cacheIsPack;
    }

    public String getCacheHasAttachments() {
        return cacheHasAttachments;
    }

    public void setCacheHasAttachments(String cacheHasAttachments) {
        this.cacheHasAttachments = cacheHasAttachments;
    }

    public String getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(String isVirtual) {
        this.isVirtual = isVirtual;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIdCategoryDefault() {
        return idCategoryDefault;
    }

    public void setIdCategoryDefault(String idCategoryDefault) {
        this.idCategoryDefault = idCategoryDefault;
    }

    public String getIdTaxRulesGroup() {
        return idTaxRulesGroup;
    }

    public void setIdTaxRulesGroup(String idTaxRulesGroup) {
        this.idTaxRulesGroup = idTaxRulesGroup;
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

    public ProductFull_Ecotax getEcotax() {
        return ecotax;
    }

    public void setEcotax(ProductFull_Ecotax ecotax) {
        this.ecotax = ecotax;
    }

    public String getMinimalQuantity() {
        return minimalQuantity;
    }

    public void setMinimalQuantity(String minimalQuantity) {
        this.minimalQuantity = minimalQuantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
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

    public Boolean getCustomizable() {
        return isCustomizable;
    }

    public void setCustomizable(Boolean customizable) {
        isCustomizable = customizable;
    }

    public Boolean getShowQuantities() {
        return showQuantities;
    }

    public void setShowQuantities(Boolean showQuantities) {
        this.showQuantities = showQuantities;
    }

    public String getQuantityLabel() {
        return quantityLabel;
    }

    public void setQuantityLabel(String quantityLabel) {
        this.quantityLabel = quantityLabel;
    }

    public List<Object> getQuantityDiscounts() {
        return quantityDiscounts;
    }

    public void setQuantityDiscounts(List<Object> quantityDiscounts) {
        this.quantityDiscounts = quantityDiscounts;
    }

    public Integer getCustomerGroupDiscount() {
        return customerGroupDiscount;
    }

    public void setCustomerGroupDiscount(Integer customerGroupDiscount) {
        this.customerGroupDiscount = customerGroupDiscount;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public List<ProductFull_image> getImages() {
        return images;
    }

    public void setImages(List<ProductFull_image> images) {
        this.images = images;
    }

    public ProductFull_image getCover() {
        return ProductFullImage;
    }

    public void setCover(ProductFull_image ProductFullImage) {
        this.ProductFullImage = ProductFullImage;
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

    public Object getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Object discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Object getDiscountPercentageAbsolute() {
        return discountPercentageAbsolute;
    }

    public void setDiscountPercentageAbsolute(Object discountPercentageAbsolute) {
        this.discountPercentageAbsolute = discountPercentageAbsolute;
    }

    public Object getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Object discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(Double priceAmount) {
        this.priceAmount = priceAmount;
    }

    public Double getRegularPriceAmount() {
        return regularPriceAmount;
    }

    public void setRegularPriceAmount(Double regularPriceAmount) {
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

    public String getAddToCartUrl() {
        return addToCartUrl;
    }

    public void setAddToCartUrl(String addToCartUrl) {
        this.addToCartUrl = addToCartUrl;
    }

    public List<MainVariant> getMainVariants() {
        return mainVariants;
    }

    public void setMainVariants(List<MainVariant> mainVariants) {
        this.mainVariants = mainVariants;
    }

    public ProductFull_Product_Flags getFlags() {
        return flags;
    }

    public void setFlags(ProductFull_Product_Flags flags) {
        this.flags = flags;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
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

    public String getReferenceToDisplay() {
        return referenceToDisplay;
    }

    public void setReferenceToDisplay(String referenceToDisplay) {
        this.referenceToDisplay = referenceToDisplay;
    }

    public void setCustomizable(String customizable) {
        this.customizable = customizable;
    }

    public String getTextFields() {
        return textFields;
    }

    public void setTextFields(String textFields) {
        this.textFields = textFields;
    }

    public String getUploadableFiles() {
        return uploadableFiles;
    }

    public void setUploadableFiles(String uploadableFiles) {
        this.uploadableFiles = uploadableFiles;
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

    public Boolean getCondition() {
        return condition;
    }

    public void setCondition(Boolean condition) {
        this.condition = condition;
    }

    public Boolean getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(Boolean showPrice) {
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

    public String getCacheDefaultAttribute() {
        return cacheDefaultAttribute;
    }

    public void setCacheDefaultAttribute(String cacheDefaultAttribute) {
        this.cacheDefaultAttribute = cacheDefaultAttribute;
    }

    public String getAdvancedStockManagement() {
        return advancedStockManagement;
    }

    public void setAdvancedStockManagement(String advancedStockManagement) {
        this.advancedStockManagement = advancedStockManagement;
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

    public String getPackStockType() {
        return packStockType;
    }

    public void setPackStockType(String packStockType) {
        this.packStockType = packStockType;
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

    public String getLinkRewrite() {
        return linkRewrite;
    }

    public void setLinkRewrite(String linkRewrite) {
        this.linkRewrite = linkRewrite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(Integer outOfStock) {
        this.outOfStock = outOfStock;
    }

    public Integer get_new() {
        return _new;
    }

    public void set_new(Integer _new) {
        this._new = _new;
    }

    public String getIdProductAttribute() {
        return idProductAttribute;
    }

    public void setIdProductAttribute(String idProductAttribute) {
        this.idProductAttribute = idProductAttribute;
    }

    public Integer getQuantityWanted() {
        return quantityWanted;
    }

    public void setQuantityWanted(Integer quantityWanted) {
        this.quantityWanted = quantityWanted;
    }

    public List<Object> getExtraContent() {
        return extraContent;
    }

    public void setExtraContent(List<Object> extraContent) {
        this.extraContent = extraContent;
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

    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    public ProductFull_Product_SpecificPrice getSpecificPrices() {
        return specificPrices;
    }

    public void setSpecificPrices(ProductFull_Product_SpecificPrice specificPrices) {
        this.specificPrices = specificPrices;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantityAllVersions() {
        return quantityAllVersions;
    }

    public void setQuantityAllVersions(Integer quantityAllVersions) {
        this.quantityAllVersions = quantityAllVersions;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public List<ProductFull_Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<ProductFull_Features> features) {
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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Customization getCustomizations() {
        return Customization;
    }

    public void setCustomizations(Customization Customization) {
        this.Customization = Customization;
    }

    public Integer getIdCustomization() {
        return idCustomization;
    }

    public void setIdCustomization(Integer idCustomization) {
        this.idCustomization = idCustomization;
    }
}
