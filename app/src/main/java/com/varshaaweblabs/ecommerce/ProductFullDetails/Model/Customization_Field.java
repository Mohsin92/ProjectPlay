package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dinesh on 2/10/17.
 */

public class Customization_Field implements Serializable {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("id_customization_field")
    @Expose
    private String idCustomizationField;
    @SerializedName("required")
    @Expose
    private String required;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("input_name")
    @Expose
    private String inputName;
    @SerializedName("is_customized")
    @Expose
    private Boolean isCustomized;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIdCustomizationField() {
        return idCustomizationField;
    }

    public void setIdCustomizationField(String idCustomizationField) {
        this.idCustomizationField = idCustomizationField;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public Boolean getCustomized() {
        return isCustomized;
    }

    public void setCustomized(Boolean customized) {
        isCustomized = customized;
    }
}
