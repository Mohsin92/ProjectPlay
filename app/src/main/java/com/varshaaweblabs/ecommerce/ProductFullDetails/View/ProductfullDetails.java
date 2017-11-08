package com.varshaaweblabs.ecommerce.ProductFullDetails.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Cart.View.AddToCartActivity;
import com.varshaaweblabs.ecommerce.Filter.Model.Product;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Adpter.Prod_RecommendedAdapter;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Adpter.ProductColor_Adapter;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Adpter.ProductSize_Adapter;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Attribute_Group;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Combination;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Custmization_Resp;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Customization_Field;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Group;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.ProductFull;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.ProductFull_Product;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Product_Recommend;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Presenter.IProductFullDetailsInterface;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Presenter.ProductFullDetailsPresenter;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Imageutils;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinesh on 21/9/17.
 */

public class ProductfullDetails extends BaseActivity implements IProductFullDetailsInterface, ProductFullDetailsView.View, View.OnClickListener, Imageutils.ImageAttachmentListener, ProductColor_Adapter.Helper, ProductSize_Adapter.Helper {

    public static final String EXTRA_NAME = "";
    ArrayList<String> alImages = new ArrayList<String>();
    ViewPager vPager;
    MyPagerAdapter myPagerAdapter;
    private Menu menu;
    TextView prod_name, prod_brndname, prod_price, tv_desc;
    Product prod_fulldetails = new Product();
    Product_Recommend prod_recomm_details = new Product_Recommend();
    Boolean flag_recomm = true, flag_prod = true;
    ImageView iv_manufacture;
    private MultiSnapRecyclerView recomanded_rw, rv_prod_att_list;
    int img_pos;
    RelativeLayout rl_attribute;
    String prod_id;
    ArrayList<Group> grps = new ArrayList<>();
    ArrayList<ProductFull> full_resp = new ArrayList<>();
    ArrayList<ProductFull_Product> full_prod_resp = new ArrayList<>();
    ArrayList<Customization_Field> prod_cust_fieldlist = new ArrayList<>();
    ArrayList<Attribute_Group> grp_att = new ArrayList<>();
    ArrayList<Attribute_Group> grp_attsize = new ArrayList<>();
    ArrayList<Attribute_Group> grp_attcolor = new ArrayList<>();
    ArrayList<Combination> prod_combi_list = new ArrayList<>();
    ArrayList<Product_Recommend> prod_recomm_list = new ArrayList<>();
    ProductSize_Adapter prodsize_attributeadapter;
    ProductColor_Adapter prod_coloradapter;
    ProductFullDetailsPresenter prod_fullpres;
    CardView cardview;
    LayoutParams layoutparams;
    TextView textview, tv_avail;
    RelativeLayout rl_features, rl_cust;
    NestedScrollView ns_scroll;
    LinearLayout ll_feature_name, ll_feature_value;
    Prod_RecommendedAdapter recom_productList_adapter;
    ArrayList<String> stringArrayList = new ArrayList<String>(2);
    String prod_sizeatt_id = "", prod_coloratt_id = "";
    Boolean flag_combi = false;
    CardView card_custmization;
    LinearLayout ll_customization, ll_cust_title;
    List<EditText> allEds = new ArrayList<EditText>();
    List<Button> allbtn = new ArrayList<Button>();
    EditText ed;
    Button btn;
    TextView tv_title, tv_disc, tv_disc_per;
    Button btn_save_customization, btn_addcart;
    RecyclerView single_choice_rv_color, single_choice_rv_size;
    TextView textCartItemCount;
    String mCartItemCount;
    String cart_id, cust_id;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;
    CardView card_color, card_size;
    Imageutils imageutils;
    Bitmap bitmap;
    private String file_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prod_fullpres = new ProductFullDetailsPresenter(this, this);
        prod_fullpres.attachView(this);
        setContentView(R.layout.product_detail_main);
        vPager = (ViewPager) findViewById(R.id.prod_pager);
        final AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
        prod_name = (TextView) findViewById(R.id.prod_name);
        prod_brndname = (TextView) findViewById(R.id.prod_brandname);
        prod_price = (TextView) findViewById(R.id.prod_price);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
//        rl_attribute = (RelativeLayout) findViewById(R.id.rl_attribute);
        iv_manufacture = (ImageView) findViewById(R.id.iv_manufacture);
//        rv_prod_att_list = (MultiSnapRecyclerView) findViewById(R.id.prod_att_list);
        tv_avail = (TextView) findViewById(R.id.tv_avail);
        rl_features = (RelativeLayout) findViewById(R.id.rl_features);
        ll_feature_name = (LinearLayout) findViewById(R.id.ll_feature_name);
        ll_feature_value = (LinearLayout) findViewById(R.id.ll_feature_value);
        ns_scroll = (NestedScrollView) findViewById(R.id.ns_scroll);
        recomanded_rw = (MultiSnapRecyclerView) findViewById(R.id.recomanded_rw);
        card_custmization = (CardView) findViewById(R.id.card_custmization);
        ll_cust_title = (LinearLayout) findViewById(R.id.ll_cust_title);
        ll_customization = (LinearLayout) findViewById(R.id.ll_cust);
        rl_cust = (RelativeLayout) findViewById(R.id.rl_cust);
        btn_save_customization = (Button) findViewById(R.id.btn_save_customization);
        single_choice_rv_color = (RecyclerView) findViewById(R.id.single_choice_rv_color);
        single_choice_rv_size = (RecyclerView) findViewById(R.id.single_choice_rv_size);
        btn_addcart = (Button) findViewById(R.id.btn_addcart);
        tv_disc = (TextView) findViewById(R.id.tv_disc);
        tv_disc_per = (TextView) findViewById(R.id.tv_disc_per);
        card_color = (CardView) findViewById(R.id.card_color);
        card_size = (CardView) findViewById(R.id.card_size);


        pref_login = this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();


        recomanded_rw.setLayoutManager(new LinearLayoutManager(ProductfullDetails.this));
        recomanded_rw.setHasFixedSize(true);
//        rv_prod_att_list.setLayoutManager(new LinearLayoutManager(ProductfullDetails.this));
//        rv_prod_att_list.setHasFixedSize(true);
        single_choice_rv_color.setHasFixedSize(true);
        single_choice_rv_size.setHasFixedSize(true);

        btn_save_customization.setOnClickListener(this);
        btn_addcart.setOnClickListener(this);


        imageutils = new Imageutils(this);

        float heightDp = 13 * getResources().getDisplayMetrics().heightPixels / 20;
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
        lp.height = (int) heightDp;

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Constants.PROD_COMBINATIONID = "";

        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");

        Intent prod = getIntent();
        flag_recomm = prod.getBooleanExtra("flag_recomm", true);
        flag_prod = prod.getBooleanExtra("flag_prod", true);


        //TODO flag_recomm flag true for Recommend Product and Populer Product Navigation

        if (!flag_recomm) {
            alImages.clear();
            prod_recomm_details = (Product_Recommend) prod.getSerializableExtra("selected_data");
            if (Constants.PROD_COMBINATIONID.equalsIgnoreCase("")) {
                for (int i = 0; i < prod_recomm_details.getImages().size(); i++) {
                    alImages.add(prod_recomm_details.getImages().get(i).getLarge().getUrl());
                }
            }

            getPrices();
            tv_desc.setText(android.text.Html.fromHtml(prod_recomm_details.getDescriptionShort()).toString());
            Constants.FULLPRODUCT_ID = prod_recomm_details.getId();
        } else {
            alImages.clear();
            prod_fulldetails = (Product) prod.getSerializableExtra("selected_data");
            if (Constants.PROD_COMBINATIONID.equalsIgnoreCase("")) {
                for (int i = 0; i < prod_fulldetails.getImages().size(); i++) {
                    alImages.add(prod_fulldetails.getImages().get(i).getLarge().getUrl());
                }
            }
            getPrices();
            Constants.FULLPRODUCT_ID = prod_fulldetails.getId();
        }


        myPagerAdapter = new MyPagerAdapter(alImages);
        vPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (alImages.size() == 1) {

            tabLayout.setVisibility(View.VISIBLE);
        } else {

            tabLayout.setupWithViewPager(vPager, true);
        }

        DetailOnPageChangeListener listener = new DetailOnPageChangeListener();
        vPager.setOnPageChangeListener(listener);

        getData();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (toolbar.getMenu().size() > 0) {
                    Drawable overflowIcon = toolbar.getOverflowIcon();
                    Drawable naviIcon = toolbar.getNavigationIcon();
                    if ((collapsingToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbar))) {
                        if (flag_recomm) {
                            collapsingToolbar.setTitle(prod_fulldetails.getName());
                        } else {
                            collapsingToolbar.setTitle(prod_recomm_details.getName());
                        }

                        if (overflowIcon != null) {
                            Drawable newIcon = overflowIcon.mutate();
                            newIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.MULTIPLY);
                            toolbar.setOverflowIcon(newIcon);
                            Drawable neviIcon = naviIcon.mutate();
                            neviIcon.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.MULTIPLY);
                            toolbar.setNavigationIcon(neviIcon);

                        }
                    } else {
                        toolbar.getMenu().getItem(0).setIcon(R.mipmap.cart_grey);
                        collapsingToolbar.setTitle(" ");

                        if (overflowIcon != null) {
                            Drawable newIcon = overflowIcon.mutate();
                            newIcon.setColorFilter(getResources().getColor(R.color.cardview_dark_background), PorterDuff.Mode.MULTIPLY);
                            toolbar.setOverflowIcon(newIcon);
                            Drawable neviIcon = naviIcon.mutate();
                            neviIcon.setColorFilter(getResources().getColor(R.color.cardview_dark_background), PorterDuff.Mode.MULTIPLY);
                            toolbar.setNavigationIcon(neviIcon);
                        }

                    }
                }
            }
        });


        FloatingActionButton share = (FloatingActionButton) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Product Details");
                i.putExtra(android.content.Intent.EXTRA_TEXT, prod_fulldetails.getImages().get(0).getLarge().getUrl());
                startActivity(Intent.createChooser(i, "Share via"));
            }
        });
    }

    private void getData() {


        if (!flag_combi) {
            try {
                prod_fullpres.getfullproducts(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        final MenuItem menuItem = menu.findItem(R.id.cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupBadge();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cart:
                Intent cart = new Intent(ProductfullDetails.this, AddToCartActivity.class);
                cart.putExtra("cart_intent_flag", true);
                cart.putExtra("login_intent_flag", false);
                startActivity(cart);
                return true;
            case R.id.wishlist:
                Toast.makeText(ProductfullDetails.this, "WishList", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.appbar:
                break;

            case R.id.btn_save_customization:
                String text = "";
                ArrayList<String> inputtag = new ArrayList<>();
                cart_id = pref_login.getString(Constants.CART_ID, "");
                cust_id = pref_login.getString(Constants.CUSTOMIZATION_ID, "");
                String cust_finalurl;
                String tag_url = "";
                ArrayList<String> cust_url = new ArrayList<>();
                cust_url.clear();
                String url = "<prestashop><customization><id_cart>" + cart_id + "</id_cart><id_guest></id_guest><id_customer>" + cust_id + "</id_customer><id_product>" + Constants.FULLPRODUCT_ID + "</id_product>";

                for (int i = 0; i < full_prod_resp.size(); i++) {
                    for (int j = 0; j < full_prod_resp.get(i).getCustomizations().getFields().size(); j++) {
                        inputtag.add(full_prod_resp.get(i).getCustomizations().getFields().get(j).getInputName());
                    }
                }


                //TODO for get Text from Edittext and Image From Button
                for (int a = 0; a < allEds.size(); a++) {
                    text = allEds.get(a).getText().toString();
                    cust_url.add("<" + inputtag.get(a) + ">" + text + "</" + inputtag.get(a) + ">");
                }


                for (int b = 0; b < allbtn.size(); b++) {

                }
                for (int i = 0; i < cust_url.size(); i++) {
                    tag_url += cust_url.get(i);
                }
                cust_finalurl = url + tag_url + "</customization></prestashop>";
                Log.e("cust_finalurl", String.valueOf(cust_finalurl));
                try {
                    prod_fullpres.getCustmization(cust_finalurl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_addcart:
                for (int i = 0; i < full_prod_resp.size(); i++) {
                    Constants.prod_attribute_id = full_prod_resp.get(i).getIdProductAttribute();
                }
                if (full_prod_resp.get(0).getAvailability().toUpperCase().equalsIgnoreCase("unavailable")) {
                    Toast.makeText(ProductfullDetails.this, full_prod_resp.get(0).getAvailabilityMessage() + " " + "Select Another Product", Toast.LENGTH_SHORT).show();
                } else {
                    Intent cart = new Intent(ProductfullDetails.this, AddToCartActivity.class);
                    cart.putExtra("login_intent_flag", false);
                    cart.putExtra("cart_intent_flag", false);
                    startActivity(cart);
                }
        }
    }

    //TODO Response
    @Override
    public void Prod_FullData(final ProductFull prod_full, ProductFullDetailsView.View view) {
        grp_att.clear();
        grp_attsize.clear();
        grp_attcolor.clear();
        grps.clear();
        full_resp.clear();
        full_prod_resp.clear();
        prod_combi_list.clear();
        prod_cust_fieldlist.clear();
        prod_recomm_list.clear();

        //TODO Recommended Product

        for (int i = 0; i < prod_full.getAccessories().size(); i++) {
            prod_recomm_list.add(prod_full.getAccessories().get(i));
        }


        for (int i = 0; i < prod_full.getGroups().size(); i++) {
            grps.add(prod_full.getGroups().get(i));

            for (int j = 0; j < prod_full.getGroups().get(i).getAttributes().size(); j++) {
                grp_att.add(prod_full.getGroups().get(i).getAttributes().get(j));
                if (prod_full.getGroups().get(i).getGroupName().equalsIgnoreCase("Size")) {

                    if (prod_full.getGroups().get(i).getAttributes().get(j).getIs_visible()) {
                        grp_attsize.add(prod_full.getGroups().get(i).getAttributes().get(j));
                    }
                } else if (prod_full.getGroups().get(i).getGroupName().equalsIgnoreCase("Color")) {
                    if (prod_full.getGroups().get(i).getAttributes().get(j).getIs_visible()) {
                        grp_attcolor.add(prod_full.getGroups().get(i).getAttributes().get(j));
                    }
                }
            }
        }

        for (int i = 0; i < prod_full.getCombinations().size(); i++) {
            prod_combi_list.add(prod_full.getCombinations().get(i));
        }
        full_prod_resp.add(prod_full.getProduct());
        full_resp.add(prod_full);
        ProductfullDetails.this.runOnUiThread(new Runnable() {
            ProductSize_Adapter.Helper help_size = new ProductSize_Adapter.Helper() {

                @Override
                public void onSizeItemSelected(Attribute_Group mSingleChoiceItem) {
                    prod_sizeatt_id = mSingleChoiceItem.getIdAttributes().toString();
                    getproductatt_id(prod_sizeatt_id, prod_coloratt_id);

                }

                @Override
                public void onDefaultSelected(String id) {
                    prod_sizeatt_id = id;
                }

            };
            ProductColor_Adapter.Helper help = new ProductColor_Adapter.Helper() {
                @Override
                public void onItemSelected(Attribute_Group mSingleChoiceItem) {
                    prod_coloratt_id = mSingleChoiceItem.getIdAttributes().toString();
                    getproductatt_id(prod_sizeatt_id, prod_coloratt_id);

                }

                @Override
                public void onDefaultSelected(String id) {
                    prod_coloratt_id = id;
                }
            };


            @Override
            public void run() {
                rl_features.removeAllViews();
                ll_feature_name.removeAllViews();
                ll_feature_value.removeAllViews();
                rl_cust.removeAllViews();
                ll_customization.removeAllViews();

                ns_scroll.post(new Runnable() {
                    public void run() {
                        ns_scroll.scrollTo(ns_scroll.getBottom(), 0);
                    }
                });

                if (!Constants.PROD_COMBINATIONID.equalsIgnoreCase("")) {
                    alImages.clear();
                    if (!flag_recomm) {
                        for (int i = 0; i < prod_recomm_details.getImages().size(); i++) {
                            alImages.add(prod_recomm_details.getImages().get(i).getLarge().getUrl());
                        }
                        getPrices();
                    } else {
                        for (int i = 0; i < prod_fulldetails.getImages().size(); i++) {
                            alImages.add(prod_full.getProduct().getImages().get(i).getLarge().getUrl());
                        }
                        getPrices();
                        myPagerAdapter = new MyPagerAdapter(alImages);
                        vPager.setAdapter(myPagerAdapter);
                    }
                }


                //TODO For Product Customization

                for (int i = 0; i < full_prod_resp.size(); i++) {
                    tv_avail.setText(full_prod_resp.get(i).getAvailability().toUpperCase());

                    if (full_prod_resp.get(i).getCustomizable()) {
                        card_custmization.setVisibility(View.VISIBLE);
                    }


                    for (int j = 0; j < full_prod_resp.get(i).getCustomizations().getFields().size(); j++) {
                        if (full_prod_resp.get(i).getCustomizations().getFields().get(j).getType().equalsIgnoreCase("text")) {
                            ed = new EditText(ProductfullDetails.this);
                            tv_title = new TextView(ProductfullDetails.this);

                            if (tv_title.getParent() != null)
                                ((ViewGroup) tv_title.getParent()).removeView(tv_title);
                            tv_title.setText(full_prod_resp.get(i).getCustomizations().getFields().get(j).getLabel());
                            ll_customization.addView(tv_title);
                            if (ed.getParent() != null)
                                ((ViewGroup) ed.getParent()).removeView(ed);
                            ed.setId(j);
                            allEds.add(ed);// <- fix
                            ll_customization.addView(ed);
                        } else {
                            Button myButton = new Button(ProductfullDetails.this);
                            myButton.setText("Add Images");
                            myButton.setPadding(5, 5, 5, 5);
                            myButton.setTextColor(Color.parseColor("#ffffff"));
                            myButton.setBackgroundColor(Color.parseColor("#f2972a"));
                            LayoutParams btn_lay = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                            btn_lay.gravity = Gravity.CENTER;
                            myButton.setLayoutParams(btn_lay);
                            tv_title = new TextView(ProductfullDetails.this);


                            if (tv_title.getParent() != null)
                                ((ViewGroup) tv_title.getParent()).removeView(tv_title);
                            tv_title.setText(full_prod_resp.get(i).getCustomizations().getFields().get(j).getLabel());
                            ll_customization.addView(tv_title);
                            myButton.setId(j);
                            allbtn.add(myButton);
                            ll_customization.addView(myButton);

                            final int id_ = myButton.getId();

                            myButton.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Toast.makeText(ProductfullDetails.this,
                                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                                            .show();
                                    imageutils.imagepicker(1);
                                }
                            });

                        }

                    }
                    for (int j = 0; j < full_prod_resp.get(i).getFeatures().size(); j++) {
                        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View v = vi.inflate(R.layout.product_feature_item, null);
                        TextView textView = (TextView) v.findViewById(R.id.tv_key);
                        TextView tv_value = (TextView) v.findViewById(R.id.tv_value);
                        tv_value.setId(i);

                        if (textView.getParent() != null)
                            ((ViewGroup) textView.getParent()).removeView(textView); // <- fix
                        textView.setText(full_prod_resp.get(i).getFeatures().get(j).getName());

                        ll_feature_name.addView(textView);
                        if (tv_value.getParent() != null)
                            ((ViewGroup) tv_value.getParent()).removeView(tv_value); // <- fix
                        tv_value.setText(full_prod_resp.get(i).getFeatures().get(j).getValue());

                        ll_feature_value.addView(tv_value);
                    }


                }
                rl_cust.addView(ll_customization);
                rl_features.addView(ll_feature_name);
                rl_features.addView(ll_feature_value);

                for (int j = 0; j < full_resp.size(); j++) {
                    Glide.with(ProductfullDetails.this).load(full_resp.get(j).getManufacturerImageUrl()).into(iv_manufacture);

                }


                for (int i = 0; i < grps.size(); i++) {
                    if (grps.get(i).getGroupName().equalsIgnoreCase("Size")) {
                        card_size.setVisibility(View.VISIBLE);
                        ProductSize_Adapter mSizeAdapter = new ProductSize_Adapter(ProductfullDetails.this, help_size);
                        single_choice_rv_size.setAdapter(mSizeAdapter);
                        mSizeAdapter.setValues(grp_attsize);
                    } else {
                        card_color.setVisibility(View.VISIBLE);
                        ProductColor_Adapter mSingleChoiceAdapter = new ProductColor_Adapter(ProductfullDetails.this, help);
                        single_choice_rv_color.setAdapter(mSingleChoiceAdapter);
                        mSingleChoiceAdapter.setValues(grp_attcolor);
                    }
                }


                //TODO Recommended Product

                recom_productList_adapter = new Prod_RecommendedAdapter(ProductfullDetails.this, prod_recomm_list);
                LinearLayoutManager secondManager = new LinearLayoutManager(ProductfullDetails.this, LinearLayoutManager.HORIZONTAL, false);
                recomanded_rw.setLayoutManager(secondManager);
                recomanded_rw.setAdapter(recom_productList_adapter);

            }


        });

        view.hideProgress();

    }

    //TODO Response of Custmization
    @Override
    public void getCustmizations(final Custmization_Resp resp_cust) {
        ProductfullDetails.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                editor_login.putString(Constants.CUSTOMIZATION_ID, resp_cust.getIdCustomization());
                editor_login.commit();
                editor_login.putString(Constants.CART_ID, resp_cust.getIdCart());
                editor_login.commit();
                Toast.makeText(ProductfullDetails.this, "Cust_Id " + resp_cust.getIdCustomization() + resp_cust.getIdCart(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onItemSelected(Attribute_Group mSingleChoiceItem) {

    }

    @Override
    public void onSizeItemSelected(Attribute_Group mSingleChoiceItem) {

    }

    @Override
    public void onDefaultSelected(String id) {

    }


    public class DetailOnPageChangeListener extends
            ViewPager.SimpleOnPageChangeListener {

        private int currentPage;

        @Override
        public void onPageSelected(int position) {

            currentPage = position;


        }

        public int getCurrentPage() {
            return currentPage;
        }
    }

    private class MyPagerAdapter extends PagerAdapter {

        ArrayList<String> alImages = new ArrayList<String>();
        DisplayImageOptions options;
        public ImageLoader imageLoader = ImageLoader.getInstance();
        private String currentPage;
        private LayoutInflater inflater;

        public MyPagerAdapter(ArrayList<String> alImages) {

            this.alImages = alImages;
            inflater = LayoutInflater.from(ProductfullDetails.this);
            // this.currentPage=currentPage;
            // imageLoader.init(ImageLoaderConfiguration.createDefault(this.context));
            // TODO Auto-generated constructor stub
            imageLoader.init(ImageLoaderConfiguration.createDefault(ProductfullDetails.this));

        }

        @Override
        public int getCount() {
            return alImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            View imageLayout = inflater.inflate(R.layout.item_pager_image,
                    container, false);
            currentPage = alImages.get(position);
            ImageView imageView = (ImageView) imageLayout
                    .findViewById(R.id.image);
            final ProgressBar spinner = (ProgressBar) imageLayout
                    .findViewById(R.id.loading);

            options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisk(true)
                    .resetViewBeforeLoading(true).considerExifParams(true)
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                    .displayer(new FadeInBitmapDisplayer(300)).build();


            imageLoader.getInstance().displayImage(alImages.get(position),
                    imageView, options, new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            spinner.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingComplete(String imageUri,
                                                      View view, Bitmap loadedImage) {
                            spinner.setVisibility(View.GONE);
                        }
                    });
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent prod_fullimage = new Intent(ProductfullDetails.this, ProductFullImage.class);
                    prod_fullimage.putExtra("selected_image", (Serializable) alImages);
                    prod_fullimage.putExtra("flag_recomm", flag_recomm);
                    startActivity(prod_fullimage);
                    try {


                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        Log.e("ImageExceptions: ", e.toString());
                    }
                }
            });

            container.addView(imageLayout, 0);

            return imageLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imageutils.onActivityResult(requestCode, resultCode, data);

        Log.e("Result : ", "Activity Result");
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {

        this.bitmap = file;
        this.file_name = filename;
//        mainimage.setImageBitmap(file);


//        String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;

//        imageutils.createImage(file, filename, path, false);
        String file_format = file_name.substring(file_name.lastIndexOf(".") + 1);

        Toast.makeText(this, file_format, Toast.LENGTH_SHORT).show();
        String base64 = imageutils.BitMapToString(file);


        Log.e("Image Base : ", file_format + "|" + base64);
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

        }


    }

    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void setupBadge() {

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


    public void getproductatt_id(String size_id, String color_id) {
        prod_sizeatt_id = size_id;
        prod_coloratt_id = color_id;
        ArrayList<String> size_attribute_idlist = new ArrayList<>();
        ArrayList<String> color_attribute_idlist = new ArrayList<>();
        size_attribute_idlist.clear();
        color_attribute_idlist.clear();

        for (int j = 0; j < prod_combi_list.size(); j++) {
            for (int k = 0; k < prod_combi_list.get(j).getAttributesValues().size(); k++) {
                if (prod_combi_list.get(j).getAttributesValues().get(k).getIdAttributes().toString().equalsIgnoreCase("1")) {
                    size_attribute_idlist.add(prod_combi_list.get(j).getAttributesValues().get(k).getIdAttributesValues().toString());
                } else {
                    color_attribute_idlist.add(prod_combi_list.get(j).getAttributesValues().get(k).getIdAttributesValues().toString());
                }
            }
        }
        for (int p = 0; p < size_attribute_idlist.size(); p++) {
            if (prod_sizeatt_id == size_attribute_idlist.get(p) && prod_coloratt_id == color_attribute_idlist.get(p)) {
                Toast.makeText(ProductfullDetails.this, "ID Product Attribute" + prod_combi_list.get(p).getIdProductAttribute(), Toast.LENGTH_SHORT).show();
                Constants.PROD_COMBINATIONID = String.valueOf(prod_combi_list.get(p).getIdProductAttribute());
                flag_combi = true;
            }

        }
        if (flag_combi) {
            try {
                prod_fullpres.getfullproducts(null);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(ProductfullDetails.this, "Wrong Combination", Toast.LENGTH_SHORT).show();
        }


    }

    private void getPrices() {
        if (!flag_recomm) {
            prod_name.setText(prod_recomm_details.getName());
            prod_brndname.setText(prod_recomm_details.getManufacturerName());
            if (!prod_recomm_details.getPriceAmount().equals(prod_recomm_details.getRegularPriceAmount())) {
                tv_disc.setVisibility(View.VISIBLE);
                tv_disc_per.setVisibility(View.VISIBLE);
                prod_price.setText("$ " + prod_recomm_details.getPriceAmount().toString());
                prod_price.setTextColor(Color.parseColor("#696969"));
                tv_disc.setPaintFlags(tv_disc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tv_disc.setText("$ " + prod_recomm_details.getRegularPrice().toString());
                prod_price.setTextColor(Color.parseColor("#000000"));
                tv_disc_per.setText("SAVE " + prod_recomm_details.getDiscountPercentageAbsolute());
            } else {
                tv_disc.setVisibility(View.GONE);
                tv_disc_per.setVisibility(View.GONE);
                prod_price.setText("$ " + prod_recomm_details.getPriceAmount().toString());
            }
            tv_desc.setText(android.text.Html.fromHtml(prod_recomm_details.getDescriptionShort()).toString());
        } else {
            prod_name.setText(prod_fulldetails.getName());
            prod_brndname.setText(prod_fulldetails.getManufacturerName());
            if (!prod_fulldetails.getPriceAmount().equals(prod_fulldetails.getRegularPriceAmount())) {
                tv_disc.setVisibility(View.VISIBLE);
                tv_disc_per.setVisibility(View.VISIBLE);
                prod_price.setText("$ " + prod_fulldetails.getPriceAmount().toString());
                prod_price.setTextColor(Color.parseColor("#696969"));
                tv_disc.setPaintFlags(tv_disc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tv_disc.setText("$ " + prod_fulldetails.getRegularPrice().toString());
                prod_price.setTextColor(Color.parseColor("#000000"));
                tv_disc_per.setText("SAVE " + prod_fulldetails.getDiscountPercentageAbsolute());
            } else {
                tv_disc.setVisibility(View.GONE);
                tv_disc_per.setVisibility(View.GONE);
                prod_price.setText("$ " + prod_fulldetails.getPriceAmount().toString());
            }

            tv_desc.setText(android.text.Html.fromHtml(prod_fulldetails.getDescriptionShort()).toString());

        }

    }

}



