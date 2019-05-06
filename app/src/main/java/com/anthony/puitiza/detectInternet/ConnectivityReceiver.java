package com.anthony.puitiza.detectInternet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ConnectivityReceiver extends BroadcastReceiver {


    static Context mcontext;
    public static ConnectivityReceiverListener connectivityReceiverListener;


    public ConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        /*get action name from activity which is trigger the broadcast receiver*/
        String action = intent.getAction();

        /*check action name*/
        if (("android.net.conn.CONNECTIVITY_CHANGE").equals(action)) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null
                    && activeNetwork.isConnectedOrConnecting();
            Log.e("receiver", "on");
            if (connectivityReceiverListener != null) {
                connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
            }

        }

    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
