package com.varshaaweblabs.ecommerce.Base;

import android.support.annotation.NonNull;

/**
 * Created by root on 17/4/17.
 */

public abstract class BasePresenter<V extends BaseView> {

    protected V mView;


    public BasePresenter() {

    }
    public final void attachView(@NonNull V view) {
        mView = view;

    }


    public V getView() {
        return mView;
    }



    public final void detachView() {
        mView = null;

    }

    /**
     * Check if the view is attached.
     * This checking is only necessary when returning from an asynchronous call
     */
    protected final boolean isViewAttached() {
        return mView != null;
    }



}