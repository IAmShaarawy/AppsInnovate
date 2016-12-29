package net.elshaarawy.appsinnovate.RealmModels;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Pair;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by elshaarawy on 27-Dec-16.
 */

public class Country extends RealmObject implements Parcelable {

    private String name;
    private String topLevelDomainArray;
    private String alpha2Code;
    private String alpha3Code;
    private String callingCodesArray;
    private String capital;
    private String altSpellingsArray;
    private String relevance;
    private String region;
    private String subregion;
    private String translationsObject;
    private String population;
    private String latlngArray;
    private String demonym;
    private String area;
    private String gini;
    private String timezonesArray;
    private String bordersArray;
    private String nativeName;
    private String numericCode;
    private String currenciesArray;
    private String languagesArray;

    public Country() {
    }

    protected Country(Parcel in) {
        name = in.readString();
        topLevelDomainArray = in.readString();
        alpha2Code = in.readString();
        alpha3Code = in.readString();
        callingCodesArray = in.readString();
        capital = in.readString();
        altSpellingsArray = in.readString();
        relevance = in.readString();
        region = in.readString();
        subregion = in.readString();
        translationsObject = in.readString();
        population = in.readString();
        latlngArray = in.readString();
        demonym = in.readString();
        area = in.readString();
        gini = in.readString();
        timezonesArray = in.readString();
        bordersArray = in.readString();
        nativeName = in.readString();
        numericCode = in.readString();
        currenciesArray = in.readString();
        languagesArray = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(topLevelDomainArray);
        parcel.writeString(alpha2Code);
        parcel.writeString(alpha3Code);
        parcel.writeString(callingCodesArray);
        parcel.writeString(capital);
        parcel.writeString(altSpellingsArray);
        parcel.writeString(relevance);
        parcel.writeString(region);
        parcel.writeString(subregion);
        parcel.writeString(translationsObject);
        parcel.writeString(population);
        parcel.writeString(latlngArray);
        parcel.writeString(demonym);
        parcel.writeString(area);
        parcel.writeString(gini);
        parcel.writeString(timezonesArray);
        parcel.writeString(bordersArray);
        parcel.writeString(nativeName);
        parcel.writeString(numericCode);
        parcel.writeString(currenciesArray);
        parcel.writeString(languagesArray);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopLevelDomainArray() {
        return topLevelDomainArray;
    }

    public void setTopLevelDomainArray(String topLevelDomainArray) {
        this.topLevelDomainArray = topLevelDomainArray;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCallingCodesArray() {
        return callingCodesArray;
    }

    public void setCallingCodesArray(String callingCodesArray) {
        this.callingCodesArray = callingCodesArray;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getAltSpellingsArray() {
        return altSpellingsArray;
    }

    public void setAltSpellingsArray(String altSpellingsArray) {
        this.altSpellingsArray = altSpellingsArray;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getTranslationsObject() {
        return translationsObject;
    }

    public void setTranslationsObject(String translationsObject) {
        this.translationsObject = translationsObject;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getLatlngArray() {
        return latlngArray;
    }

    public void setLatlngArray(String latlngArray) {
        this.latlngArray = latlngArray;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGini() {
        return gini;
    }

    public void setGini(String gini) {
        this.gini = gini;
    }

    public String getTimezonesArray() {
        return timezonesArray;
    }

    public void setTimezonesArray(String timezonesArray) {
        this.timezonesArray = timezonesArray;
    }

    public String getBordersArray() {
        return bordersArray;
    }

    public void setBordersArray(String bordersArray) {
        this.bordersArray = bordersArray;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getCurrenciesArray() {
        return currenciesArray;
    }

    public void setCurrenciesArray(String currenciesArray) {
        this.currenciesArray = currenciesArray;
    }

    public String getLanguagesArray() {
        return languagesArray;
    }

    public void setLanguagesArray(String languagesArray) {
        this.languagesArray = languagesArray;
    }
}
