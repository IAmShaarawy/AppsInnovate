package net.elshaarawy.appsinnovate.AsyncTasks;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;

import net.elshaarawy.appsinnovate.Managers.HttpManager;
import net.elshaarawy.appsinnovate.Managers.MyPreferenceManager;
import net.elshaarawy.appsinnovate.Managers.RealmManager;
import net.elshaarawy.appsinnovate.R;
import net.elshaarawy.appsinnovate.RealmModels.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;




import static net.elshaarawy.appsinnovate.Managers.MyPreferenceManager.Keys.*;

/**
 * Created by elshaarawy on 27-Dec-16.
 */

public class CountriesTask extends AsyncTask<Void, Void, String> {
    private final String TAG = this.getClass().getSimpleName();
    private RealmManager realmManager;
    private Activity activity;
    private Dialog dialog;
    private MyPreferenceManager myPreferenceManager;
    private HttpManager httpManager;

    public CountriesTask(RealmManager realmManager, Activity activity) {
        this.realmManager = realmManager;
        this.activity = activity;
        myPreferenceManager = new MyPreferenceManager(activity, DEFAULT_SHARED_PREFERENCE);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(activity);
        dialog.setCancelable(false);
        dialog.setTitle("Loading...");
        dialog.show();
        httpManager = HttpManager.getInstance();
    }

    @Override
    protected String doInBackground(Void... voids) {
        URL countriesUrl;
        String serverResponse;
        try {
            countriesUrl = new URL(activity.getResources().getString(R.string.countries_API));
            serverResponse = httpManager.get(countriesUrl);
            return serverResponse;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        parseData(s);
        dialog.hide();
        myPreferenceManager.editValue(PREF_IS_FIRST_TIME, true);
    }

    private void parseData(String serverResponse) {
        realmManager.clearAll(Country.class);
        try {
            JSONArray data = new JSONArray(serverResponse);
            for (int i = 0; i < data.length(); i++) {
                JSONObject item = data.getJSONObject(i);
                Country country = new Country();
                country.setName(item.getString("name"));
                country.setTopLevelDomainArray(item.getString("topLevelDomain"));
                country.setAlpha2Code(item.getString("alpha2Code"));
                country.setAlpha3Code(item.getString("alpha3Code"));
                country.setCallingCodesArray(item.getString("callingCodes"));
                country.setCapital(item.getString("capital"));
                country.setAltSpellingsArray(item.getString("altSpellings"));
                country.setRelevance(item.getString("relevance"));
                country.setRegion(item.getString("region"));
                country.setSubregion(item.getString("subregion"));
                country.setTranslationsObject(item.getString("translations"));
                country.setPopulation(item.getString("population"));
                country.setLatlngArray(item.getString("latlng"));
                country.setDemonym(item.getString("demonym"));
                country.setArea(item.getString("area"));
                country.setGini(item.getString("gini"));
                country.setTimezonesArray(item.getString("timezones"));
                country.setTimezonesArray(item.getString("borders"));
                country.setNativeName(item.getString("nativeName"));
                country.setNumericCode(item.getString("numericCode"));
                country.setCurrenciesArray(item.getString("currencies"));
                country.setLanguagesArray(item.getString("languages"));
                realmManager.addModel(country);
                Log.i(TAG, "" + country.getCurrenciesArray() + ",   " + i);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
