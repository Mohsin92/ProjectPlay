package com.varshaaweblabs.ecommerce.Filter.View;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Response;
import com.varshaaweblabs.ecommerce.Cart.View.AddToCartActivity;
import com.varshaaweblabs.ecommerce.Filter.Adapter.FilterProductList_Adapter;
import com.varshaaweblabs.ecommerce.Filter.Model.Filter_Resp;
import com.varshaaweblabs.ecommerce.Filter.Model.Product;
import com.varshaaweblabs.ecommerce.Filter.Model.Product_Sortby;
import com.varshaaweblabs.ecommerce.Filter.Presenter.IProductInterface;
import com.varshaaweblabs.ecommerce.Filter.Presenter.ProductPresenter;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.MainActivity;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinesh on 6/9/17.
 */

public class FilterProductList extends BaseActivity implements FilterView.View, IProductInterface, View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProductPresenter filterPresenter;
    Boolean flag = false, Congratulations_flag = false;
    LinearLayout lay_filter, lay_sort, ll_list;
    ImageView list_product;
    String prod_id;
    boolean flag_list = false;
    private Menu menu;
    TextView textCartItemCount;
    String mCartItemCount;
    SharedPreferences pref_login;
    SharedPreferences.Editor editor_login;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recycler_list;
    private ProgressDialog progressDialog;
    ArrayList<Product> product = new ArrayList<>();
    FilterProductList_Adapter filterproductList_adapter;
    ArrayList<String> resultListName = new ArrayList<>();
    ArrayList<Product_Sortby> prod_sort = new ArrayList<>();
    String pass_selected_data;
    AlertDialog alertDialog1;

    int selected_id = 0;

    public FilterProductList() {
        // Required empty public constructor


    }

    // TODO: Rename and change types and number of parameters
    public FilterProductList newInstance(ArrayList<String> filtername) {
        FilterProductList fragment = new FilterProductList();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, pStringaram1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        resultListName = filtername;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        super.drawerLayout();

        filterPresenter = new ProductPresenter(this, FilterProductList.this);
        filterPresenter.attachView(this);
        setContentView(R.layout.activity_filter_product_list);
        pref_login = this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_login = pref_login.edit();


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Product List");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDo maintain the Tracks
                if (Congratulations_flag) {
                    Intent main = new Intent(FilterProductList.this, MainActivity.class);
                    startActivity(main);
                } else {
                    finish();
                }

            }
        });

        lay_filter = (LinearLayout) findViewById(R.id.lay_filter);
        lay_sort = (LinearLayout) findViewById(R.id.lay_sort);
        ll_list = (LinearLayout) findViewById(R.id.ll_list);
        list_product = (ImageView) findViewById(R.id.list_product);
        recycler_list = (RecyclerView) findViewById(R.id.recycler_list);


        Intent i = getIntent();
        flag = i.getBooleanExtra("flag", flag);
        Congratulations_flag = i.getBooleanExtra("Congratulations_flag", Congratulations_flag);

        //ToDo maintain the Tracks
        if (flag) {
            Constants.FILTER_URL = null;
            Constants.FILTER_SORT = "product.position.asc";
            prod_id = i.getStringExtra("id");
            Constants.VALUE = prod_id;

            try {
                filterPresenter.getApplyFilters(Constants.FILTER_URL);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            Filter_selected_data(Constants.filterResp);
        }


//        resultListName = i.getStringArrayListExtra("resultListName");
//
//        Log.e("Arrary", String.valueOf(resultListName));
//
//        if (resultListName!= null) {
////            String selected_data = resultListName.toString().substring(1, resultListName.toString().length() - 1);
////            pass_selected_data = selected_data.replace(",", "-").replace(" ", "");
////            Log.e("selected_data", selected_data);
////            Log.e("Truncated Data", pass_selected_data);
//        } else {
//            pass_selected_data = null;
//        }

        String cart_id = "";
        String user_id = pref_login.getString(Constants.USER_ID, "");

        filterPresenter.getCartItem(cart_id, user_id);
        lay_filter.setOnClickListener(this);
        lay_sort.setOnClickListener(this);
        ll_list.setOnClickListener(this);
        recycler_list.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
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
                Intent cart = new Intent(FilterProductList.this, AddToCartActivity.class);
                cart.putExtra("cart_intent_flag", true);
                cart.putExtra("login_intent_flag", false);
                startActivity(cart);
                return true;
            case R.id.wishlist:
                Toast.makeText(FilterProductList.this, "WishList", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }


    @Override
    public void showProgress() {
        super.showProgress();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
    }


    @Override
    public void Filter_selected_data(final Filter_Resp filter_resp) {
        FilterProductList.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                prod_sort.clear();
                product.clear();
                for (int i = 0; i < filter_resp.getProduct().size(); i++) {

                    product.add(filter_resp.getProduct().get(i));
                }
                for (int j = 0; j < filter_resp.getSortby().size(); j++) {
                    prod_sort.add(filter_resp.getSortby().get(j));
                }

                setProductlist(flag_list);


//
            }

        });
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


    private void setProductlist(boolean flag_list) {
        if (product.size() > 0) {
            RecyclerView.LayoutManager mLayoutManager = null;
            filterproductList_adapter = new FilterProductList_Adapter(FilterProductList.this, product, flag_list);

            if (flag_list) {
                list_product.setImageResource(R.mipmap.grid_view);
                mLayoutManager = new LinearLayoutManager(FilterProductList.this);


            } else {
                list_product.setImageResource(R.mipmap.list_view);
                mLayoutManager = new GridLayoutManager(FilterProductList.this, 2);

            }

//            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(FilterProductList.this, 2);
            recycler_list.setLayoutManager(mLayoutManager);
            recycler_list.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
            recycler_list.setItemAnimator(new DefaultItemAnimator());
            recycler_list.setAdapter(filterproductList_adapter);
        }


    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.lay_filter:

                Intent filter = new Intent(FilterProductList.this, FilterActivity.class);
//                filter.putExtra("prod_flag",true);
                startActivity(filter);

                break;

            case R.id.lay_sort:
                showRadioButtonDialog();
                break;


            case R.id.ll_list:
                if (flag_list) {
                    flag_list = false;
                } else {
                    flag_list = true;
                }

                setProductlist(flag_list);


        }


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void showRadioButtonDialog() {

        // custom dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(FilterProductList.this);

        builder.setTitle("Sort");
        List<String> values = new ArrayList<>();
        values.clear();

        for (int i = 0; i < prod_sort.size(); i++) {
            values.add(prod_sort.get(i).getLabel());
        }
        final CharSequence[] val = values.toArray(new CharSequence[values.size()]);
        Log.e("button", val.toString());

        builder.setSingleChoiceItems(val, selected_id, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getApplicationContext(),
//                        "Selected pos = "+i+"Selected Url"+prod_sort.get(i).getUrlParameter(), Toast.LENGTH_SHORT).show();
                selected_id = i;
                Constants.FILTER_SORT = prod_sort.get(i).getUrlParameter();
                alertDialog1.dismiss();
                try {
                    filterPresenter.getApplyFilters(Constants.FILTER_URL);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        alertDialog1 = builder.create();
        alertDialog1.show();
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

//            if (includeEdge) {
//                // outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
//                // outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
//
//                if (position < spanCount) { // top edge
//                    outRect.top = spacing;
//                }
//                outRect.bottom = spacing; // item bottom
//            } else {
//                //         outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
//                //       outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
//                if (position >= spanCount) {
//                    outRect.top = spacing; // item top
//                }
//            }
        }
    }


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
