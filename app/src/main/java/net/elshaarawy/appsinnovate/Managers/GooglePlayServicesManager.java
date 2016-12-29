package net.elshaarawy.appsinnovate.Managers;

import android.app.Activity;
import android.content.Context;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.elshaarawy.appsinnovate.R;
import net.elshaarawy.appsinnovate.ShopdCoordinates;

import java.util.HashMap;
import java.util.List;

/**
 * Created by elshaarawy on 28-Dec-16.
 */

public class GooglePlayServicesManager {
    private static GooglePlayServicesManager managerInstance;
    private Activity activity;
    private GoogleApiAvailability apiAvailability;
    private int isAvailable;
    private boolean userResolvableError;


    public GooglePlayServicesManager(Activity activity) {
        this.activity = activity;
        apiAvailability = GoogleApiAvailability.getInstance();
        isAvailable = apiAvailability.isGooglePlayServicesAvailable(activity);
        userResolvableError = apiAvailability.isUserResolvableError(isAvailable);

    }

    public boolean isPlayServicesAvailable() {

        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (userResolvableError) {
            apiAvailability.getErrorDialog(activity, isAvailable, 9001).show();
        } else {
            Toast.makeText(activity, "Error Can't Connect", Toast.LENGTH_LONG).show();
        }
        return false;
    }

}
