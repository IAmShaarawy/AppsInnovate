package net.elshaarawy.appsinnovate.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import net.elshaarawy.appsinnovate.Adapters.CountriesAdapter;
import net.elshaarawy.appsinnovate.AsyncTasks.CountriesTask;
import net.elshaarawy.appsinnovate.Dialogs.CountryDetailsDialog;
import net.elshaarawy.appsinnovate.Managers.RealmManager;
import net.elshaarawy.appsinnovate.R;
import net.elshaarawy.appsinnovate.RealmModels.Country;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import net.elshaarawy.appsinnovate.Adapters.CountriesAdapter.CountryClickCallback;
import static net.elshaarawy.appsinnovate.Dialogs.CountryDetailsDialog.Keys.*;

import java.util.ArrayList;
import java.util.List;

public class CountriesActivity extends AppCompatActivity implements CountryClickCallback,TextWatcher {
    private RealmManager realmManager;
    private RecyclerView countriesRecyclerView;
    private CountriesAdapter countriesAdapter;
    private RealmResults<Country> allCountries;
    private List<Country> countryList;
    private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        realmManager = RealmManager.getInstance();
        allCountries = realmManager.getAllCountries();
        countryList = new ArrayList<>(allCountries.size());
        countryList.addAll(allCountries);
        search = (EditText) findViewById(R.id.search);
        countriesRecyclerView = (RecyclerView) findViewById(R.id.countries_recycler);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countriesAdapter = new CountriesAdapter(this,countryList,this);
        search.addTextChangedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        countriesRecyclerView.setAdapter(countriesAdapter);
    }

    @Override
    public void onItemClick(int position) {
        CountryDetailsDialog detailsDialog = new CountryDetailsDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DATA,countryList.get(position));
        detailsDialog.setArguments(bundle);
        detailsDialog.show(getFragmentManager(),"");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        RealmResults<Country> countries ;
        if (charSequence.toString()!=null){
            RealmQuery<Country> query = realmManager.getRealm().where(Country.class);
            query.beginsWith("name",charSequence.toString(), Case.INSENSITIVE);
            countries = query.findAll();
        }
        else {
            countries = realmManager.getAllCountries();
        }
        countryList.clear();
        countryList.addAll(countries);
        countriesAdapter.notifyDataSetChanged();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
