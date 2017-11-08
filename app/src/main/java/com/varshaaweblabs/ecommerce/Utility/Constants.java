package com.varshaaweblabs.ecommerce.Utility;


import com.varshaaweblabs.ecommerce.Cart.Model.Cart_Product;
import com.varshaaweblabs.ecommerce.Filter.Model.Filter_Resp;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Category_Resp;
import com.varshaaweblabs.ecommerce.HomeScreenDrawer.Model.Product_Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mohsin on 5/6/17.
 */

public class Constants {
    public static final String BASE_URL = "http://prestashop.varshaawebdemo.com/api/";
    public static final String CONSUMER_KEY = "consumer_key";
    public static final String CONSUMER_SECRET = "consumer_secret";
    public static final String SIGNATURE_METHOD = "oauth_signature_method";
    public static final String TIMESTAMP = "oauth_timestamp";
    public static final String NONCE = "oauth_nonce";
    public static final String OAUTH_VERSION = "oauth_version";
    public static final ArrayList<Category_Resp> allCategory_resp = new ArrayList<>();

    public static String CONTENT_TYPE = "Content-Type";
    public static String CONTENT_VALUE = "application/json";
    public static String LABEL = "";
    public static String VALUE = null;
    public static String FULLPRODUCT_ID = "";
    public static String PROD_COMBINATIONID = "";
    public static String SIZE_ID = "";
    public static String COLOR_ID = "";
    public static int selection = 0;
    public static String prod_attribute_id = "";
    public static String CART_ID = "cart_id";
    public static String CUSTOMIZATION_ID = "cust_id";
    public static String PAYPAL_TOKEN = "paypal_token";
    public static String TRANSACTION_ID = "paypal_transaction";


    public static final String PAYPAL_CLIENT_ID = "AdVsqemLR7wb817WklDh3e1DLS0NaOkQVwn7kk1QhPT8yQXwjZ0IKXL3kuk1rlB7F-ttgQHx-mzilAby";
    public static HashMap<String, List<String>> filter_hashmap = new HashMap<>();//Hashmap for multiple selected Filter

    public static String FILTER_URL = "";//to make the full url of constants
    public static String FILTER_SORT = "";

    public static int POSITION = 0;

    public static String APP_NAME = "MyStore";
    public static String LOGIN_RESPONSE = "login_response";
    public static String USER_PHOTO = "avatar_url";
    public static String USER_ID = "user_id";
    public static String BANNER = "banner";
    public static String TITLE = "title";


    public static String CART_COUNT = "cart_count";
    public static String SUB_CATEGORY_RESPONSE = "";
    public static ArrayList<Product_Data> prod_resp = new ArrayList<>();
    public static ArrayList<Cart_Product> order_prod = new ArrayList<>();
    public static String ADDRESS_ID = "address_id";
    public static String STATENAME = "state_name";
    public static String STATE_ID = "state_id";
    public static String COUNTRYNAME = "country_name";
    public static String COUNTRY_ID = "country_id";
    public static String PAY_OPTION_PAYMENT = "pay_option_payment";
    public static String PAY_OPTION_MODULE = "pay_option_module";
    public static String PAY_ID = "pay_id";
    public static String ORDER_ID = "order_id";
    public static String SELECTED_QTY = "";
    public static String CAT_SLIDER_IMAGE = "";
//    public static ArrayList<Category_Response> subcategory_list = new ArrayList<>();
//    public static ArrayList<Category_Response> subcategory_display_list = new ArrayList<>();

    public static int parent_id = 0;
    public static Boolean flag_sub_category = false;

    public static Class<?> ADDRESS_CLASSNAME;//TODO for Save ADD Intent

    public static Filter_Resp filterResp = new Filter_Resp();//Store the whole data of Filter Resp
}
