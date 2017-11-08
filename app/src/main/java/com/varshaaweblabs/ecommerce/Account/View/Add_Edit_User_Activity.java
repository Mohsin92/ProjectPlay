package com.varshaaweblabs.ecommerce.Account.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Account.Presenter.AddEditUserPresenter;
import com.varshaaweblabs.ecommerce.Account.Presenter.IAdd_Edit_User_Interface;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Cart.View.AddToCartActivity;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.MainActivity;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginCustomer_Data;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by dinesh on 25/10/17.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class Add_Edit_User_Activity extends BaseActivity implements View.OnClickListener, IAdd_Edit_User_Interface, MyAccountView.View {

    TextView tv_user_birthday;
    RadioGroup rg_gender;
    Button btn_save_user;
    Boolean signup_flag = true, guest_flag = true;
    ;
    EditText ed_user_firstname, ed__user_lastname, ed_user_email, ed_user_old_pass, ed_user_new_pass;
    TextView tv_new_pass, tv_old_pass;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor;
    RadioButton rd_male, rd_female;
    CheckBox chk_receive_partner, chk_signup_newsletter;
    String chk_receive_partner_id, chk_signup_newsletter_id;
    Calendar myCalendar = Calendar.getInstance();
    AddEditUserPresenter adduserPresnter;
    LoginCustomer_Data cust_data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account);


        Intent signup = getIntent();
        signup_flag = signup.getBooleanExtra("signup_flag", true);
        guest_flag = signup.getBooleanExtra("guest_flag", true);

        pref_login = Add_Edit_User_Activity.this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor = pref_login.edit();

        adduserPresnter = new AddEditUserPresenter(this, this);
        adduserPresnter.attachView(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_user_birthday = (TextView) findViewById(R.id.tv_user_birthday);
        rg_gender = (RadioGroup) findViewById(R.id.radioGroup1);
        btn_save_user = (Button) findViewById(R.id.btn_save_user);
        ed_user_firstname = (EditText) findViewById(R.id.ed_user_firstname);
        ed__user_lastname = (EditText) findViewById(R.id.ed__user_lastname);
        ed_user_email = (EditText) findViewById(R.id.ed_user_email);
        ed_user_old_pass = (EditText) findViewById(R.id.ed_user_old_pass);
        ed_user_new_pass = (EditText) findViewById(R.id.ed_user_new_pass);
        tv_new_pass = (TextView) findViewById(R.id.tv_new_pass);
        tv_old_pass = (TextView) findViewById(R.id.tv_old_pass);
        rd_male = (RadioButton) findViewById(R.id.rd_male);
        rd_female = (RadioButton) findViewById(R.id.rd_female);
        chk_receive_partner = (CheckBox) findViewById(R.id.chk_receive_partner);
        chk_signup_newsletter = (CheckBox) findViewById(R.id.chk_signup_newsletter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //TODO signup_flag true for Create Account
        //TODO signup_flag false for Edit Account
        if (signup_flag) {
            getSupportActionBar().setTitle("Create an account");
            tv_old_pass.setVisibility(View.GONE);
            ed_user_old_pass.setVisibility(View.GONE);
            tv_new_pass.setText("Password");
            ed_user_new_pass.setHint("Password");
        } else {
            getSupportActionBar().setTitle("Edit account");
            setData();
        }


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        tv_user_birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Add_Edit_User_Activity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn_save_user.setOnClickListener(this);

        chk_receive_partner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_receive_partner.isChecked()) {
                    chk_receive_partner_id = "1";
                } else {
                    chk_receive_partner_id = "0";
                }
            }
        });

        chk_signup_newsletter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_signup_newsletter.isChecked()) {
                    chk_signup_newsletter_id = "1";
                } else {
                    chk_signup_newsletter_id = "0";
                }
            }
        });


    }

    private void setData() {
        Gson gson = new Gson();
        cust_data = gson.fromJson(pref_login.getString(Pref_Data.LOGIN_RESPONSE, ""), LoginCustomer_Data.class);
        tv_new_pass.setVisibility(View.VISIBLE);
        ed_user_new_pass.setVisibility(View.VISIBLE);
        String id_gender = cust_data.getIdGender();
        String customer_id = cust_data.getId();
        String id_newsletter = cust_data.getNewsletter();
        String id_otion = cust_data.getOptin();
        ed_user_firstname.setText(cust_data.getFirstname());
        ed__user_lastname.setText(cust_data.getLastname());
        ed_user_email.setText(cust_data.getEmail());
        tv_user_birthday.setText(cust_data.getBirthday());
        if (id_gender.equalsIgnoreCase("1")) {
            rg_gender.check(R.id.rd_male);
        } else {
            rg_gender.check(R.id.rd_female);
        }
        if (id_newsletter.equalsIgnoreCase("1")) {
            chk_signup_newsletter.setChecked(true);
        } else {
            chk_signup_newsletter.setChecked(false);
        }
        if (id_otion.equalsIgnoreCase("1")) {
            chk_receive_partner.setChecked(true);
        } else {
            chk_receive_partner.setChecked(false);
        }

    }


    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tv_user_birthday.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_user:
                String link, data, id_option, id_news;
                int selected = rg_gender.getCheckedRadioButtonId();
                String id_gender;
                RadioButton gender = (RadioButton) findViewById(selected);
                if (gender.getText().toString().equalsIgnoreCase("Male")) {
                    id_gender = "1";
                } else {
                    id_gender = "2";
                }
                if (chk_signup_newsletter.isChecked()) {
                    id_news = "1";
                } else {
                    id_news = "0";
                }
                if (chk_receive_partner.isChecked()) {
                    id_option = "1";
                } else {
                    id_option = "0";
                }

                String firstname = ed_user_firstname.getText().toString();
                String lastname = ed__user_lastname.getText().toString();
                String email = ed_user_email.getText().toString();
                String old_passwd = ed_user_old_pass.getText().toString();
                String new_passwd = ed_user_new_pass.getText().toString();
                String birth_day = tv_user_birthday.getText().toString();

                if (TextUtils.isEmpty(new_passwd) || new_passwd.length() < 5) {
                    ed_user_new_pass.setError("You must have 5 characters in your password");
                    return;
                }
                if (signup_flag) {
                    link = "account/create-account?output_format=JSON&display=full";
                    data = "<prestashop><customer><id_gender>" + id_gender + "</id_gender><firstname>" + firstname + "</firstname><lastname>" + lastname + "</lastname><email>" + email + "</email><passwd>" + new_passwd + "</passwd><birthday>" + birth_day + "</birthday><optin>" + id_option + "</optin><newsletter>" + id_news + "</newsletter></customer></prestashop>";

                } else {
                    String customer_id = cust_data.getId();
                    link = "account/edit-account?output_format=JSON&display=full";
                    data = "<prestashop><customer> <id>" + customer_id + "</id><id_gender>" + id_gender + "</id_gender><firstname>" + firstname + "</firstname><lastname>" + lastname + "</lastname><email>" + email + "</email><passwd>" + old_passwd + "</passwd><newpasswd>" + new_passwd + "</newpasswd><birthday>" + birth_day + "</birthday><optin>" + id_option + "</optin><newsletter>" + id_news + "</newsletter></customer></prestashop>";
                }
                try {
                    Log.e("Link", link);
                    Log.e("Data", data);
                    adduserPresnter.createAccount(data, link);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

    @Override
    public void Create_Account(final LoginCustomer_Data customer_data) {

        Add_Edit_User_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (customer_data.getError_msg() != null) {
                    Toast.makeText(Add_Edit_User_Activity.this, customer_data.getError_msg().toString(), Toast.LENGTH_LONG).show();

                } else {
                    editor.putString(Constants.USER_ID, customer_data.getId().toString()    );
                    editor.commit();
                    //TODO guest_flag true for Guest User
                    //TODO guest_flag false for User
                    if (guest_flag) {
                        Intent cart = new Intent(Add_Edit_User_Activity.this, AddToCartActivity.class);
                        cart.putExtra("cart_intent_flag", true);
                        startActivity(cart);
                    } else {
                        Intent main_activity = new Intent(Add_Edit_User_Activity.this, MainActivity.class);
                        startActivity(main_activity);
                    }
                }
            }
        });
    }
}

