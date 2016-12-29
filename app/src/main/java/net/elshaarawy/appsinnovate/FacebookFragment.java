package net.elshaarawy.appsinnovate;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import net.elshaarawy.appsinnovate.Activities.MainActivity;
import net.elshaarawy.appsinnovate.Managers.MyPreferenceManager;

import static com.facebook.FacebookSdk.getApplicationContext;
import static net.elshaarawy.appsinnovate.Managers.MyPreferenceManager.Keys.*;

/**
 * Created by elshaarawy on 29-Dec-16.
 */

public class FacebookFragment extends Fragment {


    private CallbackManager callbackManager;
    private Activity activity;
    private LoginButton loginButton;
    private MyPreferenceManager preferenceManager;
    private FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            LogedInActions();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        activity = this.getActivity();
        preferenceManager = new MyPreferenceManager(activity,DEFAULT_SHARED_PREFERENCE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_facebook_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "email");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, facebookCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void LogedInActions() {
        preferenceManager.editValue(PREF_IS_AUTHENTICATED,true);
        loginButton.setVisibility(View.GONE);
        Profile profile = Profile.getCurrentProfile();
        if ( profile != null){
            View view = activity.getLayoutInflater().inflate(R.layout.toast_custom,
                    (ViewGroup) activity.findViewById(R.id.custom_toast));
            TextView tv = (TextView) view.findViewById(R.id.toast_message);
            tv.setText(profile.getName());
            Toast toast = new Toast(getApplicationContext());
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        }, 3000);
    }
}
