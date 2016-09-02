package com.testapps.tututest.network;

import android.content.Context;
import android.util.Log;

import com.testapps.tututest.app.TutuTestApp;
import com.testapps.tututest.common.INamesTable;
import com.testapps.tututest.models.City;
import com.testapps.tututest.network.converters.CityJsonToModelConverter;
import com.testapps.tututest.network.converters.StationJsonToModelConverter;
import com.testapps.tututest.repository.impl.CityRepository;
import com.testapps.tututest.specifications.ISpecification;
import com.testapps.tututest.specifications.ISqlSpecification;
import com.testapps.tututest.specifications.impl.AllCitiesFromSortedListSpecification;
import com.testapps.tututest.specifications.impl.AllCitiesToSortedListSpecification;
import com.testapps.tututest.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class JsonParser implements IJsonParser {

    private static final int REQUEST_CODE_FROM = 100;
    private static final int REQUEST_CODE_TO = 200;
    private static JsonParser sInstance;
    private static final String HTTP_SOURCE_URL = "https://raw.githubusercontent.com/tutu-ru/hire_android_test/master/allStations.json";
    private final CityJsonToModelConverter cityToModelConverter = new CityJsonToModelConverter();
    //private final StationJsonToModelConverter stationToModelConverter = new StationJsonToModelConverter();
    private ICityJsonToModelListener cityToModel;
    //private IStationJsonToModelListener stationToModel;
    private Request request;

    @Inject OkHttpClient client;
    @Inject CityRepository cityRepository;

    public static synchronized JsonParser getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new JsonParser(context);
        }
        return sInstance;
    }

    private JsonParser(Context context) {
       TutuTestApp.get(context).getAppComponent().inject(this);
    }

    private ArrayList<City> getList(int fromOrTo, ISpecification specification) {
        cityToModel = cityToModelConverter.getmCallbackJsonCityToModelConverter();
        //stationToModel = stationToModelConverter.getmCallbackJsonStationToModelConverter();
        request = createRequest(HTTP_SOURCE_URL);
        String serverData = getServerData();
        return checkDataAndTryToGetJson(serverData, fromOrTo, specification);
    }

    private String getServerData() {
        return pullRequestToGetDataFromServer(client, request);
    }

    private Request createRequest(final String url) {
        return new Request.Builder()
                .url(url)
                .build();
    }

    private String pullRequestToGetDataFromServer(final OkHttpClient client, final Request request) {
        String data = "";
        try {
            data = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            Utils.logError(e);
        }
        return data;
    }

    private ArrayList<City> checkDataAndTryToGetJson(final String serverData, int fromOrTo, ISpecification specification) {
        if (serverData != null) {
            Log.e("--SERVER DATA--", " " + serverData);
            return parseJsonToList(serverData, fromOrTo, specification);
        } else {
            throw new RuntimeException("Server data is empty");
        }
    }

    private ArrayList<City> parseJsonToList(String serverData, int fromOrTo, ISpecification specification) {
        try {

            JSONObject cities = new JSONObject(serverData);
            JSONArray arrayCities;
            String cityDestination;
            if(fromOrTo == REQUEST_CODE_FROM) {
                arrayCities = cities.getJSONArray(INamesTable.ARRAY_CITIES_FROM);
                cityDestination = INamesTable.ARRAY_CITIES_FROM;
            } else {
                arrayCities = cities.getJSONArray(INamesTable.ARRAY_CITIES_TO);
                cityDestination = INamesTable.ARRAY_CITIES_TO;
            }

            for (int jsonArrayIndex = 0; jsonArrayIndex < arrayCities.length(); jsonArrayIndex++) {
                JSONObject arrayFromJObj = arrayCities.getJSONObject(jsonArrayIndex);

                City cityItem = new City();
                cityItem.setCountryCityId(cityToModel.getCountryCityId(arrayFromJObj));
                cityItem.setCountryCityTitle(cityToModel.getCountryCityTitle(arrayFromJObj));
                cityItem.setCountryRegionTitle(cityToModel.getCountryRegionTitle(arrayFromJObj));
                cityItem.setCountryTitle(cityToModel.getCountryTitle(arrayFromJObj));
                cityItem.setCountryPointLongitude(cityToModel.getCountryPointLongitude(arrayFromJObj));
                cityItem.setCountryPointLatitude(cityToModel.getCountryPointLatitude(arrayFromJObj));
                cityItem.setCountryDistrictTitle(cityToModel.getCountryDistrictTitle(arrayFromJObj));
                cityItem.setStations(cityToModel.getStations(arrayFromJObj));
                cityItem.setFromOrTo(cityDestination);

                cityRepository.add(cityItem);
            }
        } catch (JSONException e) {
            Utils.logError(e);
        }
        return (ArrayList<City>) cityRepository.query(specification);
    }

    @Override
    public ArrayList<City> getListCityFrom(ISpecification specification) {
        return getList(REQUEST_CODE_FROM, specification);
    }

    @Override
    public ArrayList<City> getListCityTo(ISpecification specification) {
        return getList(REQUEST_CODE_TO, specification);
    }
}
