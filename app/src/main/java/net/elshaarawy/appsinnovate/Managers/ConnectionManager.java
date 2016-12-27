package net.elshaarawy.appsinnovate.Managers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by elshaarawy on 25-Dec-16.
 */

public class ConnectionManager {
    private static ConnectionManager connectionManager = new ConnectionManager();

    private ConnectionManager() {
    }
    public static ConnectionManager getInstance(){
        return connectionManager;
    }

    public  boolean isOnline(Activity activity) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return mConnectivityManager.getActiveNetworkInfo() != null;
    }
}
