package com.varshaaweblabs.ecommerce.Utility;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


public class CheckNetWork {
	Context mContext;

	public static boolean CheckNetWork(Context context) {
		// TODO Auto-generated constructor stub

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();
		
		
		return isConnected;
	}

	public static void ShowNoNetwork(Context context) {
		// TODO Auto-generated constructor stub

		Toast.makeText(context,"No Internet Connected",Toast.LENGTH_LONG).show();
	}
	
}
