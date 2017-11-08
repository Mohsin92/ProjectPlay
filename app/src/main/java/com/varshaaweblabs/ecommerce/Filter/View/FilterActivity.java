package com.varshaaweblabs.ecommerce.Filter.View;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.varshaaweblabs.ecommerce.Base.BaseActivity;
import com.varshaaweblabs.ecommerce.Filter.Adapter.Active_Filter_Adapter;
import com.varshaaweblabs.ecommerce.Filter.Adapter.FilterAdapter;
import com.varshaaweblabs.ecommerce.Filter.Adapter.FilterCategory_Adapter;
import com.varshaaweblabs.ecommerce.Filter.Adapter.Sub_FilterAdapter;
import com.varshaaweblabs.ecommerce.Filter.Model.Active_Filter;
import com.varshaaweblabs.ecommerce.Filter.Model.Filter_Resp;
import com.varshaaweblabs.ecommerce.Filter.Model.Filters;
import com.varshaaweblabs.ecommerce.Filter.Model.Product;
import com.varshaaweblabs.ecommerce.Filter.Model.Sub_Filter;
import com.varshaaweblabs.ecommerce.Filter.Presenter.FilterPresenter;
import com.varshaaweblabs.ecommerce.Filter.Presenter.IFilterInterface;
import com.varshaaweblabs.ecommerce.Filter.Presenter.ProductPresenter;
import com.varshaaweblabs.ecommerce.R;
import com.varshaaweblabs.ecommerce.Utility.BreadcrumbLayout;
import com.varshaaweblabs.ecommerce.Utility.Constants;
import com.varshaaweblabs.ecommerce.Utility.Pref_Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dinesh on 8/9/17.
 */

public class FilterActivity extends BaseActivity implements FilterView.View, IFilterInterface, View.OnClickListener, Active_Filter_Adapter.Helper {

    FilterPresenter filterPresenter;
    ListView filter_list, lv_active_filters;
    ListView frag_list, filter_cat_list;
    CheckedTextView ctg_check;
    Button btn_apply, btn_reset;
    FilterAdapter filter_adapter;
    Sub_FilterAdapter sub_filteradapter;
    Active_Filter_Adapter active_filter_adapter;
    FilterCategory_Adapter filterCategory_adapter;
    String widget;
    String label;
    Boolean prod_flag = false;
    int pos_j = 0;
    Boolean flag_select = false;
    ArrayList<String> tag_id = new ArrayList<>();

    SharedPreferences pref_filter;
    SharedPreferences.Editor editor_filter;

    ArrayList<Sub_Filter> subfil_response = new ArrayList<Sub_Filter>();
    ArrayList<Active_Filter> active_filterResponse = new ArrayList<>();
    ArrayList<Product> prod = new ArrayList<>();
    ArrayList<Filters> cat_response = new ArrayList<Filters>();
    ArrayList<String> resultListName = new ArrayList<>();
    ProductPresenter filterProductPresenter;
    int selecteditem = 0;
    boolean flag = false;
    boolean flag_apply = false;
    int posi = 0;
    String pass_selected_data = null;
    private BreadcrumbLayout breadcrumbLayout;
    SlidingDrawer slidingdrawer;
    LinearLayout ll_iv_close, ll_filter_view;
    LinearLayout SlidingButton, ll_filter_main, ll_title_filter, ll_filter_lay, ll_sliding_content;
    RelativeLayout rl_slider;
    ImageView iv_arrow;
    boolean flag_drawer = false;
    TextView tv_nofilter;
    Animation animationUp, animationdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filterPresenter = new FilterPresenter(this, this);
        filterPresenter.attachView(this);
//        filterProductPresenter=new ProductPresenter(this,FilterActivity.this);
        setContentView(R.layout.activity_filter);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        filter_list = (ListView) findViewById(R.id.filter_list);
        frag_list = (ListView) findViewById(R.id.frag_list);
        filter_cat_list = (ListView) findViewById(R.id.filter_cat_list);
        btn_apply = (Button) findViewById(R.id.btn_apply);
        btn_reset = (Button) findViewById(R.id.btn_clear);
        breadcrumbLayout = (BreadcrumbLayout) findViewById(R.id.breadcrumb);
        slidingdrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
        SlidingButton = (LinearLayout) findViewById(R.id.handle);
        ll_title_filter = (LinearLayout) findViewById(R.id.ll_title_filter);
        ll_filter_main = (LinearLayout) findViewById(R.id.ll_filter_main);
        ll_iv_close = (LinearLayout) findViewById(R.id.ll_iv_close);
        ll_filter_view = (LinearLayout) findViewById(R.id.ll_filter_view);
        lv_active_filters = (ListView) findViewById(R.id.lv_active_filters);
        ll_filter_lay = (LinearLayout) findViewById(R.id.ll_filter_lay);
        rl_slider = (RelativeLayout) findViewById(R.id.rl_slider);
        iv_arrow = (ImageView) findViewById(R.id.iv_arrow);
        ll_sliding_content = (LinearLayout) findViewById(R.id.ll_sliding_content);
        tv_nofilter = (TextView) findViewById(R.id.tv_nofilter);

        animationdown = AnimationUtils.loadAnimation(this, R.anim.top_bottom);
        animationUp = AnimationUtils.loadAnimation(this, R.anim.bottom_top);


        ll_filter_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingdrawer.close();
            }
        });

        ll_iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pref_filter = this.getSharedPreferences(Pref_Data.APP_NAME, MODE_PRIVATE);
        editor_filter = pref_filter.edit();

        ll_title_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag_drawer) {
                    slidingdrawer.open();
                    flag_drawer = true;
                } else {

                    slidingdrawer.close();
                    flag_drawer = false;
                }
            }
        });


        Gson gson = new Gson();
        Filter_Resp filter_resp = gson.fromJson(pref_filter.getString(Pref_Data.FILTER_RESPONSE, ""), Filter_Resp.class);


        if (filter_resp != null) {
            if (filter_resp.getMain_filters().getActiveFilter() != null) {
                Filter_Data(filter_resp);
            } else {
                try {
                    filterPresenter.getFilters(pass_selected_data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            try {
                filterPresenter.getFilters(pass_selected_data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        breadcrumbLayout.setOnBreadcrumbSelectedListener(new BreadcrumbLayout.OnBreadcrumbSelectedListener() {
            @Override
            public void onBreadcrumbSelected(BreadcrumbLayout.Breadcrumb crumb) {
                Toast.makeText(FilterActivity.this, "Selected==> " + crumb.getTag(), Toast.LENGTH_SHORT).show();
                Constants.VALUE = crumb.getTag().toString();

                try {
                    filterPresenter.getFilters(pass_selected_data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBreadcrumbUnselected(BreadcrumbLayout.Breadcrumb crumb) {
                Toast.makeText(FilterActivity.this, "UnSelected", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onBreadcrumbReselected(BreadcrumbLayout.Breadcrumb crumb) {

                Toast.makeText(FilterActivity.this, "ReSelected==> " + crumb.getTag() + "  "
                        + crumb.getPosition(), Toast.LENGTH_SHORT).show();


                Constants.VALUE = crumb.getTag().toString();

                try {
                    filterPresenter.getFilters(pass_selected_data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        btn_apply.setOnClickListener(this);
        btn_reset.setOnClickListener(this);

    }

    @Override
    public void showProgress() {
        super.showProgress();
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
    public void Filter_Data(final Filter_Resp filter_resp) {
        subfil_response.clear();
        cat_response.clear();
        tag_id.clear();
        resultListName.clear();
        active_filterResponse.clear();
        Constants.filterResp = filter_resp;
        prod.clear();
        Constants.filter_hashmap.clear();
        subfil_response.addAll(filter_resp.getMain_filters().getSub_filter());
        prod.addAll(filter_resp.getProduct());
        if (filter_resp.getMain_filters().getActiveFilter().size() > 0) {
            active_filterResponse.addAll(filter_resp.getMain_filters().getActiveFilter());
        }

        for (int j = 0; j < subfil_response.size(); j++) {
            cat_response.addAll(subfil_response.get(j).getFilters());
        }


        Gson gson = new Gson();
        String filter_data = gson.toJson(filter_resp);
        editor_filter.putString(Pref_Data.FILTER_RESPONSE, filter_data);
        editor_filter.commit();

        if (flag_apply) {
            Intent productlist = new Intent(FilterActivity.this, FilterProductList.class);
            startActivity(productlist);
            flag_apply = false;
        }


        FilterActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {


                for (int i = 0; i < subfil_response.size(); i++) {
                    filter_adapter = new FilterAdapter(FilterActivity.this, subfil_response);
                    filter_list.setAdapter(filter_adapter);
                    filter_list.setChoiceMode(filter_list.CHOICE_MODE_SINGLE);


                }


                slidingdrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {

                    @Override
                    public void onDrawerOpened() {
                        iv_arrow.setImageResource(R.mipmap.up_arrow);

                        ll_iv_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                slidingdrawer.close();
                            }
                        });

                        if (active_filterResponse.size() == 0) {
                            tv_nofilter.setVisibility(View.VISIBLE);
                            lv_active_filters.setVisibility(View.GONE);
                            slidingdrawer.setBackgroundColor(Color.parseColor("#d4d4d4"));

                        } else {
                            tv_nofilter.setVisibility(View.GONE);
                            lv_active_filters.setVisibility(View.VISIBLE);
                            slidingdrawer.setBackgroundColor(Color.parseColor("#ffffff"));
                            final Active_Filter_Adapter.Helper active_filter_helper = new Active_Filter_Adapter.Helper() {
                                @Override
                                public void OnTagSelected(ArrayList id) {
                                    tag_id.clear();
                                    if (id.size() != 0) {
                                        for (int i = 0; i < id.size(); i++) {
                                            tag_id.add(id.get(i).toString());
                                        }
                                    }

                                }

                            };


                            active_filter_adapter = new Active_Filter_Adapter(FilterActivity.this, active_filterResponse, active_filter_helper);
                            lv_active_filters.setAdapter(active_filter_adapter);

                        }

                    }
                });

                slidingdrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {

                    public void onDrawerClosed() {
                        ll_iv_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
                        iv_arrow.setImageResource(R.mipmap.arrow_down);
                        slidingdrawer.setBackgroundColor(Color.parseColor("#00000000"));
                        for (int k = 0; k < tag_id.size(); k++) {
                            String id = String.valueOf(tag_id.get(k));
                            getClearFilter(id);

                        }


                    }


                });


                filter_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                        label = subfil_response.get(pos).getLabel();
                        posi = pos;
                        setFilter(filter_resp, pos);
                        for (int j = 0; j < subfil_response.get(pos).getFilters().size(); j++) {
                            if (subfil_response.get(pos).getFilters().get(j).getActive()) {
                                sub_filteradapter.toggleChecked(j);
                            }
                        }

                    }
                });
                setFilter(filter_resp, 0);
                filter_list.setActivated(true);
                filter_list.setItemChecked(0, true);


                if (subfil_response.size() > posi) {
                    for (int i = 0; i < subfil_response.size(); i++) {
                        if (subfil_response.get(i).getLabel().equalsIgnoreCase(label)) {
                            setFilter(filter_resp, i);
                            filter_list.requestFocus();
//                            filter_list.setActivated(true);
                            filter_list.setItemChecked(i, true);
                            filter_list.setSelection(i);


                            for (int j = 0; j < subfil_response.get(i).getFilters().size(); j++) {
                                if (subfil_response.get(i).getFilters().get(j).getActive()) {
                                    sub_filteradapter.toggleChecked(j);
                                }
                            }
                        }
                    }

                } else {
                    setFilter(filter_resp, 0);
                    filter_list.setItemChecked(0, true);
                    filter_list.setActivated(true);
                    for (int j = 0; j < subfil_response.get(0).getFilters().size(); j++) {
                        if (subfil_response.get(0).getFilters().get(j).getActive()) {
                            sub_filteradapter.toggleChecked(j);
                        }
                    }
                }


                if (flag) {
                    flag = false;
                    setFilter(filter_resp, 0);
                    filter_list.setItemChecked(0, true);
                }

                if (active_filterResponse.size() > 0) {
                    for (int i = 0; i < active_filterResponse.size(); i++) {
                        String label = active_filterResponse.get(i).getLabel();
                        Toast.makeText(FilterActivity.this, "Selected " + label, Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });


    }

    private void getClearFilter(String id) {
        for (int j = 0; j < active_filterResponse.size(); j++) {
            for (int i = 0; i < active_filterResponse.get(j).getList_active_filters().size(); i++) {
                if (active_filterResponse.get(j).getList_active_filters().get(i).getValue().toString().equalsIgnoreCase(id)) {
                    label = active_filterResponse.get(j).getList_active_filters().get(i).getLabel();
                    Constants.FILTER_URL = Constants.FILTER_URL.replace("-" + label, "");
                    Log.e("Filter_url123", Constants.FILTER_URL);
                }
            }
        }
        if (!label.equalsIgnoreCase("")) {
            getfilter_criteria();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        hideProgress();

    }

    public void setFilter(final Filter_Resp filter_resp, int pos) {
        widget = filter_resp.getMain_filters().getSub_filter().get(pos).getWidgetType();

        if (resultListName.size() > 0) {
            Constants.filter_hashmap.clear();
            final ArrayList<String> filter_array = new ArrayList<>();
            filter_array.clear();
            Constants.filter_hashmap.put(Constants.LABEL, resultListName);
            filter_array.addAll(Constants.filter_hashmap.get(Constants.LABEL));
//            Log.e("Filter_Array", filter_array.toString());
            String url = getquerystring(filter_array);
            Constants.FILTER_URL = Constants.FILTER_URL + "/" + Constants.LABEL + "-" + url;
            Log.e("Filter_url", Constants.FILTER_URL);
            try {
                filterPresenter.getFilters(Constants.FILTER_URL);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (widget.equalsIgnoreCase("checkboxes")) {
            sub_filteradapter = new Sub_FilterAdapter(FilterActivity.this, filter_resp.getMain_filters().getSub_filter().get(pos).getFilters());
            frag_list.setAdapter(sub_filteradapter);
            frag_list.setOnItemClickListener(myOnItemClickListener);


        } else if (widget.equalsIgnoreCase("list")) {
            filterCategory_adapter = new FilterCategory_Adapter(FilterActivity.this, filter_resp.getMain_filters().getSub_filter().get(pos).getFilters());
            frag_list.setAdapter(filterCategory_adapter);
            frag_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Constants.VALUE = filter_resp.getMain_filters().getSub_filter().get(0).getFilters().get(i).getValue();
                    breadcrumbLayout.setVisibility(View.VISIBLE);
                    btn_apply.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    p.weight = (float) 0.11;

                    btn_reset.setLayoutParams(p);

                    if (breadcrumbLayout.getCrumbCount() == 0) {
                        breadcrumbLayout.addCrumb(breadcrumbLayout.newCrumb().setText("Home").setTag(filter_resp.getMain_filters().getSub_filter().get(0).getFilters().get(i).getValue()));
                        if (breadcrumbLayout.getSelectedCrumbPosition() == 0) {
                            breadcrumbLayout.getCrumbAt(0).setTag(2);
//                            breadcrumbLayout.addCrumb(breadcrumbLayout.newCrumb().setText("Home").setTag(2));
                        }
                    }
                    breadcrumbLayout.addCrumb(breadcrumbLayout.newCrumb().setText(filter_resp.getMain_filters().getSub_filter().get(0).getFilters().get(i).getLabel()).setTag(filter_resp.getMain_filters().getSub_filter().get(0).getFilters().get(i).getValue()));

                    try {
                        filterPresenter.getFilters(pass_selected_data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            });


        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_apply:
                getfilter_criteria();
                flag_apply = true;
                break;

            case R.id.btn_clear:
                Constants.VALUE = null;
                Constants.FILTER_URL = null;
                active_filterResponse.clear();
                editor_filter.putString(Pref_Data.FILTER_RESPONSE, "");
                editor_filter.commit();
                flag = true;
                breadcrumbLayout.removeAllCrumbs();
                breadcrumbLayout.setVisibility(View.GONE);
                try {
                    filterPresenter.getFilters(null);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


        }

    }

    AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            if (flag_drawer) {
                slidingdrawer.close();
            }
            btn_apply.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            p.weight = (float) 0.11;
            btn_reset.setLayoutParams(p);
//            btn_reset.setVisibility(View.VISIBLE);
            sub_filteradapter.toggleChecked(position);
            List<String> resultListIDS = sub_filteradapter.getCheckedItemsID();
            resultListName = sub_filteradapter.getCheckedItemsNAMES();
//            String selected_data = resultListName.toString().substring(1, resultListName.toString().length() - 1);
//            pass_selected_data = selected_data.replace(",", "-").replace(" ", "");
            String resultListNAMES = sub_filteradapter.getCheckedNAMES();


//            Toast.makeText(FilterActivity.this, "Selected " + resultListName, Toast.LENGTH_SHORT).show();

//            setTags(resultListName, position);


        }
    };


    public String getquerystring(ArrayList<String> filter_array) {
        String selected_data = filter_array.toString().substring(1, filter_array.toString().length() - 1);
        pass_selected_data = selected_data.replace("," + " ", "-");
        return pass_selected_data;
    }


    //    Function for final call
    public void getfilter_criteria() {
        if (resultListName.size() > 0) {
            Constants.filter_hashmap.clear();
            final ArrayList<String> filter_array = new ArrayList<>();
            filter_array.clear();
            Constants.filter_hashmap.put(Constants.LABEL, resultListName);
            filter_array.addAll(Constants.filter_hashmap.get(Constants.LABEL));
//            Log.e("Filter_Array", filter_array.toString());
            String url = getquerystring(filter_array);
            Constants.FILTER_URL = Constants.FILTER_URL + "/" + Constants.LABEL + "-" + url;
//

            try {
                filterPresenter.getFilters(Constants.FILTER_URL);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                filterPresenter.getFilters(Constants.FILTER_URL);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public void OnTagSelected(ArrayList id) {

    }
}
