package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;
import com.varshaaweblabs.ecommerce.Base.BaseFragment;
import com.varshaaweblabs.ecommerce.Filter.View.FilterProductList;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Adapter.ProductList_Adapter;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Home_Slider_Resp;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Slider_Images;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Presenter.HomeFragmentPresenter;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Presenter.IHomeFragmentInterface;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.HomeView;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.MainActivity;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Adpter.Prod_RecommendedAdapter;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Product_Recommend;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by mohsin on 14/6/17.
 */

public class Homepage extends BaseFragment implements HomeView.View,View.OnClickListener,IHomeFragmentInterface {
    private ProgressDialog progressDialog;
    private MultiSnapRecyclerView recyclerView;
    TextView tv_bestproduct_vwall, tv_bestoffer_vwall;
    ViewPager vPager;
    ProductList_Adapter productList_adapter;
    MyPagerAdapter myPagerAdapter;
    HomeFragmentPresenter home_presenter;
    ArrayList<String> alImages = new ArrayList<String>();
    private boolean mHorizontal;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Prod_RecommendedAdapter recom_productList_adapter;



    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Timber.d("%s - OnCreateView", this.getClass().getSimpleName());

        home_presenter=new HomeFragmentPresenter(getContext(),Homepage.this);
        home_presenter.attachView(Homepage.this);

        pref = getContext().getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor = pref.edit();

        MainActivity.setActionBarTitle("New Arrivals");

        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

//        progressDialog = Utils.generateProgressDialog(getActivity(), false);

        vPager = (ViewPager) view.findViewById(R.id.pager);
        recyclerView = (MultiSnapRecyclerView) view.findViewById(R.id.recycler_vw);
//        recycler_vw = (RecyclerView) view.findViewById(R.id.recycler_view);
        tv_bestproduct_vwall = (TextView) view.findViewById(R.id.tv_bestproduct_vwall);
//        tv_bestoffer_vwall = (TextView) view.findViewById(R.id.tv_bestoffer_vwall);
        tv_bestproduct_vwall.setOnClickListener(this);
//        tv_bestoffer_vwall.setOnClickListener(this);

//        recycler_vw.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recycler_vw.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        mHorizontal = true;

        try {
            home_presenter.HomeData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // home_presenter.getProducts();


        DetailOnPageChangeListener listener = new DetailOnPageChangeListener();
        vPager.setOnPageChangeListener(listener);


        final int[] currentPage = {0};
        Timer timer;
        final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
        final long PERIOD_MS = 3000;
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage[0] == alImages.size() - 1) {
                    currentPage[0] = 0;
                }
                vPager.setCurrentItem(currentPage[0]++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);


        return view;

    }




    //
//    private void getProductsAPI() {
//
//        progressDialog.show();
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        final OAuthInterceptor oauth1Woocommerce = new OAuthInterceptor.Builder()
//                .consumerKey(BuildConfig.consumer_key)
//                .consumerSecret(BuildConfig.consumer_secret)
//                .build();
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .addInterceptor(interceptor)
//                .addInterceptor(oauth1Woocommerce)
//                // Interceptor oauth1Woocommerce added
//                .build();
//
//        Retrofit mRetrofit = new Retrofit.Builder()
//                .baseUrl(getString(R.string.base_url))
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(client)
//                .build();
//
//
//        ApiInterface apiInterface = mRetrofit.create(ApiInterface.class);
//
//        Call<List<Product_Response>> getProducts = apiInterface.getProducts();
//        getProducts.enqueue(new Callback<List<Product_Response>>() {
//            @Override
//            public void onResponse(Call<List<Product_Response>> call, Response<List<Product_Response>> response) {
//                progressDialog.dismiss();
//                if (response.body() != null || response.code() == 200) {
//
//                    for (int i = 0; i < response.body().size(); i++) {
//                        product.add(response.body().get(i));
//                    }
//
//                    Toast.makeText(getActivity(), product.get(0).getName(), Toast.LENGTH_SHORT).show();
//
//                    Gson gson = new Gson();
//
//                    String json_product = gson.toJson(product);
//
//                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(
//                            Constants.APP_NAME, 0).edit();
//                    editor.putString(Constants.PRODUCT_RESPONSE, json_product.toString());
//
//                    editor.commit();
//
//                    Log.e("Product Count ", String.valueOf(product.size()));
//
//                    productList_adapter = new ProductList_Adapter(getActivity(), product);
//
//
////
//                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
//                    recycler_vw.setLayoutManager(mLayoutManager);
//                    recycler_vw.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
//                    recycler_vw.setItemAnimator(new DefaultItemAnimator());
//                    recycler_vw.setAdapter(productList_adapter);
//
////                    recycler_vw
////                            .setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//
//                    RecyclerView.LayoutManager mLayoutManagers = new GridLayoutManager(getActivity(), 2);
//                    recyclerView.setLayoutManager(mLayoutManagers);
//                    recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
//                    recyclerView.setItemAnimator(new DefaultItemAnimator());
//                    recyclerView.setAdapter(productList_adapter);
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Product_Response>> call, Throwable t) {
//                call.cancel();
//                progressDialog.dismiss();
//            }
//        });
//
//
//    }
//
    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.tv_bestproduct_vwall:
                editor.putString(Pref_Data.FILTER_RESPONSE,"");
                editor.commit();
                Intent productlist=new Intent(getActivity(),FilterProductList.class);
                productlist.putExtra("flag",true);
                startActivity(productlist);
                break;


        }

    }
//
//    private void clearBackStack() {
//        Log.d("Clearing backStack", "clear");
//        FragmentManager manager = getActivity().getSupportFragmentManager();
//        if (manager.getBackStackEntryCount() > 0) {
//            if (BuildConfig.DEBUG) {
//                for (int i = 0; i < manager.getBackStackEntryCount(); i++) {
//                    Timber.d("BackStack content_%d= id: %d, name: %s", i, manager.getBackStackEntryAt(i).getId(), manager.getBackStackEntryAt(i).getName());
//                }
//            }
//            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
//            manager.popBackStackImmediate(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        }
//        Timber.d("backStack cleared.");
////        TODO maybe implement own fragment backStack handling to prevent banner fragment recreation during clearing.
////        http://stackoverflow.com/questions/12529499/problems-with-android-fragment-back-stack
//    }
////
//    @Override
//    public void getProductList(final Product_Resp prod_resp) {
//        getActivity().runOnUiThread(new Runnable() {
//            final ArrayList<Product_Data> product = new ArrayList<Product_Data>();
//            @Override
//            public void run() {
//                product.clear();
//                for (int i = 0; i < prod_resp.getProducts().size(); i++) {
//                    product.add(prod_resp.getProducts().get(i));
//                }
//                //TODO for Recommended Product temp.
//                    Constants.prod_resp.clear();
//                    Constants.prod_resp.addAll(product);
//                productList_adapter = new ProductList_Adapter(getActivity(), product);
////
////
////
//                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
//                recycler_vw.setLayoutManager(mLayoutManager);
//                recycler_vw.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
//                recycler_vw.setItemAnimator(new DefaultItemAnimator());
//                recycler_vw.setAdapter(productList_adapter);
//
////                    recycler_vw
////                            .setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//
//                RecyclerView.LayoutManager mLayoutManagers = new GridLayoutManager(getActivity(), 2);
//                recyclerView.setLayoutManager(mLayoutManagers);
//                recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                recyclerView.setAdapter(productList_adapter);
////
//            }
//
//        });
//    }

    @Override
    public void getHomeData(final Home_Slider_Resp homedataResp) {
        getActivity().runOnUiThread(new Runnable() {
            ArrayList<Slider_Images> slider_images = new ArrayList<>();
            final ArrayList<Product_Recommend> product = new ArrayList<Product_Recommend>();

            @Override
            public void run() {

                slider_images.clear();
                for (int i = 0; i < homedataResp.getSlider().size(); i++) {
                    slider_images.add(homedataResp.getSlider().get(i));
                }
                for (int i = 0; i < slider_images.size(); i++) {
                    alImages.add(slider_images.get(i).getImageUrl());
                }
                myPagerAdapter = new MyPagerAdapter(alImages);
                vPager.setAdapter(myPagerAdapter);

                product.clear();
                for (int i = 0; i < homedataResp.getProduct().size(); i++) {
                    product.add(homedataResp.getProduct().get(i));
                }
                recom_productList_adapter = new Prod_RecommendedAdapter(getActivity(), product);
                LinearLayoutManager secondManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(secondManager);
                recyclerView.setAdapter(recom_productList_adapter);
            }
        });
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

    //
//
    private class MyPagerAdapter extends PagerAdapter {

        ArrayList<String> alImages = new ArrayList<String>();
        DisplayImageOptions options;
        public ImageLoader imageLoader = ImageLoader.getInstance();
        private String currentPage;
        private LayoutInflater inflater;

        public MyPagerAdapter(ArrayList<String> alImages) {

            this.alImages = alImages;
            inflater = LayoutInflater.from(getActivity());
            // this.currentPage=currentPage;
            // imageLoader.init(ImageLoaderConfiguration.createDefault(this.context));
            // TODO Auto-generated constructor stub
            imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));

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
                    // this will log the page number that was click
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

        //
//    }
//
//    /**
//     * RecyclerView item decoration - give equal margin around grid item
//     */
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
            /**
             * Converting dp to pixel
             */


        }
    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    }





