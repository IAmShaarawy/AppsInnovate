package net.elshaarawy.appsinnovate;

import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.elshaarawy.appsinnovate.ShopdCoordinates.Regions.*;

/**
 * Created by elshaarawy on 27-Dec-16.
 */

public class ShopdCoordinates {
    public HashMap<String, List<Pair<Double, Double>>> coordinatesMap;

    public ShopdCoordinates() {
        coordinatesMap = new HashMap<>();
        ArrayList<Pair<Double, Double>> temp1 = new ArrayList<>();
        ArrayList<Pair<Double, Double>> temp2 = new ArrayList<>();
        ArrayList<Pair<Double, Double>> temp3 = new ArrayList<>();
        ArrayList<Pair<Double, Double>> temp4 = new ArrayList<>();


        temp1.add(new Pair<>(30.006112, 30.971814));
        temp1.add(new Pair<>(30.002112, 30.951712));
        temp1.add(new Pair<>(30.006112, 30.971814));
        temp1.add(new Pair<>(29.993177, 30.96459));
        coordinatesMap.put(OCTOBER_6th, temp1);

        temp2.add(new Pair<>(30.07775, 31.312799));
        temp2.add(new Pair<>(30.10976, 31.304016));
        temp2.add(new Pair<>(30.069479, 31.280893));
        temp2.add(new Pair<>(30.122908, 31.246704));
        temp2.add(new Pair<>(30.047175, 31.242512));
        temp2.add(new Pair<>(30.11357, 31.295391));
        temp2.add(new Pair<>(30.056328, 31.2589));
        temp2.add(new Pair<>(30.074361, 31.345691));
        temp2.add(new Pair<>(30.07557, 31.245044));
        coordinatesMap.put(CAIRO, temp2);

        temp3.add(new Pair<>(30.01045, 31.203711));
        temp3.add(new Pair<>(30.028751, 31.20597));
        temp3.add(new Pair<>(30.050567, 31.19955));
        temp3.add(new Pair<>(30.034158, 31.213819));
        temp3.add(new Pair<>(30.070937, 31.208391));
        temp3.add(new Pair<>(30.039199, 31.203411));
        temp3.add(new Pair<>(30.050149, 31.198836));
        coordinatesMap.put(GIZA, temp3);

        temp4.add(new Pair<>(31.231733, 29.949922));
        temp4.add(new Pair<>(31.213077, 29.942222));
        temp4.add(new Pair<>(31.192365, 29.906019));
        temp4.add(new Pair<>(31.272364, 29.994763));
        temp4.add(new Pair<>(31.167677, 29.932971));
        temp4.add(new Pair<>(31.244963, 29.966639));
        temp4.add(new Pair<>(31.169838, 29.931705));
        coordinatesMap.put(ALEX, temp4);
    }

    public class Regions {
        public static final String OCTOBER_6th = "6th of October";
        public static final String CAIRO = "cairo";
        public static final String GIZA = "giza";
        public static final String ALEX = "alex";
    }
}
