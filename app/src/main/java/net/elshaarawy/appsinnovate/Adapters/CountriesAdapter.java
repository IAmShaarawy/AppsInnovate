package net.elshaarawy.appsinnovate.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.elshaarawy.appsinnovate.R;
import net.elshaarawy.appsinnovate.RealmModels.Country;

import java.net.URI;
import java.net.URL;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by elshaarawy on 27-Dec-16.
 */

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Country> countriesList;
    private CountryClickCallback countryClickCallback;
    public interface CountryClickCallback {
        void onItemClick(int position);
    }

    public CountriesAdapter(Context context, List<Country> countriesList,CountryClickCallback countryClickCallback) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.countriesList = countriesList;
        this.countryClickCallback = countryClickCallback;
    }

    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.country_item,parent,false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryHolder holder, int position) {
        Country country = countriesList.get(position);
        Uri uri = Uri.parse("http://www.geognos.com/api/en/countries/flag/")
                .buildUpon()
                .appendPath(country.getAlpha2Code()+".png")
                .build();
        Picasso.with(context)
                .load(uri)
                .into(holder.flag);
        holder.name.setText(country.getName());
        holder.capital.setText(country.getCapital());
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public class CountryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView item;
        ImageView flag;
        TextView name,capital;
        public CountryHolder(View itemView) {
            super(itemView);

            item = (CardView) itemView.findViewById(R.id.country_item);
            flag = (ImageView) itemView.findViewById(R.id.flag);
            name = (TextView) itemView.findViewById(R.id.country_name);
            capital = (TextView) itemView.findViewById(R.id.country_capital);

            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.country_item:
                    countryClickCallback.onItemClick(getAdapterPosition());
            }
        }
    }

}
