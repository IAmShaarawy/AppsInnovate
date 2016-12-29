package net.elshaarawy.appsinnovate;

import android.app.Application;

import io.realm.Realm;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by elshaarawy on 27-Dec-16.
 */

public class TheApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
