package com.testapps.tututest.network;

import org.json.JSONException;
import org.json.JSONObject;

public interface IStationJsonToModelListener {

    long getStationId(JSONObject jsonObjectItem) throws JSONException;
    String getStationTitle(JSONObject jsonObjectItem) throws JSONException;
    String getStationCountryTitle(JSONObject jsonObjectItem) throws JSONException;
    double getStationPointLongitude(JSONObject jsonObjectItem) throws JSONException;
    double getStationPointLatitude(JSONObject jsonObjectItem) throws JSONException;
    String getStationDistrictTitle(JSONObject jsonObjectItem) throws JSONException;
    long getStationCityId(JSONObject jsonObjectItem) throws JSONException;
    String getStationCityTitle(JSONObject jsonObjectItem) throws JSONException;
    String getStationRegionTitle(JSONObject jsonObjectItem) throws JSONException;

}
