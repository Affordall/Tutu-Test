package com.testapps.tututest.network;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface ICityJsonToModelListener {

    long getCountryCityId(JSONObject jsonObjectItem) throws JSONException;
    String getCountryCityTitle(JSONObject jsonObjectItem) throws JSONException;
    String getCountryRegionTitle(JSONObject jsonObjectItem) throws JSONException;
    String getCountryTitle(JSONObject jsonObjectItem) throws JSONException;
    double getCountryPointLongitude(JSONObject jsonObjectItem) throws JSONException;
    double getCountryPointLatitude(JSONObject jsonObjectItem) throws JSONException;
    String getCountryDistrictTitle(JSONObject jsonObjectItem) throws JSONException;
    List<?> getStations(JSONObject jsonObjectItem) throws JSONException;
    String getFromOtTo(JSONObject jsonObjectItem) throws JSONException;

}
