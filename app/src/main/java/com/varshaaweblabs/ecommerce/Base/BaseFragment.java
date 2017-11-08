package com.varshaaweblabs.ecommerce.Base;

/**
 * Created by root on 17/4/17.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements BaseView {

    private BaseActivity activity;
    private MyProgressDialog mProgressDialog;

    public void Init() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new MyProgressDialog(activity);
        Init();
        setHasOptionsMenu(false);

    }



    @Override
    public void showProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.ShowDialog(null);
        } else {
            showToast("showProgress is null");
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.hideDialog();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.activity = (BaseActivity) context;

        }
    }



    public BaseActivity getBaseActivity() {
        return activity;
    }

    @Override
    public void onDetach() {
        activity = null;
        super.onDetach();
    }

    @Override
    public String getStringById(int id) {
        return getString(id);
    }

    @Override
    public String getStringById(int id, String param1) {
           return activity.getStringById(id,param1);
    }

    @Override
    public String getStringById(int id, String param1, String param2) {return activity.getStringById(id,param1,param2);
    }


    @Override
    public void showToast(String errorMessage) {
        if (activity != null) {
            activity.showToast(errorMessage);
        }
    }
}
