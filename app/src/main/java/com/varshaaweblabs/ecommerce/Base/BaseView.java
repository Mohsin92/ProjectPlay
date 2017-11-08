package com.varshaaweblabs.ecommerce.Base;

/**
 * Created by root on 17/4/17.
 */
public interface BaseView {

    void showProgress();
    void hideProgress();
    void showToast(String errorMessage);

    String getStringById(int id);
    String getStringById(int id, String param1);
    String getStringById(int id, String param1, String param2);

}