package com.varshaaweblabs.ecommerce.Base;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.view.Window;

import com.varshaaweblabs.ecommerce.R;


public class MyProgressDialog {
    public static final String MSG_LOGIN = "Logging in. please wait...";
    public static final String MSG_REGISTER = "Registering. please wait...";
    public static final String MSG_WAIT = "Please wait...";
    private Context mContext;
    private Context fcontext;
    private Dialog mProgressDialog;




    public MyProgressDialog() {

    }

    public MyProgressDialog(Context context) {
        mContext = context;
        mProgressDialog = new Dialog(mContext);

    }





    public void ShowDialog(@Nullable String Msg) {

        if (mProgressDialog == null) {
            mProgressDialog = new Dialog(mContext);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setCancelable(true);
        }
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog.setCancelable(false);

        mProgressDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setContentView(R.layout.progress_bar_custom);
        mProgressDialog.show();
    }

    public void hideDialog() {
        if (mProgressDialog != null) {

            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();
        }

    }
}
