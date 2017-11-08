package com.varshaaweblabs.ecommerce.HomeScreenDrawer.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.varshaaweblabs.ecommerce.Base.BaseFragment;
import com.varshaaweblabs.ecommerce.Filter.View.FilterProductList;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Adapter.ExpandableList_Adapter;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Adapter.SubCategory_Adapter;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_Data;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_Resp;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_id;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Presenter.IFragmentInterface;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Presenter.ItemFragmentPresenter;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.View.HomeView;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ItemFragment extends BaseFragment implements AdapterView.OnClickListener, IFragmentInterface, HomeView.View {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    ArrayList<Category_Data> sub_category = new ArrayList<>();
    ArrayList<Category_Data> child_category = new ArrayList<>();
    HashMap<String, List<Category_Data>> expandableListCategory;
    SubCategory_Adapter subCategory_adapter;
    ImageView iv_sub_banner;
    ItemFragmentPresenter frag_presenter;
    ArrayList cat_id = new ArrayList();
    String cat_name;
    ExpandableListView expListView;
    ExpandableList_Adapter listAdapter;
    List<String> listDataHeader;
    List<String> child = new ArrayList<String>();
    HashMap<String, List<String>> listDataChild;
    public ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    //
//    // TODO: Customize parameters
    private int mColumnCount = 1;

    //    private OnListFragmentInteractionListener mListener;
//
//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(Integer columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frag_presenter = new ItemFragmentPresenter(getContext(), ItemFragment.this);
        frag_presenter.attachView(ItemFragment.this);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            Constants.parent_id = mColumnCount;
            Log.e("Parent Id ", String.valueOf(Constants.parent_id));
            Toast.makeText(getContext(), "Parent Id:" + Constants.parent_id, Toast.LENGTH_SHORT).show();
        }

        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));

        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true).considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .displayer(new FadeInBitmapDisplayer(300)).build();



    }

    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
//        progressDialog = Utils.generateProgressDialog(getActivity(), false);
        iv_sub_banner = (ImageView) view.findViewById(R.id.iv_sub_banner);

        expListView = (ExpandableListView) view.findViewById(R.id.rv_subcategorylist);


        frag_presenter.getListCategory();


//        rv_subcategorylist.setOnItemClickListener((AdapterView.OnItemClickListener) getContext());
//        rv_subcategorylist.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv_subcategorylist, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Category_Data category_response = sub_category.get(position);
//                Constants.subcategory_display_list.clear(); //clear arraylist to get fresh data.
//
//                for (int i = 0; i < Constants.subcategory_list.size(); i++) {
//                    if (category_response.getId().equals(Constants.subcategory_list.get(i).getParent())) {
//                        Log.e("Sub Cateogry Listed: ", Constants.subcategory_list.get(i).getName());
//                        Constants.flag_sub_category = true;
//                        Constants.subcategory_display_list.add(Constants.subcategory_list.get(i));  //add data into arraylist to display in activity that is SubCategory.java
//                    }
//                }
//                Toast.makeText(getActivity(), category_response.getName() + " is selected!", Toast.LENGTH_SHORT).show();
//
//                if (Constants.flag_sub_category) {
//                    Intent in = new Intent(getActivity(), SubCategory.class);
//                    in.putExtra(Constants.BANNER, category_response.getImage().getSrc());
//                    in.putExtra(Constants.TITLE, category_response.getName());
//                    startActivity(in);
//                    Constants.flag_sub_category = false;
//                    getActivity().overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
//                }
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));
//
//
//        if (Constants.sub_category.size() != 0) {
//
//            for (int i = 0; i < Constants.sub_category.size(); i++) {
//                if (Constants.sub_category.get(i).getId().equals(mColumnCount)) {
//                    Glide.with(getActivity()).load(Constants.sub_category.get(i).getImage().getSrc()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv_sub_banner);
//                }
//            }
//        }
//

//
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void getList(final Category_Resp response) {
        final ArrayList<Category_id> id = new ArrayList<>();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                for(int i=0;i<Constants.allCategory_resp.size();i++){
                    for(int j=0;j<Constants.allCategory_resp.get(i).getCategories().size();j++){
                        if(Constants.allCategory_resp.get(i).getCategories().get(j).getId()==Constants.parent_id){
                            Constants.CAT_SLIDER_IMAGE=Constants.allCategory_resp.get(i).getCategories().get(j).getSliderImage();
                        }
                    }

                }

                imageLoader.getInstance().displayImage(Constants.CAT_SLIDER_IMAGE,
                        iv_sub_banner, options, new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingStarted(String imageUri, View view) {
//                        spinner.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onLoadingComplete(String imageUri,
                                                          View view, Bitmap loadedImage) {
//                        spinner.setVisibility(View.GONE);
                            }
                        });
                cat_id.clear();
                listDataHeader = new ArrayList<String>();
                listDataChild = new HashMap<String, List<String>>();
                for (int k = 0; k < Constants.allCategory_resp.get(0).getCategories().size(); k++) {
                    cat_id.add(Constants.allCategory_resp.get(0).getCategories().get(k).getId().toString());
                }

                for (int i = 0; i < response.getCategories().size(); i++) {
//                    Toast.makeText(getContext(), "Products " + response.getCategories().get(i).getName(), Toast.LENGTH_SHORT).show();
                    listDataHeader.add(response.getCategories().get(i).getName());
                    if (response.getCategories().get(i).getAssociations() != null) {
                        if (response.getCategories().get(i).getAssociations().getCategories() != null) {
                            for (int j = 0; j < response.getCategories().get(i).getAssociations().getCategories().size(); j++) {
                                for (int x = 0; x < cat_id.size(); x++) {
                                    if (cat_id.get(x).equals(response.getCategories().get(i).getAssociations().getCategories().get(j).getId())) {
//                                        Toast.makeText(getContext(), "Children Name:- " + response.getCategories().get(i).getAssociations().getCategories().get(j).getName(), Toast.LENGTH_SHORT).show();
                                        child.add(response.getCategories().get(i).getAssociations().getCategories().get(j).getName());
                                    }

                                }

                            }

                        } else {
                            Toast.makeText(getContext(), "No Category Found", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getContext(), "No Category Found", Toast.LENGTH_LONG).show();
                    }
                    listDataChild.put(listDataHeader.get(i), child);
                    child = new ArrayList<String>();
                }

                listAdapter = new ExpandableList_Adapter(getContext(), listDataHeader, listDataChild);
                expListView.setAdapter(listAdapter);
               expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                   @Override
                   public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                       Toast.makeText(getContext(),"Selected " +response.getCategories().get(groupPosition).getAssociations().getCategories().get(childPosition).getName(),Toast.LENGTH_SHORT).show();
                       Constants.VALUE=response.getCategories().get(groupPosition).getAssociations().getCategories().get(childPosition).getId();
                       Intent prod=new Intent(getContext(), FilterProductList.class);
                       prod.putExtra("id",Constants.VALUE);
                       prod.putExtra("flag",true);
                       startActivity(prod);

                       return false;
                   }
               });


                // setting list adapter


            }

        });
    }
//
//    private void getSubCategoryAPI() {
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
//        Call<List<Category_Response>> getSubCategory = apiInterface.getsubCategory(String.valueOf(mColumnCount));
//        getSubCategory.enqueue(new Callback<List<Category_Response>>() {
//            @Override
//            public void onResponse(Call<List<Category_Response>> call, Response<List<Category_Response>> response) {
//                progressDialog.dismiss();
//                if (response.body() != null || response.code() == 200) {
//
//                    for (int i = 0; i < response.body().size(); i++) {
//                        sub_category.add(response.body().get(i));
//                    }
//
//                    Gson gson = new Gson();
//
//                    String json_product = gson.toJson(sub_category);
//
//                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(
//                            Constants.APP_NAME, 0).edit();
//                    editor.putString(Constants.SUB_CATEGORY_RESPONSE, json_product.toString());
//
//                    editor.apply();
//
//                    Log.e("Category Count ", String.valueOf(sub_category.size()));
//
//                    subCategory_adapter = new SubCategory_Adapter(getActivity(), sub_category);
//
//
////
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//                    rv_subcategorylist.setLayoutManager(mLayoutManager);
////                    rv_subcategorylist.addItemDecoration(new Homepage.GridSpacingItemDecoration(2, dpToPx(5), true));
//                    rv_subcategorylist.setItemAnimator(new DefaultItemAnimator());
//                    rv_subcategorylist.setAdapter(subCategory_adapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Category_Response>> call, Throwable t) {
//                progressDialog.dismiss();
//                call.cancel();
//            }
//        });
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnListFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onListFragmentInteraction(DummyItem item);
//    }
}
