package net.elshaarawy.appsinnovate.Managers;

import android.util.Log;

import net.elshaarawy.appsinnovate.RealmModels.Country;

import io.realm.Realm;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

import static android.R.attr.country;
import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by elshaarawy on 27-Dec-16.
 */

public class RealmManager {
    private Realm realm;
    private static RealmManager realmManager;

    private final String TAG = this.getClass().getSimpleName();

    private RealmManager() {
            realm = Realm.getDefaultInstance();
    }

    public static RealmManager getInstance(){
        if (realmManager == null){
            realmManager = new RealmManager();
        }
        return realmManager;
    }


    public void clearAll(Class modelClass) {

        realm.beginTransaction();
        realm.delete(modelClass);
        realm.commitTransaction();
    }

    public Realm getRealm() {
        return realm;
    }

    public RealmResults<Country> getAllCountries(){
        return realm.where(Country.class).findAll();
    }

    public void addModel(RealmObject o) {

        realm.beginTransaction();
        realm.copyToRealm(o);
        realm.commitTransaction();

        Log.i(TAG,"addModel");

    }

}
