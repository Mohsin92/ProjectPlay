package com.varshaaweblabs.ecommerce.ProductFullDetails.Model;

/**
 * Created by root on 2/10/17.
 */

public class ColorItem implements SingleChoiceItem {
    private int id;
    private int color;
    private boolean isSelected = false;

    public ColorItem(int id, int color) {
        this.id = id;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }
}
