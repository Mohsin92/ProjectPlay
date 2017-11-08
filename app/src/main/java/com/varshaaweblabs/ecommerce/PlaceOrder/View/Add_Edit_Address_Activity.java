package com.varshaaweblabs.ecommerce.PlaceOrder.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Added;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.Country_List_resp;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.Country_Model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.State_List_Resp;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.State_Model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_List;
import com.varshaaweblabs.ecommerce.PlaceOrder.Model.User_Address_model;
import com.varshaaweblabs.ecommerce.PlaceOrder.Presenter.Add_Edit_AddressPresenter;
import com.varshaaweblabs.ecommerce.PlaceOrder.Presenter.IAdd_Edit_AddressInterface;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dinesh on 14/10/17.
 */

public class Add_Edit_Address_Activity extends BaseActivity implements IAdd_Edit_AddressInterface, OrderSummeryView.View, View.OnClickListener {

    EditText ed_firstname, ed_lastname, ed_email, ed_phone_no, ed_add, ed_add2, ed_city, ed_zip;
    Spinner sp_state, sp_country;
    CheckBox check_bill;
    String country = "", states = "";
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;
    ArrayList<Country_List_resp> country_list = new ArrayList<>();
    ArrayList<State_List_Resp> state_list = new ArrayList<>();
    ArrayList<Country_List_resp> country_list_resp = new ArrayList<>();
    ArrayList<State_List_Resp> state_list_resp = new ArrayList<>();
    Country_Model country_model;
    State_Model state_model;
    Add_Edit_AddressPresenter add_edit_addressPresenter;
    String[] spinnerArray_country;
    String[] spinnerArray_state;
    ArrayList<HashMap<String, String>> dommap_country = new ArrayList();
    ArrayList<HashMap<String, String>> dommap_state = new ArrayList();
    String id_country = "", id_state = "";
    Button btn_save_address;
    Cart_Coupon_Added coupon_list = new Cart_Coupon_Added();
    Boolean flag_add = true, order_add_flag = true;
    String add_id = "";
    Gson gson = new Gson();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_address);

        pref_login = this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();

        add_edit_addressPresenter = new Add_Edit_AddressPresenter(Add_Edit_Address_Activity.this, this);
        add_edit_addressPresenter.attachView(this);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ed_firstname = (EditText) findViewById(R.id.ed_firstname);
        ed_lastname = (EditText) findViewById(R.id.ed_lastname);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_phone_no = (EditText) findViewById(R.id.ed_phone_no);
        ed_add = (EditText) findViewById(R.id.ed_add);
        ed_add2 = (EditText) findViewById(R.id.ed_add_2);
        ed_city = (EditText) findViewById(R.id.ed_city);
        ed_zip = (EditText) findViewById(R.id.ed_zip);
        sp_state = (Spinner) findViewById(R.id.sp_state);
        sp_country = (Spinner) findViewById(R.id.sp_country);
        btn_save_address = (Button) findViewById(R.id.btn_save_address);

        btn_save_address.setOnClickListener(this);


        Intent add_address = getIntent();
        coupon_list = (Cart_Coupon_Added) add_address.getSerializableExtra("coupon_data");
        flag_add = add_address.getBooleanExtra("address_flag", true);
        order_add_flag = add_address.getBooleanExtra("order_add_flag", true);

        country = pref_login.getString(Pref_Data.COUNTRIES, "");
        states = pref_login.getString(Pref_Data.STATES, "");

        //TODO Flag True for Add new Address
        //TODO Flag False for Edit Address

        if (flag_add) {
            getSupportActionBar().setTitle("Add New Address");
            getStateCountry();

        } else {
            getSupportActionBar().setTitle("Edit Address");
            User_Address_List add_data = (User_Address_List) add_address.getSerializableExtra("selected_add");
            getStateCountry();
            editAddressData(add_data);
        }

    }

    private void editAddressData(User_Address_List add_data) {
        btn_save_address.setText("EDIT");
        ed_firstname.setText(add_data.getFirstname());
        ed_lastname.setText(add_data.getLastname());
        ed_add.setText(add_data.getAddress1());
        ed_add2.setText(add_data.getAddress2());
        ed_city.setText(add_data.getCity());
        ed_zip.setText(add_data.getPostcode());
        ed_phone_no.setText(add_data.getPhoneMobile());
        String state_id = add_data.getIdState();
        String country_id = add_data.getIdCountry();
        add_id = add_data.getId().toString();


        for (int i = 0; i < state_list.size(); i++) {
            if (state_id.equalsIgnoreCase(state_list.get(i).getId().toString())) {
                sp_state.setSelection(i);
                break;
            }

        }

        for (int i = 0; i < country_list.size(); i++) {
            if (country_id.equalsIgnoreCase(country_list.get(i).getId().toString())) {
                sp_country.setSelection(i);
                break;
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_save_address:
                editor_login.putString(Pref_Data.ADDRESS_DATA, "");
                editor_login.commit();
                String first_name = ed_firstname.getText().toString();
                String last_name = ed_lastname.getText().toString();
                String add1 = ed_add.getText().toString();
                String add2 = ed_add2.getText().toString();
                String postcode = ed_zip.getText().toString();
                String phone_mobile = ed_phone_no.getText().toString();
                String city = ed_city.getText().toString();
                String user_id = pref_login.getString(Constants.USER_ID, "");
                if (add_id.equalsIgnoreCase("")) {
                    String url = "<prestashop><address><id_customer>" + user_id + "</id_customer><id_country>" + id_country + "</id_country><id_state>" + id_state + "</id_state><alias>My Address</alias><firstname>" + first_name + "</firstname><lastname>" + last_name + "</lastname><address1>" + add1 + "</address1><address2>" + add2 + "</address2><postcode>" + postcode + "</postcode><city>" + city + "</city><company>test</company><phone>1235465</phone><phone_mobile>" + phone_mobile + "</phone_mobile><deleted>0</deleted></address></prestashop>";
                    add_edit_addressPresenter.addAddress(url);
                } else {
                    String url = "<prestashop><address> <id>" + add_id + "</id><id_customer>" + user_id + "</id_customer><id_country>" + id_country + "</id_country><id_state>" + id_state + "</id_state><alias>My Address</alias><firstname>" + first_name + "</firstname><lastname>" + last_name + "</lastname><address1>" + add1 + "</address1><address2>" + add2 + "</address2><postcode>" + postcode + "</postcode><city>" + city + "</city><company>test</company><phone>1235465</phone><phone_mobile>" + phone_mobile + "</phone_mobile><deleted>0</deleted></address></prestashop>";
                    add_edit_addressPresenter.editAddress(url);
                }


        }
    }

    public void getStateCountry() {
        if (country.equalsIgnoreCase("") || states.equalsIgnoreCase("")) {
            try {
                add_edit_addressPresenter.getAllCountry();
                add_edit_addressPresenter.getAllStates();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            state_list_resp.clear();
            country_list_resp.clear();


            country_model = gson.fromJson(pref_login.getString(Pref_Data.COUNTRIES, ""), Country_Model.class);
            state_model = gson.fromJson(pref_login.getString(Pref_Data.STATES, ""), State_Model.class);


            for (int i = 0; i < state_model.getStates().size(); i++) {
                state_list_resp.add(state_model.getStates().get(i));
            }

            for (int i = 0; i < country_model.getCountries().size(); i++) {
                country_list_resp.add(country_model.getCountries().get(i));
            }

            for (int i = 0; i < state_list_resp.size(); i++) {
                state_list.add(state_list_resp.get(i));
            }
            for (int i = 0; i < country_list_resp.size(); i++) {
                country_list.add(country_list_resp.get(i));
            }
            getStateSpinner(state_list);
            getCountrySpinner(country_list);
        }
    }

    @Override
    public void getCountry(Country_Model country_resp) {
        country_list.clear();
        for (int i = 0; i < country_resp.getCountries().size(); i++) {
            country_list.add(country_resp.getCountries().get(i));
        }
        Add_Edit_Address_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getCountrySpinner(country_list);


            }
        });
    }


    @Override
    public void getState(State_Model state_resp) {
        state_list.clear();
        for (int i = 0; i < state_resp.getStates().size(); i++) {
            state_list.add(state_resp.getStates().get(i));
        }
        Add_Edit_Address_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                getStateSpinner(state_list);


            }
        });
    }

    private void getStateSpinner(ArrayList<State_List_Resp> state_list) {

        List<String> state_name = new ArrayList<>();
        final List<Integer> state_id = new ArrayList<>();
        state_id.clear();
        state_name.clear();

        for (int i = 0; i < state_list.size(); i++) {
            state_id.add(state_list.get(i).getId());
            state_name.add(state_list.get(i).getName());
        }
        spinnerArray_state = new String[state_list.size()];
        dommap_state.clear();
        for (int i = 0; i < state_list.size(); i++) {
            spinnerArray_state[i] = String.valueOf(state_name.get(i));

            HashMap<String, String> map = new HashMap<String, String>();
            map.put(Constants.STATE_ID, String.valueOf(state_id.get(i)));
            map.put(Constants.STATENAME, spinnerArray_state[i]);
            dommap_state.add(map);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Add_Edit_Address_Activity.this, android.R.layout.simple_spinner_item, spinnerArray_state);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_state.setAdapter(adapter);
        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerArray_state.length > 0) {
                    id_state = dommap_state.get(i).get(Constants.STATE_ID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });
    }

    private void getCountrySpinner(final ArrayList<Country_List_resp> country_list1) {

        List<String> country_name = new ArrayList<>();
        final List<Integer> country_id = new ArrayList<>();
        country_name.clear();
        country_id.clear();

        for (int i = 0; i < country_list1.size(); i++) {
            country_id.add(country_list1.get(i).getId());
            country_name.add(country_list1.get(i).getName());
        }
        spinnerArray_country = new String[country_name.size()];
        dommap_country.clear();
        for (int i = 0; i < country_name.size(); i++) {
            spinnerArray_country[i] = String.valueOf(country_name.get(i));

            HashMap<String, String> map = new HashMap<String, String>();
            map.put(Constants.COUNTRY_ID, String.valueOf(country_id.get(i)));
            map.put(Constants.COUNTRYNAME, spinnerArray_country[i]);
            dommap_country.add(map);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Add_Edit_Address_Activity.this, android.R.layout.simple_spinner_item, spinnerArray_country);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_country.setAdapter(adapter);

        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerArray_country.length > 0) {
                    id_country = dommap_country.get(i).get(Constants.COUNTRY_ID);
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });
    }

    @Override
    public void addAddress(final User_Address_model add_address_list) {
        Add_Edit_Address_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                add_id = add_address_list.getAddresses().get(0).getId().toString();
                editor_login.putString(Constants.ADDRESS_ID, add_id);
                editor_login.commit();
                //TODO Flag for Navigation
                if (order_add_flag) {
                    Intent order = new Intent(Add_Edit_Address_Activity.this, Activity_Order_Summery.class);
                    order.putExtra("order_flag", true);
                    order.putExtra("coupon_data", coupon_list);
                    startActivity(order);
                } else {
                    Intent address = new Intent(Add_Edit_Address_Activity.this, Show_Address_Activity.class);
                    address.putExtra("show_address_flag", false);
                    address.putExtra("coupon_data", coupon_list);
                    startActivity(address);
                }



            }

        });
    }


    @Override
    public void editAddress(final User_Address_model edit_address_list) {
        Add_Edit_Address_Activity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                add_id = edit_address_list.getAddresses().get(0).getId().toString();
                editor_login.putString(Constants.ADDRESS_ID, add_id);
                editor_login.commit();
                Intent address = new Intent(Add_Edit_Address_Activity.this, Show_Address_Activity.class);
                address.putExtra("show_address_flag", false);
                address.putExtra("coupon_data", coupon_list);
                startActivity(address);


            }

        });
    }
}
