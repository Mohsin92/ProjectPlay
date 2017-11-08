package com.varshaaweblabs.ecommerce.ProductFullDetails.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Filter.Model.Product;
import com.varshaaweblabs.ecommerce.ProductFullDetails.Model.Product_Recommend;
import com.varshaaweblabs.ecommerce.R;

import java.util.ArrayList;

/**
 * Created by dinesh on 22/9/17.
 */

public class ProductFullImage extends BaseActivity implements View.OnClickListener {

    ArrayList<String> get_prod_fullImages = new ArrayList<String>();
    ArrayList<String> prod_fullImages = new ArrayList<String>();
    ViewPager vPager;
    MyPagerAdapter myPagerAdapter;
    Boolean flag_recomm = true;
    Product prod_imgfull = new Product();
    Product_Recommend prod_recomm_details = new Product_Recommend();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_fullimage);
        vPager = (ViewPager) findViewById(R.id.full_pager);
        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");


        float heightDp = getResources().getDisplayMetrics().heightPixels;
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) vPager.getLayoutParams();
        lp.height = (int) heightDp;

        Intent prod = getIntent();


        flag_recomm = prod.getBooleanExtra("flag_recomm", true);
        get_prod_fullImages = (ArrayList<String>) prod.getSerializableExtra("selected_image");
        myPagerAdapter = new MyPagerAdapter(get_prod_fullImages);
        vPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (get_prod_fullImages.size() == 1) {
            tabLayout.setVisibility(View.GONE);
        } else {
            tabLayout.setupWithViewPager(vPager, true);

        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public void onClick(View view) {

    }

    private class MyPagerAdapter extends PagerAdapter {

        ArrayList<String> prod_fullImages = new ArrayList<String>();
        DisplayImageOptions options;
        public ImageLoader imageLoader = ImageLoader.getInstance();
        private String currentPage;
        private LayoutInflater inflater;

        public MyPagerAdapter(ArrayList<String> prod_fullImages) {

            this.prod_fullImages = prod_fullImages;
            inflater = LayoutInflater.from(ProductFullImage.this);
            // this.currentPage=currentPage;
            // imageLoader.init(ImageLoaderConfiguration.createDefault(this.context));
            // TODO Auto-generated constructor stub
            imageLoader.init(ImageLoaderConfiguration.createDefault(ProductFullImage.this));

        }

        @Override
        public int getCount() {
            return prod_fullImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            View imageLayout = inflater.inflate(R.layout.prop_slider_images,
                    container, false);
            currentPage = prod_fullImages.get(position);
            TouchImageView imageView = (TouchImageView) imageLayout
                    .findViewById(R.id.tv_image);
//            final ProgressBar spinner = (ProgressBar) imageLayout
//                    .findViewById(R.id.loading);

            options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisk(true)
                    .resetViewBeforeLoading(true).considerExifParams(true)
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                    .displayer(new FadeInBitmapDisplayer(300)).build();


            imageLoader.getInstance().displayImage(prod_fullImages.get(position),
                    imageView, options, new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
//                            spinner.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadingComplete(String imageUri,
                                                      View view, Bitmap loadedImage) {
//                            spinner.setVisibility(View.GONE);
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


}
