package com.varshaaweblabs.ecommerce.OrderDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 26/10/17.
 */

public class GetOrder_Data extends GetOrder_Resp implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_address_delivery")
    @Expose
    private String idAddressDelivery;
    @SerializedName("id_address_invoice")
    @Expose
    private String idAddressInvoice;
    @SerializedName("id_cart")
    @Expose
    private String idCart;
    @SerializedName("id_currency")
    @Expose
    private String idCurrency;
    @SerializedName("id_lang")
    @Expose
    private String idLang;
    @SerializedName("id_customer")
    @Expose
    private String idCustomer;
    @SerializedName("id_carrier")
    @Expose
    private String idCarrier;
    @SerializedName("current_state")
    @Expose
    private String currentState;
    @SerializedName("module")
    @Expose
    private String module;
    @SerializedName("invoice_number")
    @Expose
    private String invoiceNumber;
    @SerializedName("invoice_date")
    @Expose
    private String invoiceDate;
    @SerializedName("delivery_number")
    @Expose
    private String deliveryNumber;
    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;
    @SerializedName("valid")
    @Expose
    private String valid;
    @SerializedName("date_add")
    @Expose
    private String dateAdd;
    @SerializedName("date_upd")
    @Expose
    private String dateUpd;
    @SerializedName("shipping_number")
    @Expose
    private String shippingNumber;
    @SerializedName("id_shop_group")
    @Expose
    private String idShopGroup;
    @SerializedName("id_shop")
    @Expose
    private String idShop;
    @SerializedName("secure_key")
    @Expose
    private String secureKey;
    @SerializedName("payment")
    @Expose
    private String payment;
    @SerializedName("recyclable")
    @Expose
    private String recyclable;
    @SerializedName("gift")
    @Expose
    private String gift;
    @SerializedName("gift_message")
    @Expose
    private String giftMessage;
    @SerializedName("mobile_theme")
    @Expose
    private String mobileTheme;
    @SerializedName("total_discounts")
    @Expose
    private String totalDiscounts;
    @SerializedName("total_discounts_tax_incl")
    @Expose
    private String totalDiscountsTaxIncl;
    @SerializedName("total_discounts_tax_excl")
    @Expose
    private String totalDiscountsTaxExcl;
    @SerializedName("total_paid")
    @Expose
    private String totalPaid;
    @SerializedName("total_paid_tax_incl")
    @Expose
    private String totalPaidTaxIncl;
    @SerializedName("total_paid_tax_excl")
    @Expose
    private String totalPaidTaxExcl;
    @SerializedName("total_paid_real")
    @Expose
    private String totalPaidReal;
    @SerializedName("total_products")
    @Expose
    private String totalProducts;
    @SerializedName("total_products_wt")
    @Expose
    private String totalProductsWt;
    @SerializedName("total_shipping")
    @Expose
    private String totalShipping;
    @SerializedName("total_shipping_tax_incl")
    @Expose
    private String totalShippingTaxIncl;
    @SerializedName("total_shipping_tax_excl")
    @Expose
    private String totalShippingTaxExcl;
    @SerializedName("carrier_tax_rate")
    @Expose
    private String carrierTaxRate;
    @SerializedName("total_wrapping")
    @Expose
    private String totalWrapping;
    @SerializedName("total_wrapping_tax_incl")
    @Expose
    private String totalWrappingTaxIncl;
    @SerializedName("total_wrapping_tax_excl")
    @Expose
    private String totalWrappingTaxExcl;
    @SerializedName("round_mode")
    @Expose
    private String roundMode;
    @SerializedName("round_type")
    @Expose
    private String roundType;
    @SerializedName("conversion_rate")
    @Expose
    private String conversionRate;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("associations")
    @Expose
    private GetOrder_Association associations;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdAddressDelivery() {
        return idAddressDelivery;
    }

    public void setIdAddressDelivery(String idAddressDelivery) {
        this.idAddressDelivery = idAddressDelivery;
    }

    public String getIdAddressInvoice() {
        return idAddressInvoice;
    }

    public void setIdAddressInvoice(String idAddressInvoice) {
        this.idAddressInvoice = idAddressInvoice;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(String idCurrency) {
        this.idCurrency = idCurrency;
    }

    public String getIdLang() {
        return idLang;
    }

    public void setIdLang(String idLang) {
        this.idLang = idLang;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdCarrier() {
        return idCarrier;
    }

    public void setIdCarrier(String idCarrier) {
        this.idCarrier = idCarrier;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
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

    public String getShippingNumber() {
        return shippingNumber;
    }

    public void setShippingNumber(String shippingNumber) {
        this.shippingNumber = shippingNumber;
    }

    public String getIdShopGroup() {
        return idShopGroup;
    }

    public void setIdShopGroup(String idShopGroup) {
        this.idShopGroup = idShopGroup;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }

    public String getSecureKey() {
        return secureKey;
    }

    public void setSecureKey(String secureKey) {
        this.secureKey = secureKey;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRecyclable() {
        return recyclable;
    }

    public void setRecyclable(String recyclable) {
        this.recyclable = recyclable;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getGiftMessage() {
        return giftMessage;
    }

    public void setGiftMessage(String giftMessage) {
        this.giftMessage = giftMessage;
    }

    public String getMobileTheme() {
        return mobileTheme;
    }

    public void setMobileTheme(String mobileTheme) {
        this.mobileTheme = mobileTheme;
    }

    public String getTotalDiscounts() {
        return totalDiscounts;
    }

    public void setTotalDiscounts(String totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    public String getTotalDiscountsTaxIncl() {
        return totalDiscountsTaxIncl;
    }

    public void setTotalDiscountsTaxIncl(String totalDiscountsTaxIncl) {
        this.totalDiscountsTaxIncl = totalDiscountsTaxIncl;
    }

    public String getTotalDiscountsTaxExcl() {
        return totalDiscountsTaxExcl;
    }

    public void setTotalDiscountsTaxExcl(String totalDiscountsTaxExcl) {
        this.totalDiscountsTaxExcl = totalDiscountsTaxExcl;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getTotalPaidTaxIncl() {
        return totalPaidTaxIncl;
    }

    public void setTotalPaidTaxIncl(String totalPaidTaxIncl) {
        this.totalPaidTaxIncl = totalPaidTaxIncl;
    }

    public String getTotalPaidTaxExcl() {
        return totalPaidTaxExcl;
    }

    public void setTotalPaidTaxExcl(String totalPaidTaxExcl) {
        this.totalPaidTaxExcl = totalPaidTaxExcl;
    }

    public String getTotalPaidReal() {
        return totalPaidReal;
    }

    public void setTotalPaidReal(String totalPaidReal) {
        this.totalPaidReal = totalPaidReal;
    }

    public String getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(String totalProducts) {
        this.totalProducts = totalProducts;
    }

    public String getTotalProductsWt() {
        return totalProductsWt;
    }

    public void setTotalProductsWt(String totalProductsWt) {
        this.totalProductsWt = totalProductsWt;
    }

    public String getTotalShipping() {
        return totalShipping;
    }

    public void setTotalShipping(String totalShipping) {
        this.totalShipping = totalShipping;
    }

    public String getTotalShippingTaxIncl() {
        return totalShippingTaxIncl;
    }

    public void setTotalShippingTaxIncl(String totalShippingTaxIncl) {
        this.totalShippingTaxIncl = totalShippingTaxIncl;
    }

    public String getTotalShippingTaxExcl() {
        return totalShippingTaxExcl;
    }

    public void setTotalShippingTaxExcl(String totalShippingTaxExcl) {
        this.totalShippingTaxExcl = totalShippingTaxExcl;
    }

    public String getCarrierTaxRate() {
        return carrierTaxRate;
    }

    public void setCarrierTaxRate(String carrierTaxRate) {
        this.carrierTaxRate = carrierTaxRate;
    }

    public String getTotalWrapping() {
        return totalWrapping;
    }

    public void setTotalWrapping(String totalWrapping) {
        this.totalWrapping = totalWrapping;
    }

    public String getTotalWrappingTaxIncl() {
        return totalWrappingTaxIncl;
    }

    public void setTotalWrappingTaxIncl(String totalWrappingTaxIncl) {
        this.totalWrappingTaxIncl = totalWrappingTaxIncl;
    }

    public String getTotalWrappingTaxExcl() {
        return totalWrappingTaxExcl;
    }

    public void setTotalWrappingTaxExcl(String totalWrappingTaxExcl) {
        this.totalWrappingTaxExcl = totalWrappingTaxExcl;
    }

    public String getRoundMode() {
        return roundMode;
    }

    public void setRoundMode(String roundMode) {
        this.roundMode = roundMode;
    }

    public String getRoundType() {
        return roundType;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }

    public String getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(String conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public GetOrder_Association getAssociations() {
        return associations;
    }

    public void setAssociations(GetOrder_Association associations) {
        this.associations = associations;
    }
}
