package com.varshaaweblabs.ecommerce.Account.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.varshaaweblabs.ecommerce.Account.Presenter.ForgotPasswordPresenter;
import com.varshaaweblabs.ecommerce.Account.Presenter.IForgotPasswordInterface;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.MainActivity;
import com.varshaaweblabs.ecommerce.R;

import java.io.IOException;

/**
 * Created by dinesh on 26/10/17.
 */

public class Forgot_Password_Activity extends BaseActivity implements View.OnClickListener, IForgotPasswordInterface, MyAccountView.View {
    AnimationDrawable animationDrawable;
    LinearLayout ll_forgot_gradient_layout;
    ImageView iv_close;
    Button btn_send_link;
    EditText ed_forgot_email;
    ForgotPasswordPresenter forget_pass_presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);
        forget_pass_presenter = new ForgotPasswordPresenter(this, this);
        forget_pass_presenter.attachView(this);


        ll_forgot_gradient_layout = (LinearLayout) findViewById(R.id.ll_forgot_gradient_layout);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        btn_send_link = (Button) findViewById(R.id.btn_send_link);
        ed_forgot_email = (EditText) findViewById(R.id.ed_forgot_email);

        iv_close.setOnClickListener(this);
        btn_send_link.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_close:
                finish();
                break;

            case R.id.btn_send_link:
                String email = ed_forgot_email.getText().toString();
                String data = "<prestashop><customer><email>" + email + "</email></customer></prestashop>";

                try {
                    forget_pass_presenter.forgotPassword(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

    @Override
    public void forgetpass(final String msg) {
        Forgot_Password_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Forgot_Password_Activity.this);
                alertDialogBuilder.setMessage(msg);
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent main = new Intent(Forgot_Password_Activity.this, MainActivity.class);
                                startActivity(main);
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}
