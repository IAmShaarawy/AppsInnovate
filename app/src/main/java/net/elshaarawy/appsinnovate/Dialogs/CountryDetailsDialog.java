package net.elshaarawy.appsinnovate.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import net.elshaarawy.appsinnovate.RealmModels.Country;

import static net.elshaarawy.appsinnovate.Dialogs.CountryDetailsDialog.Keys.*;

import java.io.BufferedReader;
import java.io.Serializable;

/**
 * Created by elshaarawy on 28-Dec-16.
 */

public class CountryDetailsDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Bundle bundle = getArguments();
        Country country = bundle.getParcelable(DATA);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(country.getName());
        builder.setMessage(String.format("Native name: %s\nPopulation: %s\nCapital: %s\nRegion: %s",
                country.getNativeName(),country.getPopulation(),country.getCapital(),country.getRegion()));
        return builder.create();
    }

    public class Keys {
        public static final String DATA = "data";
    }
}
