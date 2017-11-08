package com.varshaaweblabs.ecommerce.Payment.View;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;
import com.google.gson.Gson;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Added;
import com.varshaaweblabs.ecommerce.Payment.Adapter.Payment_Option_Adapter;
import com.varshaaweblabs.ecommerce.Payment.Model.OrderCheckOut_Resp;
import com.varshaaweblabs.ecommerce.Payment.Model.PaymentModule_Data;
import com.varshaaweblabs.ecommerce.Payment.Model.PaymentModule__Resp;
import com.varshaaweblabs.ecommerce.Payment.Presenter.IPaymentInterface;
import com.varshaaweblabs.ecommerce.Payment.Presenter.PaymentPresenter;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dinesh on 13/10/17.
 */

public class Payment_Activity extends BaseActivity implements IPaymentInterface, IPaymentView.View, View.OnClickListener, OnProgressBarListener {
    SharedPreferences pref_login;
    Cart cart_prod_data;
    TextView tv_cart_subtotal, tv_cart_subtotal_value, tv_cart_tax_value, tv_cart_shipping_value, tv_cart_total, tv_cart_coupon, tv_cart_coupon_value;
    LinearLayout ll_coupon_details;
    ImageView coupon_delete;
    String coupon_id;
    Button btn_place_order;
    String payamount;
    Cart_Coupon_Added coupon_list = new Cart_Coupon_Added();
    PaymentPresenter payment_presenter;
    ArrayList<PaymentModule_Data> pay_data_list = new ArrayList<>();
    Payment_Option_Adapter payment_option_adapter;
    ListView lv_radio_pay_option;
    private Timer timer;
    ImageView iv_order_pay;
    private NumberProgressBar bnp;

    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;


    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Constants.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        payment_presenter = new PaymentPresenter(this, this);
        payment_presenter.attachView(this);

        Intent intent = new Intent(this, PayPalService.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        startService(intent);

        pref_login = Payment_Activity.this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        tv_cart_subtotal = (TextView) findViewById(R.id.tv_cart_subtotal);
        tv_cart_subtotal_value = (TextView) findViewById(R.id.tv_cart_subtotal_value);
        tv_cart_tax_value = (TextView) findViewById(R.id.tv_cart_tax_value);
        tv_cart_shipping_value = (TextView) findViewById(R.id.tv_cart_shipping_value);
        tv_cart_total = (TextView) findViewById(R.id.tv_cart_total);
        tv_cart_coupon = (TextView) findViewById(R.id.tv_cart_coupon);
        tv_cart_coupon_value = (TextView) findViewById(R.id.tv_cart_coupon_value);
        ll_coupon_details = (LinearLayout) findViewById(R.id.ll_coupon_details);
        coupon_delete = (ImageView) findViewById(R.id.coupon_delete);
        lv_radio_pay_option = (ListView) findViewById(R.id.lv_radio_pay_option);
        btn_place_order = (Button) findViewById(R.id.btn_place_order);
        iv_order_pay = (ImageView) findViewById(R.id.iv_order_pay);

        Intent payment = getIntent();
        coupon_list = (Cart_Coupon_Added) payment.getSerializableExtra("coupon_data1");


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payment");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btn_place_order.setOnClickListener(this);


        Gson gson = new Gson();
        cart_prod_data = gson.fromJson(pref_login.getString(Pref_Data.CART_DATA, ""), Cart.class);
        setData();


        try {

            payment_presenter.getPaymentModule();
            payment_presenter.getToken();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exceptions: ", e.toString());
        }


        bnp = (NumberProgressBar) findViewById(R.id.numberbar2);
        bnp.setOnProgressBarListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(5);
                        bnp.setSuffix(" ");
                        bnp.setReachedBarColor(ContextCompat.getColor(Payment_Activity.this, (R.color.grey)));
//                        bnp.setProgressTextVisibility(NumberProgressBar.ProgressTextVisibility.Invisible);
                        bnp.setProgressTextSize(0);
                        bnp.setReachedBarHeight(5);

                    }
                });
            }
        }, 1000, 100);

    }

    private void setData() {

        tv_cart_subtotal_value.setText("$" + cart_prod_data.getSubtotals().getProducts().getValue());
        tv_cart_tax_value.setText("$" + cart_prod_data.getSubtotals().getTax().getValue());
        tv_cart_shipping_value.setText("$" + cart_prod_data.getSubtotals().getShipping().getValue());
        tv_cart_total.setText("$" + cart_prod_data.getTotals().getTotal().getValue());
        payamount = cart_prod_data.getTotals().getTotal().getValue();
        if (coupon_list != null) {
            ll_coupon_details.setVisibility(View.VISIBLE);
            tv_cart_coupon.setText(coupon_list.getName());
            tv_cart_coupon_value.setText("- $ " + coupon_list.getReductionAmount());
            coupon_id = coupon_list.getIdCartRule();
            coupon_delete.setVisibility(View.GONE);
        } else {
            ll_coupon_details.setVisibility(View.GONE);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_place_order:

                if (Constants.PAY_ID.equalsIgnoreCase("1")) {
                    payment_presenter.checkOutAPI();  // For Cash On Delivery Option
                } else {
                    getPayment();  // For PAYPAL Payment
                }

                break;
        }
    }

    //Response of Get Payment Module Call
    @Override
    public void PaymentOption(PaymentModule__Resp payment_option) {

        Payment_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                pay_data_list.clear();
                PaymentModule__Resp pay_data = gson.fromJson(pref_login.getString(Pref_Data.PAYMENT_MODULE, ""), PaymentModule__Resp.class);
                for (int i = 0; i < pay_data.getPaymentModule().size(); i++) {
                    pay_data_list.add(pay_data.getPaymentModule().get(i));
                    payment_option_adapter = new Payment_Option_Adapter(Payment_Activity.this, pay_data_list, coupon_list);
                    Toast.makeText(Payment_Activity.this, pay_data.getPaymentModule().get(i).getPayment().toString(), Toast.LENGTH_SHORT).show();
                    lv_radio_pay_option.setAdapter(payment_option_adapter);

                }
            }
        });
    }

    @Override
    public void checkOut(final OrderCheckOut_Resp checkout) {
        Payment_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (checkout != null) {
                    Toast.makeText(Payment_Activity.this, "Order_id" + checkout.getIdOrder().toString(), Toast.LENGTH_SHORT).show();
                    Intent success_payment = new Intent(Payment_Activity.this, Activity_Congratulations.class);
                    startActivity(success_payment);
                } else {
                    Toast.makeText(Payment_Activity.this, "Error" + checkout.getIdOrder().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void Paypal_Tranasaction() {
        Payment_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                payment_presenter.checkOutAPI();
            }
        });
    }


    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private void getPayment() {
        //Getting the amount from editText
//        payamount = tv_cart_total.getText().toString();

        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(payamount)), "USD", "Shopping Total",
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(this, PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().getJSONObject("response").getString("id");
                        Log.i("paymentExample", paymentDetails);

                        //Starting a new activity for the payment details and also putting the payment details with intent

                        payment_presenter.getTransactionId(paymentDetails);

                        startActivity(new Intent(this, Activity_Congratulations.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", payamount));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }


    @Override
    public void onProgressChange(int current, int max) {
        if (current == max) {

//            bnp.setProgress(0);
            iv_order_pay.setImageResource(R.mipmap.payment_checkout_fill);
        }
    }
}
