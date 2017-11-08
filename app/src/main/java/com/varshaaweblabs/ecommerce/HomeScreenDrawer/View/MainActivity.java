package com.varshaaweblabs.ecommerce.HomeScreenDrawer.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Account.View.My_Account_Activity;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Coupon_Added;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;
import com.varshaaweblabs.ecommerce.Cart.View.AddToCartActivity;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Fragments.Homepage;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Fragments.ItemFragment;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_Resp;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_id;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Presenter.HomeDrawerPresenter;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Presenter.IHomeInterface;
import com.varshaaweblabs.ecommerce.LoginActivity.Model.LoginCustomer_Data;
import com.varshaaweblabs.ecommerce.LoginActivity.View.LoginActivity;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;
import java.util.ArrayList;

import timber.log.Timber;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeView.View, IHomeInterface {

    NavigationView navigationView;
    AnimationDrawable animationDrawable;
    LinearLayout ll_main_drawer;
    TextView tv_login_user, tv_login_user_name;
    HomeDrawerPresenter homepresenter;
    private static MainActivity mInstance = null;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    LoginCustomer_Data cust_data;
    Cart_Coupon_Added coupon_data_list;
    TextView textCartItemCount;
    String mCartItemCount;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;


    private static synchronized MainActivity getInstance() {
        return mInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homepresenter = new HomeDrawerPresenter(this, this);
        homepresenter.attachView(this);

        pref_login = this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();
        homepresenter.checkPermission();
//        if(homepresenter.checkPermission()){
//            Toast.makeText(MainActivity.this, new Utility(MainActivity.this).getUDID(), Toast.LENGTH_SHORT).show();
//        }

        setContentView(R.layout.activity_main);
        getWindow().setWindowAnimations(R.style.dialogFragmentAnimation);
        pref = this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor = pref.edit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = LayoutInflater.from(MainActivity.this).inflate(R.layout.nav_header_main, navigationView);

        navigationView.setNavigationItemSelectedListener(this);

        ll_main_drawer = (LinearLayout) headerView.findViewById(R.id.ll_main_drawer);
        tv_login_user = (TextView) headerView.findViewById(R.id.tv_login_user);
        tv_login_user_name = (TextView) headerView.findViewById(R.id.tv_login_user_name);

        editor.putString(Pref_Data.FILTER_RESPONSE, "");
        editor.commit();

        animationDrawable = (AnimationDrawable) ll_main_drawer.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(1000);

        animationDrawable.start();

        try {
            homepresenter.getDrawerMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //TODo To Get Device ID for Guest User
        String device_id = pref.getString(Pref_Data.DEVICE_ID, "");
        Log.e("Device Id", device_id);
        if (device_id.equalsIgnoreCase("")) {
            String deviceId = Settings.Secure.getString(this.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            Toast.makeText(this, deviceId, Toast.LENGTH_SHORT).show();
            editor.putString(Pref_Data.DEVICE_ID, deviceId);
            editor.commit();
        }
        String guest_id = pref.getString(Pref_Data.GUEST_USER_ID, "");
        if (guest_id.equalsIgnoreCase("")) {
            try {
                homepresenter.getGusetId();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String cart_id = "";
        homepresenter.getCartItem(cart_id, "");


        addInitialFragment();
        setData();
    }

    private void setData() {
        cust_data = gson.fromJson(pref.getString(Pref_Data.LOGIN_RESPONSE, ""), LoginCustomer_Data.class);
        Menu m = navigationView.getMenu();
        if (cust_data != null) {
            String name = cust_data.getFirstname().toString();
            String first = name.substring(0, 1);
            tv_login_user.setText(first);
            tv_login_user_name.setText(cust_data.getFirstname().toString() + " " + cust_data.getLastname().toString());
            m.removeItem(0);
            m.removeItem(999);
            m.add("Home");
            m.add(0, 999, 0, "Logout");
            m.add(0, 1, 0, "My Account");

        } else {
            tv_login_user.setText(" ");
            tv_login_user.setBackgroundResource(R.mipmap.user);
            tv_login_user_name.setText("Login/Signup");
            m.removeItem(0);
            m.removeItem(999);
            m.removeItem(1);
            m.add("Home");
            m.add(0, 999, 0, "Login");
            clearedPref();
        }


    }


    @Override
    public void onBackPressed() {

    }


    // @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Intent filter = new Intent(MainActivity.this, FilterActivity.class);
//            startActivity(filter);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        final MenuItem menuItem = menu.findItem(R.id.cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);


        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cart:
                Intent cart = new Intent(MainActivity.this, AddToCartActivity.class);
                cart.putExtra("cart_intent_flag", true);
                cart.putExtra("login_intent_flag", false);
                startActivity(cart);
                return true;
            case R.id.wishlist:
                Toast.makeText(MainActivity.this, "WishList", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem items) {
        // Handle navigation view item clicks here.
        Integer id = items.getItemId();
        String name = items.getTitle().toString();
//        Toast.makeText(this,"Navigation ID:-"+id,Toast.LENGTH_SHORT).show();

        if (id == 0) {

            Fragment fragment = new Homepage();
            FragmentManager frgManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = frgManager.beginTransaction();
            fragmentTransaction.add(R.id.main_content_frame, fragment).commit();
            frgManager.executePendingTransactions();

        } else if (id == 1) {
            Intent my_account = new Intent(MainActivity.this, My_Account_Activity.class);
            startActivity(my_account);

        } else if (name.equalsIgnoreCase("Logout")) {
            clearedPref();
            Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_LONG).show();
            setData();
        } else if (name.equalsIgnoreCase("Login")) {
            Intent guest_login = new Intent(MainActivity.this, LoginActivity.class);
            guest_login.putExtra("guest_flag", false);
            guest_login.putExtra("coupon_data", coupon_data_list);
            startActivity(guest_login);
        } else {
            Fragment fragment = new ItemFragment().newInstance(id);
            FragmentManager frgManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = frgManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_content_frame, fragment).commit();
            frgManager.executePendingTransactions();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void clearedPref() {
        editor.putString(Pref_Data.LOGIN_RESPONSE, "");
        editor.putString(Constants.USER_ID, "");
        editor.putString(Constants.CUSTOMIZATION_ID, "");
        editor.putString(Constants.ADDRESS_ID, "");
        editor.putString(Constants.CART_COUNT, "");
        editor.putString(Pref_Data.CART_DATA, "");
        editor.putString(Pref_Data.CART_PRODUCT, "");
        editor.putString(Constants.CART_ID, "");
        editor.putString(Pref_Data.ADDRESS_DATA, "");
        editor.commit();
    }

    private void addInitialFragment() {
        Fragment fragment = new Homepage();
        FragmentManager frgManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = frgManager.beginTransaction();
        fragmentTransaction.add(R.id.main_content_frame, fragment).commit();
        frgManager.executePendingTransactions();
    }

    @Override
    public void getMenu(final Category_Resp response) {
        final ArrayList<Category_id> id = new ArrayList<Category_id>();
        Constants.allCategory_resp.clear();
        Constants.allCategory_resp.add(response);
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Menu m = navigationView.getMenu();

                for (int j = 0; j < response.getCategories().get(1).getAssociations().getCategories().size(); j++) {

                    id.add(response.getCategories().get(1).getAssociations().getCategories().get(j));

                }
                for (int j = 0; j < id.size(); j++) {
                    for (int i = 0; i < response.getCategories().size(); i++) {
                        if (id.get(j).getId().equalsIgnoreCase(response.getCategories().get(i).getId().toString())) {
                            m.add(Integer.parseInt(response.getCategories().get(i).getIdParent()), response.getCategories().get(i).getId(), Integer.parseInt(response.getCategories().get(i).getLevelDepth()), response.getCategories().get(i).getName());

                        }
                    }
                }

            }


        });
//

    }

    @Override
    public void getCart(Cart_Response cart_response) {
        String cart_count = pref_login.getString(Constants.CART_COUNT, "");
        if (cart_count.equalsIgnoreCase("")) {
            mCartItemCount = "";
        } else {
            mCartItemCount = cart_count;
        }


        if (textCartItemCount != null) {
            if (mCartItemCount.equalsIgnoreCase("")) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(mCartItemCount);
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }

    }


    public static void setActionBarTitle(String title) {
        MainActivity instance = MainActivity.getInstance();
        if (instance != null) {
            // TODO want different toolbar text font?
//            SpannableString s = new SpannableString(title);
//            s.setSpan(new TypefaceSpan("sans-serif-light"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            instance.setTitle(s);
            instance.setTitle(title);
        } else {
            Timber.e("NULL");
        }
    }


}
