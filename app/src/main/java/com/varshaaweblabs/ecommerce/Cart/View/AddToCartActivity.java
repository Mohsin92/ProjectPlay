package com.varshaaweblabs.ecommerce.Cart.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Cart.Adapter.AddToCart_Adapter;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Added;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Error;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Product;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;
import com.varshaaweblabs.ecommerce.Cart.Presenter.AddToCartPresenter;
import com.varshaaweblabs.ecommerce.Cart.Presenter.ICartInterface;
import com.varshaaweblabs.ecommerce.Filter.View.FilterProductList;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.MainActivity;
import com.varshaaweblabs.ecommerce.LoginActivity.View.LoginActivity;
import com.varshaaweblabs.ecommerce.PlaceOrder.View.Activity_Order_Summery;
import com.varshaaweblabs.ecommerce.PlaceOrder.View.Add_Edit_Address_Activity;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.NonScrollListView;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddToCartActivity extends BaseActivity implements ICartInterface, AddToCartView.View, View.OnClickListener {
    AddToCartPresenter addToCartPresenter;
    NonScrollListView prod_cat_list;
    EditText ed_cart_coupon;
    TextView tv_cart_apply, tv_cart_subtotal, tv_cart_subtotal_value, tv_cart_tax_value, tv_cart_shipping_value, tv_cart_total, tv_cart_coupon, tv_cart_coupon_value;
    List<Cart_Product> product;
    ArrayList<String> cart_coupon_list = new ArrayList<>();
    ImageView coupon_delete;
    AddToCart_Adapter addToCart_adapter;
    private Boolean cart_flag = false;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;
    String cart_id = "", cart_id1 = "", cart_count = "", customization_id = "", user_id = "";
    LinearLayout ll_cart_bottom, ll_include_layout, ll_empty_layout;
    Button btn_shopping, btn_checkout, btn_cont;
    Boolean cart_intent_flag, login_intent_flag;
    LinearLayout ll_coupon_details, ll_content;
    String coupon_id;
    Cart_Product cart_prod = new Cart_Product();
    Cart_Coupon_Added coupon_data_list;
    ImageView iv_edit_coupon_clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addToCartPresenter = new AddToCartPresenter(this, this, this);
        addToCartPresenter.attachView(this);


        Intent cart = getIntent();
        cart_intent_flag = cart.getBooleanExtra("cart_intent_flag", true);
        login_intent_flag = cart.getBooleanExtra("login_intent_flag", true);
        cart_id1 = pref_login.getString(Constants.CART_ID, "");
        if (cart_id1.equalsIgnoreCase("null")) {
            cart_id1 = "";
        }
        Log.e("cart_id", cart_id1);
        user_id = pref_login.getString(Constants.USER_ID, "");
        customization_id = pref_login.getString(Constants.CUSTOMIZATION_ID, "");

        //TODO Flag for empty Cart or not

        if (cart_intent_flag) {
            String cart_count = pref_login.getString(Constants.CART_COUNT, "");
            if (cart_count.equalsIgnoreCase("0")) {
                ll_empty_layout.setVisibility(View.VISIBLE);
                btn_cont.setVisibility(View.VISIBLE);
                ll_include_layout.setVisibility(View.GONE);
            } else {
                ll_empty_layout.setVisibility(View.GONE);
                ll_include_layout.setVisibility(View.VISIBLE);
                btn_cont.setVisibility(View.GONE);
                addToCartPresenter.getCartItem(cart_id1, user_id);
            }
        } else {
            try {
                addToCartPresenter.AddTocartAPI(Constants.FULLPRODUCT_ID, "1", Constants.prod_attribute_id, " <operator>up</operator>", cart_id1, customization_id); //TODO check for id_attribute parameter you will get from combinations: 03-10-2017 (Mohsin)
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void Init() {
        super.Init();
        setContentView(R.layout.activity_add_to_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pref_login = this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();

        prod_cat_list = (NonScrollListView) findViewById(R.id.prod_cat_list);
        ed_cart_coupon = (EditText) findViewById(R.id.ed_cart_coupon);
        tv_cart_apply = (TextView) findViewById(R.id.tv_cart_apply);
        tv_cart_subtotal = (TextView) findViewById(R.id.tv_cart_subtotal);
        tv_cart_subtotal_value = (TextView) findViewById(R.id.tv_cart_subtotal_value);
        tv_cart_tax_value = (TextView) findViewById(R.id.tv_cart_tax_value);
        tv_cart_shipping_value = (TextView) findViewById(R.id.tv_cart_shipping_value);
        tv_cart_total = (TextView) findViewById(R.id.tv_cart_total);
        ll_cart_bottom = (LinearLayout) findViewById(R.id.ll_cart_bottom);
        btn_shopping = (Button) findViewById(R.id.btn_shopping);
        coupon_delete = (ImageView) findViewById(R.id.coupon_delete);
        iv_edit_coupon_clear = (ImageView) findViewById(R.id.iv_edit_coupon_clear);
        tv_cart_coupon = (TextView) findViewById(R.id.tv_cart_coupon);
        tv_cart_coupon_value = (TextView) findViewById(R.id.tv_cart_coupon_value);
        ll_coupon_details = (LinearLayout) findViewById(R.id.ll_coupon_details);
        ll_include_layout = (LinearLayout) findViewById(R.id.ll_include_layout);
        ll_empty_layout = (LinearLayout) findViewById(R.id.ll_empty_layout);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        btn_checkout = (Button) findViewById(R.id.btn_checkout);
        btn_cont = (Button) findViewById(R.id.btn_cont);
        product = new ArrayList<>();


        tv_cart_apply.setOnClickListener(this);
        btn_shopping.setOnClickListener(this);
        coupon_delete.setOnClickListener(this);
        btn_checkout.setOnClickListener(this);
        btn_cont.setOnClickListener(this);
        iv_edit_coupon_clear.setOnClickListener(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Flag To Track the navigation
                if (login_intent_flag) {
                    Intent main = new Intent(AddToCartActivity.this, MainActivity.class);
                    startActivity(main);
                } else {
                    finish();
                }

            }
        });
        getSupportActionBar().setTitle("Cart");


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void AddToCart(final Cart_Response cart_response) {
        AddToCartActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                product.clear();
                Constants.order_prod.clear();
                cart_coupon_list.clear();


                if (cart_response.getCart() != null) {
                    ll_content.setVisibility(View.VISIBLE);
                    ll_cart_bottom.setVisibility(View.VISIBLE);
                    cart_id = pref_login.getString(Constants.CART_ID, "");


                    if (cart_id.equalsIgnoreCase("")) {
                        cart_id = cart_response.getIdCart();
                        editor_login.putString(Constants.CART_ID, cart_response.getIdCart());
                        editor_login.commit();
                    } else {
                        Toast.makeText(AddToCartActivity.this, "CartId: " + cart_id, Toast.LENGTH_SHORT).show();

                    }


                    product.addAll(cart_response.getCart().getProducts());
                    Constants.order_prod.addAll(product);


                    for (int j = 0; j < product.size(); j++) {
                        cart_prod = product.get(j);
                    }

                    if (cart_response.getCart().getProducts().size() == 0) {
                        cart_count = pref_login.getString(Constants.CART_COUNT, "");
                    } else {
                        editor_login.putString(Constants.CART_COUNT, String.valueOf(cart_response.getCart().getProducts().size()));
                        editor_login.commit();
                    }
                    addToCart_adapter = new AddToCart_Adapter(AddToCartActivity.this, product, new AddToCart_Adapter.onSpinnerItemHelper() {
                        @Override
                        public void onSpinnerItemSelected(String selectedValue, String product_id, String id_product_attribute) {

                            if (!cart_flag) {
                                Toast.makeText(AddToCartActivity.this, "Selected: " + selectedValue, Toast.LENGTH_SHORT).show();

                                String selected_qty = selectedValue;

                                cartAPIhelper(product, product_id, selected_qty, id_product_attribute);

                            } else {
                                cart_flag = false;
                            }
                        }

                    }, new AddToCart_Adapter.onDeleteItemHelper() {
                        @Override
                        public void onDeleteItemClick(Cart_Product cart_product) {
                            Toast.makeText(AddToCartActivity.this, cart_product.getTotal(), Toast.LENGTH_SHORT).show();
                            addToCartPresenter.removeCart(cart_product, cart_id);
                        }
                    });
                    prod_cat_list.setAdapter(addToCart_adapter);
                    addToCart_adapter.notifyDataSetChanged();
                    tv_cart_subtotal_value.setText("$" + cart_response.getCart().getSubtotals().getProducts().getValue());
                    tv_cart_tax_value.setText("$" + cart_response.getCart().getSubtotals().getTax().getValue());
                    tv_cart_shipping_value.setText("$" + cart_response.getCart().getSubtotals().getShipping().getValue());
                    tv_cart_total.setText("$" + cart_response.getCart().getTotals().getTotal().getValue());

                    if (cart_response.getCart().getVouchers().getAdded().size() > 0) {
                        coupon_data_list = cart_response.getCart().getVouchers().getAdded().get(0);
                        ll_coupon_details.setVisibility(View.VISIBLE);
                        tv_cart_coupon.setText(cart_response.getCart().getVouchers().getAdded().get(0).getName());
                        tv_cart_coupon_value.setText("- $ " + cart_response.getCart().getVouchers().getAdded().get(0).getReductionAmount());
                        coupon_id = cart_response.getCart().getVouchers().getAdded().get(0).getIdCartRule();
                    } else {
                        ll_coupon_details.setVisibility(View.GONE);
                        ed_cart_coupon.setText("");

                    }


                } else {
                    Toast.makeText(AddToCartActivity.this, "Empty Cart", Toast.LENGTH_SHORT).show();
                    addToCart_adapter.notifyDataSetChanged();
                    ll_cart_bottom.setVisibility(View.GONE);

                }

            }
        });
    }

    @Override
    public void removeCart(Cart_Response cart_response) {

    }

    @Override
    public void getCart(Cart_Response cart_response) {

    }

    @Override
    public void AddCoupon(final Cart_Coupon_Error cart_response) {
        AddToCartActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AddToCartActivity.this, cart_response.getError().get(0).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void DeleteCoupon(Cart_Response cart_response) {

    }

    private void cartAPIhelper(List<Cart_Product> product, String product_id, String selected_qty, String id_product_attribute) {

        String operator = null;

        for (int i = 0; i < product.size(); i++) {

            if (product.get(i).getId().equalsIgnoreCase(product_id) && product.get(i).getIdProductAttribute().equalsIgnoreCase(id_product_attribute)) {
                if (Integer.parseInt(selected_qty) < Integer.parseInt(product.get(i).getCartQuantity())) {
                    cart_flag = true;
                    operator = "<operator>down</operator>";
                    int qtys = Integer.parseInt(product.get(i).getCartQuantity()) - Integer.parseInt(selected_qty);
                    selected_qty = String.valueOf(qtys);
                    Log.e("selected Down: ", selected_qty);
                }
                if (Integer.parseInt(selected_qty) > Integer.parseInt(product.get(i).getCartQuantity())) {
                    cart_flag = true;
                    operator = "<operator>up</operator>";
                    int qtys = Integer.parseInt(selected_qty) - Integer.parseInt(product.get(i).getCartQuantity());
                    selected_qty = String.valueOf(qtys);
                    Log.e("selected UP: ", selected_qty);
                }

            }

            if (cart_flag) {
                if (operator != null) {
                    cart_flag = false;
                    try {
                        addToCartPresenter.AddTocartAPI(product_id, selected_qty, id_product_attribute, operator, cart_id, customization_id);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    private String getOperator(String selected_qty, List<Cart_Product> product, String product_id) {
        String operator = "";

        for (int i = 0; i < product.size(); i++) {

            if (product.get(i).getId().equalsIgnoreCase(product_id)) {
                if (Integer.parseInt(selected_qty) < Integer.parseInt(product.get(i).getCartQuantity())) {
                    return operator = "<operator>down</operator>";
                } else {
                    return operator = "<operator>up</operator>";
                }
            }

        }
        Log.e("Qty Update Operator: ", operator);
        return operator;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cart_apply:
                String coupon_data = ed_cart_coupon.getText().toString();
                addToCartPresenter.AddCoupon(coupon_data);
                break;
            case R.id.btn_shopping:
                Intent prod_details = new Intent(AddToCartActivity.this, FilterProductList.class);
                prod_details.putExtra("flag", true);
                startActivity(prod_details);
                break;
            case R.id.coupon_delete:
                addToCartPresenter.RemoveCoupon(coupon_id);
                break;
            case R.id.btn_checkout:
                String user_id = pref_login.getString(Constants.USER_ID, "");
                String add_id_1 = pref_login.getString(Constants.ADDRESS_ID, "");
                if (user_id.equalsIgnoreCase("")) {
                    Intent guest_login = new Intent(AddToCartActivity.this, LoginActivity.class);
                    guest_login.putExtra("guest_flag", true);
                    guest_login.putExtra("coupon_data", coupon_data_list);
                    startActivity(guest_login);
                } else if (add_id_1.equalsIgnoreCase("")) {
                    Intent add_address = new Intent(AddToCartActivity.this, Add_Edit_Address_Activity.class);
                    add_address.putExtra("address_flag", true);
                    add_address.putExtra("order_add_flag", true);
                    add_address.putExtra("coupon_data", coupon_data_list);
                    startActivity(add_address);
                } else {
                    Intent order = new Intent(AddToCartActivity.this, Activity_Order_Summery.class);
                    order.putExtra("order_flag", true);
                    order.putExtra("coupon_data", coupon_data_list);
                    startActivity(order);
                }
                break;
            case R.id.btn_cont:
                Intent prod_details1 = new Intent(AddToCartActivity.this, FilterProductList.class);
                prod_details1.putExtra("flag", true);
                startActivity(prod_details1);
                break;

            case R.id.iv_edit_coupon_clear:
                ed_cart_coupon.getText().clear();
                break;
        }


    }

    public void showSaveSearchAlertDialog(final List<Cart_Product> product, final String product_id, final String selected_qty, final String id_product_attribute) {

        // TODO Auto-generated method stub
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.row_cart_qty,
                null);

        final EditText etText = (EditText) dialoglayout
                .findViewById(R.id.et_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Enter Quantity");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                if (etText.getText().length() == 0) {

                    Toast.makeText(AddToCartActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
                } else {
//                    cartAPIhelper(product, product_id, etText.getText().toString(), id_product_attribute);
                    String operator = "<operator>up</operator>";
                    dialog.dismiss();
                    try {
                        cart_flag = true;
                        addToCartPresenter.AddTocartAPI(product_id, etText.getText().toString(), id_product_attribute, operator, cart_id, customization_id);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();

                    }
                });

        builder.setView(dialoglayout);
        // builder.show();
        AlertDialog dialog = builder.show();

        int alertTitleId = getResources().getIdentifier("alertTitle", "id", "android");
        TextView alertTitle = (TextView) dialog.getWindow().getDecorView()
                .findViewById(alertTitleId);
        alertTitle.setTextColor(getResources().getColor(R.color.colorPrimary));

    }
}